package Controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DBConnector.ConnectToImageDB;
import Entity.Image;
import Entity.User;

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
