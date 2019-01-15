package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CommentController {

	private MainController mainController;
	
	private boolean showCommentArea;
	

	public CommentController(MainController mainController) {
		this.mainController = mainController;
		this.showCommentArea = false;
	}
	
	
	public void showCommentAreaToggel() {
		if (showCommentArea) {
			showCommentArea = false;
		} else {
			showCommentArea = true;
		}
		System.out.println(showCommentArea);
	}


	public MainController getMainController() {
		return mainController;
	}


	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}


	public boolean isShowCommentArea() {
		return showCommentArea;
	}


	public void setShowCommentArea(boolean showCommentArea) {
		this.showCommentArea = showCommentArea;
	}
	
	

}
