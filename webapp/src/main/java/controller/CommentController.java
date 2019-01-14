package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CommentController {

	private MainController mainController;

	public CommentController(MainController mainController) {
		this.mainController = mainController;
	}

	public MainController getMainController() {
		return mainController;
	}

}
