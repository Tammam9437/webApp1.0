package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dbConnector.ConnectToCategoryDB;
import dbConnector.ConnectToLikeDB;
import dbConnector.ConnectToLinkDB;
import entity.Category;
import entity.Li;
import entity.Link;
import entity.User;

@ManagedBean
@SessionScoped
public class LinkController {

	private MainController mainController;
	private Link link;

	private boolean showLinkForm;
	private boolean showAddLink;
	private boolean showFavouriteLinks;

	public boolean isShowFavouriteLinks() {
		return showFavouriteLinks;
	}

	public void setShowFavouriteLinks(boolean showFavouriteLinks) {
		this.showFavouriteLinks = showFavouriteLinks;
	}

	public LinkController(MainController mainController) {
		this.mainController = mainController;
		this.link = new Link();
		this.showAddLink = false;
		this.showLinkForm = false;
		this.showFavouriteLinks = false;
	}

	public void addLink() {
		Link add = new Link(link.getUrl(), link.getBeschreibung());
		User user = mainController.getUserController().getUser();
		Category category = mainController.getCategoryController().getCategory();
		showAddLink = false;
		showLinkForm = true;
		user.getLinks().add(add);
		add.setUser(user);
		add.setCategory(category);
		ConnectToLinkDB.saveLinkInDB(add);
		link.setUrl("");
		link.setBeschreibung("");
		add.setUser(null);
	}

	public List<Link> getUserLinks() {
		User user = mainController.getUserController().getUser();
		List<Link> list = ConnectToLinkDB
				.queryLink("From Link WHERE iduser ='" + user.getId() + "'" + " AND DTYPE != 'YoutubeLink'");
		return list;
	}
	public void deleteLink(int linkId) {
		ConnectToLikeDB.queryDeleteLikeFromDB("delete from Li where idlink= " + linkId);
		System.out.println(linkId);
		ConnectToLinkDB.deleteLinkFromDB(linkId);
	}

	public List<Link> getLinksInCategory() {
		String currentCategory = mainController.getFilterController().getCurrentCategory();
		List<Category> categoryList = ConnectToCategoryDB.queryCategory("From Category");
		int categoryId = -1;
		ArrayList<Link> filteredList;
		for(Category category : categoryList) {
			if(category.getName().equalsIgnoreCase(currentCategory)) {
				categoryId = category.getIdCategory();
			}
		}
		if(categoryId < 0) {
			filteredList = (ArrayList<Link>) ConnectToLinkDB.queryLink("From Link WHERE DTYPE != 'YoutubeLink' ");
		}else {
			filteredList = (ArrayList<Link>) ConnectToLinkDB.queryLink("From Link WHERE DTYPE != 'YoutubeLink' AND idcategory = "+ categoryId );
		}
		java.util.Collections.sort(filteredList);
		return filteredList;
	}

	public void showAddLinkToggel() {
		if (showAddLink) {
			showAddLink = false;
		} else {
			mainController.closeAll();
			showAddLink = true;
		}
	}

	public void showFavouriteLinksToggel() {
		if (showFavouriteLinks) {
			showFavouriteLinks = false;
		} else {
			mainController.closeAll();
			showFavouriteLinks = true;
		}
	}

	public void saveLike() {
		Li like = new Li();
		like.saveInDB();
	}

	public void showLinksToggel() {
		if (showLinkForm) {
			showLinkForm = false;
		} else {
			mainController.closeAll();
			showLinkForm = true;
		}
	}

	public MainController getMainController() {
		return mainController;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public boolean isShowLinkForm() {
		return showLinkForm;
	}

	public void setShowLinkForm(boolean showLinkForm) {
		this.showLinkForm = showLinkForm;
	}

	public boolean isShowAddLink() {
		return showAddLink;
	}

	public void setShowAddLink(boolean showAddLink) {
		this.showAddLink = showAddLink;
	}

}