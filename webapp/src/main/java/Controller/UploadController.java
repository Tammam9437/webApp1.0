package Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
			showUpload = true;
		}
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
