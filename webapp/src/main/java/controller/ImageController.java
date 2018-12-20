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
	private boolean showImages;
	private boolean showUserImages;

	public ImageController(MainController mainController) {
		this.mainController = mainController;
		this.showImages = false;
		this.showUserImages = false;
	}
	
	public List<Image> getAllImagesFromDB() {
		List<Image> imageList = ConnectToImageDB.queryImage("From Image");
		
		return imageList;
		
	}
	
	public List<Image> getUserImages() {
		User user = mainController.getUserController().getUser();
		List<Image> list = ConnectToImageDB.queryImage("From Image WHERE iduser ='" + user.getId() + "'");
		return list;
	}
	
	public void showImagesToggel() {
		if (showImages) {
			showImages = false;
		} else {
			mainController.closeAll();
			showImages = true;
		}
	}
	public void showUserImagesToggel() {
		if (showUserImages) {
			showUserImages = false;
		} else {
			mainController.closeAll();
			showUserImages = true;
		}
	}
	
	public List<Image> getImagesInCategory() {
		String currentCategory = mainController.getFilterController().getCurrentCategory();
		List<Category> categoryList = ConnectToCategoryDB.queryCategory("From Category");
		int categoryId = -1;
		ArrayList<Image> filteredList;
		for(Category category : categoryList) {
			if(category.getName().equalsIgnoreCase(currentCategory)) {
				categoryId = category.getIdCategory();
			}
		}
		if(categoryId < 0) {
			filteredList = (ArrayList<Image>) ConnectToImageDB.queryImage("From Image");
		}else {
			filteredList = (ArrayList<Image>) ConnectToImageDB.queryImage("From Image WHERE idcategory = "+ categoryId );
		}
		java.util.Collections.sort(filteredList);
		return filteredList;
	}

	public MainController getMainController() {
		return mainController;
	}

	public boolean isShowImages() {
		return showImages;
	}

	public void setShowImages(boolean showImages) {
		this.showImages = showImages;
	}

	public boolean isShowUserImages() {
		return showUserImages;
	}

	public void setShowUserImages(boolean showUserImages) {
		this.showUserImages = showUserImages;
	}
	
}
