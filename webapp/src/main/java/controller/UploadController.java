package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dbConnector.ConnectToPdfDB;
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
