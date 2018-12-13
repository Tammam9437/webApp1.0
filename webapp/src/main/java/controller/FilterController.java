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

	private List<String> sortLikes;

	private List<String> categorys;

	private List<String> types;

	private String currentsortLike;

	private String currentCategory;

	private String currentType;

	public FilterController(MainController mainController) {
		this.mainController = mainController;
		initFilterCategor();
		initFilterLikes();
		initFilterType();

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

	public void initFilterType() {
		this.types = new ArrayList<>();

		this.types.add("video");
		this.types.add("pdf");
		this.types.add("link");
		this.types.add("foto");
		this.types.add("All");
		this.currentType = "All";
	}

	public void initFilterLikes() {

		this.sortLikes = new ArrayList<>();
		this.sortLikes.add("aufsteigend");
		this.sortLikes.add("absteigend");
		this.currentsortLike = "aufsteiged";
	}

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
	
	public void print(){
		System.out.println(currentCategory);
	}

	public MainController getMainController() {
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public List<String> getSortLikes() {
		return sortLikes;
	}

	public void setSortLikes(List<String> sortLikes) {
		this.sortLikes = sortLikes;
	}

	public List<String> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<String> categorys) {
		this.categorys = categorys;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
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

	public String getCurrentType() {
		return currentType;
	}

	public void setCurrentType(String currentType) {
		this.currentType = currentType;
	}

}