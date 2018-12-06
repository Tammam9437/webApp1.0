package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dbConnector.ConnectToCategoryDB;
import entity.Category;

@ManagedBean
@SessionScoped
public class CategoryController {

	private MainController mainController;
	
	private Category category;

	public CategoryController(MainController mainController) {
		this.mainController = mainController;
		this.category = null;
	}
	
	public List<Category> completeCategory(String query) {
        List<Category> allCategorys = ConnectToCategoryDB.queryCategory("From Category");
        List<Category> filteredCategorys = new ArrayList<Category>();
         
        for (int i = 0; i < allCategorys.size(); i++) {
        	Category skin = allCategorys.get(i);
            if(skin.getName().toLowerCase().contains(query)) {
                filteredCategorys.add(skin);
            }
        }
         
        return filteredCategorys;
    }

	public MainController getMainController() {
		return mainController;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
