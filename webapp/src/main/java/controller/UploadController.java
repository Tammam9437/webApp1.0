package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dbConnector.ConnectToCategoryDB;
import dbConnector.ConnectToImageDB;
import dbConnector.ConnectToPdfDB;
import entity.Category;
import entity.Pdf;

@ManagedBean
@SessionScoped
public class UploadController {

	private MainController mainController;

	private boolean showUpload;
	
	private boolean showPdfs;

	public UploadController(MainController mainController) {
		this.mainController = mainController;

		this.showUpload = false;
		this.showPdfs = false;
	}

	public void showPdfsToggel() {
		if (showPdfs) {
			showPdfs = false;
		} else {
			mainController.closeAll();
			showPdfs = true;
		}
	}
	
	public void showUploadToggel() {
		if (showUpload) {
			showUpload = false;
		} else {
			mainController.closeAll();
			showUpload = true;
		}
	}
	
	public void deletePdf(int pdfId) {
		ConnectToPdfDB.deletePdfFromDB(pdfId);
		addMessage("Delete Pdf","Pdf has been deleted."); 
	}
	
	public void deleteImage(int imageId) {
		ConnectToImageDB.deleteImageFromDB(imageId);
		addMessage("Delete Image","Image has been deleted."); 
	}
	
	public void addMessage(String summary, String detail) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
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
			filteredList = (ArrayList<Pdf>) ConnectToPdfDB.queryPdf("From Pdf WHERE idcategory = "+ categoryId );
		}
		java.util.Collections.sort(filteredList);
		return filteredList;
	}
	
	public List<Pdf> getUserPdfsInCategory() {
		String currentCategory = mainController.getFilterController().getCurrentCategory();
		List<Category> categoryList = ConnectToCategoryDB.queryCategory("From Category");
		int userId = mainController.getUserController().getUser().getId();
		int categoryId = -1;
		ArrayList<Pdf> filteredList;
		for(Category category : categoryList) {
			if(category.getName().equalsIgnoreCase(currentCategory)) {
				categoryId = category.getIdCategory();
			}
		}
		if(categoryId < 0) {
			filteredList = (ArrayList<Pdf>) ConnectToPdfDB.queryPdf("From Pdf WHERE iduser = "+ userId);
		}else {
			filteredList = (ArrayList<Pdf>) ConnectToPdfDB.queryPdf("From Pdf WHERE idcategory = "+ categoryId + " AND iduser = "+ userId );
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

	public boolean isShowPdfs() {
		return showPdfs;
	}

	public void setShowPdfs(boolean showPdfs) {
		this.showPdfs = showPdfs;
	}

}
