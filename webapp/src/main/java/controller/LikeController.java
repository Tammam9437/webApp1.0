package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LikeController {

	private MainController mainController;

	public LikeController(MainController mainController) {
		this.mainController = mainController;
	}

	public MainController getMainController() {
		return mainController;
	}

}
