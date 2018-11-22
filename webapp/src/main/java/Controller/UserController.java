package Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Entity.User;

@ManagedBean
@SessionScoped
public class UserController {

	private MainController mainController;

	private User user;

	public UserController(MainController mainController) {
		this.mainController = mainController;
	}

	public MainController getMainController() {
		return mainController;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}