package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dbConnector.ConnectToCategoryDB;
import dbConnector.ConnectToPdfDB;
import entity.Category;
import entity.Pdf;

@ManagedBean
@SessionScoped
public class UploadController {

	private MainController mainController;

	private boolean showUpload;

	public UploadController(MainController mainController) {
		this.mainController = mainController;

		this.showUpload = false;
	}

	public void showUploadToggel() {
		if (showUpload) {
			showUpload = false;
		} else {
			mainController.closeAll();
			showUpload = true;
		}
	}
	
	public List<Pdf> getPdfsInCategory() {
		String currentCategory = mainController.getFilterController().getCurrentCategory();
		List<Category> categoryList = ConnectToCategoryDB.queryCategory("From Category");
		int categoryId = -1;
		ArrayList<Pdf> filteredList;
		for(Category category : categoryList) {
			if(category.getName().equalsIgnoreCase(currentCategory)) {
				categoryId = category.getIdCategory();
			}
		}
		if(categoryId < 0) {
			filteredList = (ArrayList<Pdf>) ConnectToPdfDB.queryPdf("From Pdf");
		}else {
			filteredList = (ArrayList<Pdf>) ConnectToPdfDB.queryPdf("From Pdf WHERE category = "+ categoryId );
		}
		java.util.Collections.sort(filteredList);
		return filteredList;
	}

	public List<Pdf> getAllPdfsFromDB() {
		List<Pdf> list = ConnectToPdfDB.queryPdf("From Pdf ");
		return list;
	}

	public String onClickImage() {
		return "http://localhost:8080/webapp/image";
	}

	public MainController getMainController() {
		return mainController;
	}

	public boolean isShowUpload() {
		return showUpload;
	}

	public void setShowUpload(boolean showUpload) {
		this.showUpload = showUpload;
	}

}
