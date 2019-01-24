package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dbConnector.ConnectToCategoryDB;
import dbConnector.ConnectToImageDB;
import entity.Category;
import entity.Image;
import entity.User;

@ManagedBean
@SessionScoped
public class ImageController {

	private MainController mainController;

	public ImageController(MainController mainController) {
		this.mainController = mainController;
	}

	public List<Image> getAllImagesFromDB() {
		List<Image> imageList = ConnectToImageDB.queryImage("From Image");

		return imageList;

	}

	public List<Image> getUserImages() {
		User user = mainController.getUserController().getUser();
		return ConnectToImageDB.getUserImagesFromDB(user);

	}

	public List<Image> getImagesInCategory() {
		String currentCategory = mainController.getFilterController().getCurrentCategory();
		List<Category> categoryList = ConnectToCategoryDB.queryCategory("From Category");
		int categoryId = -1;
		ArrayList<Image> filteredList;
		for (Category category : categoryList) {
			if (category.getName().equalsIgnoreCase(currentCategory)) {
				categoryId = category.getIdCategory();
			}
		}
		if (categoryId < 0) {
			filteredList = (ArrayList<Image>) ConnectToImageDB.queryImage("From Image");
		} else {
			filteredList = (ArrayList<Image>) ConnectToImageDB
					.queryImage("From Image WHERE idcategory = " + categoryId);
		}
		java.util.Collections.sort(filteredList);
		return filteredList;
	}

	public String toImageInfos() {
		return "toImageInfos";
	}

	public MainController getMainController() {
		return mainController;
	}

}
