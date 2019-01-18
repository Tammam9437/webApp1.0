package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	private String currentSearchedLink;

	public LinkController(MainController mainController) {
		this.mainController = mainController;
		this.link = new Link();
	}

	public void addLink() {
		User user = mainController.getUserController().getUser();
		Category category = mainController.getCategoryController().getCategory();
		Link add = new Link(link.getUrl(), link.getBeschreibung(),user,category );
		user.getLinks().add(add);
		ConnectToLinkDB.saveLinkInDB(add);
		link.setUrl("");
		link.setBeschreibung("");
		mainController.getCategoryController().setCategory(null);
		FacesMessage message = new FacesMessage("Der Link ist erfolgrich eingefügt.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void deleteLink(int linkId) {
		ConnectToLikeDB.queryDeleteLikeFromDB("delete from Li where idlink= " + linkId);
		System.out.println(linkId);
		ConnectToLinkDB.deleteLinkFromDB(linkId);
	}

	public List<Link> getUserLinksInCategory() {
		User user = mainController.getUserController().getUser();
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
			if (currentSearchedLink == null || currentSearchedLink.replaceFirst("^\\s*", "").length() == 0) {
				filteredList = (ArrayList<Link>) ConnectToLinkDB
						.queryLink("From Link WHERE iduser ='" + user.getId() + "' " + " AND DTYPE = 'Link'");
			} else {
				filteredList = (ArrayList<Link>) ConnectToLinkDB
						.queryLink("From Link WHERE DTYPE ='Link' AND beschreibung LIKE '%" + currentSearchedLink + "%'"
								+ " AND iduser ='" + user.getId() + "'");
			}

		} else {
			if (currentSearchedLink == null || currentSearchedLink.replaceFirst("^\\s*", "").length() == 0) {
				filteredList = (ArrayList<Link>) ConnectToLinkDB.queryLink("From Link WHERE iduser ='" + user.getId()
						+ "'" + " AND DTYPE = 'Link' " + "AND idcategory = " + categoryId);
			} else {
				filteredList = (ArrayList<Link>) ConnectToLinkDB
						.queryLink("From Link WHERE DTYPE ='Link' AND beschreibung LIKE '%" + currentSearchedLink + "%'"
								+ " AND iduser ='" + user.getId() + "'" + "AND idcategory = " + categoryId);
			}

		}
		java.util.Collections.sort(filteredList);
		return filteredList;
	}

	public List<Link> getLinksInCategory() {
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
			if (currentSearchedLink == null || currentSearchedLink.replaceFirst("^\\s*", "").length() == 0) {
				filteredList = (ArrayList<Link>) ConnectToLinkDB.queryLink("From Link WHERE DTYPE = 'Link' ");
			} else {
				filteredList = (ArrayList<Link>) ConnectToLinkDB.queryLink(
						"From Link WHERE DTYPE ='Link' AND beschreibung LIKE '%" + currentSearchedLink + "%'");
			}
		} else {
			if (currentSearchedLink == null || currentSearchedLink.replaceFirst("^\\s*", "").length() == 0) {
				filteredList = (ArrayList<Link>) ConnectToLinkDB
						.queryLink("From Link WHERE DTYPE = 'Link' AND idcategory = " + categoryId);
			} else {
				filteredList = (ArrayList<Link>) ConnectToLinkDB
						.queryLink("From Link WHERE DTYPE ='Link' AND beschreibung LIKE '%" + currentSearchedLink + "%'"
								+ "AND idcategory = " + categoryId);
			}
		}
		java.util.Collections.sort(filteredList);
		return filteredList;
	}


	public void saveLike() {
		Li like = new Li();
		like.saveInDB();
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


	public String getCurrentSearchedLink() {
		return currentSearchedLink;
	}

	public void setCurrentSearchedLink(String currentSearchedLink) {
		this.currentSearchedLink = currentSearchedLink;
	}

}