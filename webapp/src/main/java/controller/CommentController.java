package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dbConnector.ConnectToCommentDB;
import dbConnector.ConnectToImageDB;
import entity.Comment;
import entity.Image;
import entity.User;
import exception.UserIsNullException;

@ManagedBean
@SessionScoped
public class CommentController {

	private MainController mainController;

	private String text;

	private int imageIdInfo;

	public CommentController(MainController mainController) {
		this.mainController = mainController;
	}

	public void saveComment() {
		User user = mainController.getUserController().getUser();
		if (user != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			String imageId = context.getExternalContext().getRequestParameterMap().get("imageId");
			Image image = ConnectToImageDB.getImageFromDB(Integer.parseInt(imageId));

			Comment comment = new Comment(text, user, image);
			ConnectToCommentDB.saveCommentInDB(comment);
			this.text = null;
		} else {
			throw new UserIsNullException("melden sie sich bitte erneut an");
		}
	}

	public Image getImage() {
		return ConnectToImageDB.getImageFromDB(imageIdInfo);

	}

	public MainController getMainController() {
		return mainController;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getImageIdInfo() {
		return imageIdInfo;
	}

	public void ImageIdInfoSet(int imageIdInfo) {
		this.imageIdInfo = imageIdInfo;
	}

	public void setImageIdInfo(int imageIdInfo) {
		this.imageIdInfo = imageIdInfo;
	}

}
