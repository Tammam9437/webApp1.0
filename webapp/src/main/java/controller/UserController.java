package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dbConnector.ConnectToLikeDB;
import dbConnector.ConnectToUserDB;
import entity.Email;
import entity.Li;
import entity.User;

@ManagedBean
@SessionScoped
public class UserController {

	private MainController mainController;

	private User user;
	private Email email ;

	public UserController(MainController mainController) {
		this.mainController = mainController;
		this.user = new User();
		this.email = new Email();
	}
	
	public String saveUserInDB() {
		email.setUser(user);
		user.setEmail(email);
		ConnectToUserDB.saveUserInDB(user);
		user = new User();
		email = new Email();
		return "login";
	}
	
	public String changePassword() {
		ConnectToUserDB.updateUserPassword(user.getId(), user.getPassword());
		return "successChangePassword";
	}

	public boolean confirmLoginData() {

		List<User> identicalUsers;
		identicalUsers = ConnectToUserDB.queryUser(
				"from User u where u.name = '" + user.getName() + "' And u.password= '" + user.getPassword() + "'");

		if (identicalUsers.isEmpty()) {
			return false;
		}
		ConnectToUserDB.displayUsers(identicalUsers);
		// da den id blebt wie der id von user der in DB geschpeichert "RequestScobe"
		user.setId(identicalUsers.get(0).getId());
		return true;
	}

	public List<Li> favoritesLinks() {
		List<Li> likes = ConnectToLikeDB.getUserLikes(user);
		return likes;
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

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}