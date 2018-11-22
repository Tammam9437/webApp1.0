package Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import View.FileUploadView;

@ManagedBean
@SessionScoped
public class MainController {
	private UserController userController;
	private LikeController likeController;
	private LinkController linkController;
	private FileUploadView fileUploadView;
	private UploadController uploadController;

	public MainController() {
		this.userController = new UserController(this);
		this.likeController = new LikeController(this);
		this.linkController = new LinkController(this);
		this.fileUploadView = new FileUploadView(this);
		this.uploadController = new UploadController(this);
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public LikeController getLikeController() {
		return likeController;
	}

	public void setLikeController(LikeController likeController) {
		this.likeController = likeController;
	}

	public LinkController getLinkController() {
		return linkController;
	}

	public void setLinkController(LinkController linkController) {
		this.linkController = linkController;
	}

	public FileUploadView getFileUploadView() {
		return fileUploadView;
	}

	public void setFileUploadView(FileUploadView fileUploadView) {
		this.fileUploadView = fileUploadView;
	}

	public UploadController getUploadController() {
		return uploadController;
	}

	public void setUploadController(UploadController uploadController) {
		this.uploadController = uploadController;
	}

}
