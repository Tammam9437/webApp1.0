package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dbConnector.ConnectToCategoryDB;
import entity.Category;

@ManagedBean
@SessionScoped
public class FilterController {

	private MainController mainController;

	private List<String> links;

	

	private List<String> categorys;

	private String currentsortLike;

	private String currentCategory;

	public FilterController(MainController mainController) {
		this.mainController = mainController;
		initFilterCategor();

	}

	public void initFilterCategor() {
		this.categorys = new ArrayList<>();
		List<Category> listCategory = ConnectToCategoryDB.queryCategory("From Category");

		for (Category category : listCategory) {
			this.categorys.add(category.getName());
		}

		this.categorys.add("All");
		this.currentsortLike = "All";

	}
//
//	public void initFilterLinks() {
//		this.categorys = new ArrayList<>();
//		List<Link> listLink = ConnectToLinkDB.queryLink("From Link WHERE DTYPE = 'Link' ");
//
//		for (Link link : listLink) {
//			this.links.add(link.getBeschreibung());
//		}
//	}
//
//
//	public List<String> completeLink(String query) {
//		List<String> allLinks = this.links;
//		List<String> filteredLinks = new ArrayList<String>();
//
//		for (int i = 0; i < allLinks.size(); i++) {
//			String skin = allLinks.get(i);
//			if (skin.toLowerCase().contains(query)) {
//				filteredLinks.add(skin);
//			}
//		}
//
//		return filteredLinks;
//	}

	public List<String> completeCategory(String query) {
		List<String> allCategorys = this.categorys;
		List<String> filteredCategorys = new ArrayList<String>();

		for (int i = 0; i < allCategorys.size(); i++) {
			String skin = allCategorys.get(i);
			if (skin.toLowerCase().contains(query)) {
				filteredCategorys.add(skin);
			}
		}

		return filteredCategorys;
	}

	public MainController getMainController() {
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public List<String> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<String> categorys) {
		this.categorys = categorys;
	}

	public String getCurrentsortLike() {
		return currentsortLike;
	}

	public void setCurrentsortLike(String currentsortLike) {
		this.currentsortLike = currentsortLike;
	}

	public String getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(String currentCategory) {
		this.currentCategory = currentCategory;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}


}