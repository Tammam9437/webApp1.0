package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dbConnector.ConnectToCategoryDB;
import dbConnector.ConnectToLinkDB;
import entity.Category;
import entity.Link;
import entity.User;
import entity.YoutubeLink;
import exception.UserIsNullException;

@ManagedBean
@SessionScoped
public class VideoController {

	private MainController mainController;

	private YoutubeLink youtubeLink;

	public VideoController(MainController mainController) {
		this.mainController = mainController;
		this.youtubeLink = new YoutubeLink();
	}

	public void addYoutubeVideo() {
		User user = mainController.getUserController().getUser();
		if (user != null) {
			YoutubeLink add = new YoutubeLink(youtubeLink.getUrl(), youtubeLink.getBeschreibung());
			Category category = mainController.getCategoryController().getCategory();
			user.getLinks().add(add);
			add.setUser(user);
			add.setCategory(category);
			ConnectToLinkDB.saveLinkInDB(add);
			youtubeLink.setyoutubeUrl("");
			youtubeLink.setBeschreibung("");
			mainController.getCategoryController().setCategory(null);
			FacesMessage message = new FacesMessage("Youtube Video wurde erfolgreich eingefügt.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			throw new UserIsNullException("melden sie sich bitte erneut an");
		}

	}

	public List<Link> getUserYoutubeLinks() {
		User user = mainController.getUserController().getUser();
		if (user != null) {
			List<Link> list = ConnectToLinkDB
					.queryLink("From Link WHERE iduser ='" + user.getId() + "'" + " AND DTYPE = 'YoutubeLink'");
			return list;
		} else {
			throw new UserIsNullException("melden sie sich bitte erneut an");
		}

	}

	public List<Link> getVideosInCategory() {
		String currentCategory = mainController.getFilterController().getCurrentCategory();
		List<Category> categoryList = ConnectToCategoryDB.queryCategory("From Category");
		int categoryId = -1;
		ArrayList<Link> filteredList;
		for (Category category : categoryList) {
			if (category.getName().equalsIgnoreCase(currentCategory)) {
				categoryId = category.getIdCategory();
			}
		}
		if (categoryId < 0) {
			filteredList = (ArrayList<Link>) ConnectToLinkDB.queryLink("From Link WHERE DTYPE = 'YoutubeLink'");
		} else {
			filteredList = (ArrayList<Link>) ConnectToLinkDB
					.queryLink("From Link WHERE DTYPE = 'YoutubeLink' AND idcategory =" + categoryId);
		}
		java.util.Collections.sort(filteredList);
		return filteredList;
	}

	public void deleteVedio(int videoId) {
		System.out.println("Deleting Video");
		ConnectToLinkDB.deleteLinkFromDB(videoId);
	}

	public List<Link> getYoutubeLinks() {
		List<Link> list = ConnectToLinkDB.queryLink("From Link WHERE DTYPE = 'YoutubeLink'");
		return list;
	}

	public MainController getMainController() {
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public YoutubeLink getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(YoutubeLink youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

}
