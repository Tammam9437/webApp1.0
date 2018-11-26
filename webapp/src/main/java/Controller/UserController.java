package Controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DBConnector.ConnectToUserDB;
import Entity.User;

@ManagedBean
@SessionScoped
public class UserController {

	private MainController mainController;

	private User user;

	public UserController(MainController mainController) {
		this.mainController = mainController;
		this.user = new User();
	}
	
	public boolean confirmLoginData() {

		List<User> identicalUsers;
		identicalUsers = ConnectToUserDB
				.queryUser("from User u where u.name = '" + user.getName() + "' And u.password= '" + user.getPassword() + "'");

		if (identicalUsers.isEmpty()) {
			return false;
		}
		ConnectToUserDB.displayUsers(identicalUsers);
		// da den id blebt wie der id von user der in DB geschpeichert "RequestScobe"
		user.setId(identicalUsers.get(0).getId());
		return true;
	}
	
	
	public String navigationFromLogin() {
		if (confirmLoginData()) {
			return "userHomePage";
		}
		return "error";
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