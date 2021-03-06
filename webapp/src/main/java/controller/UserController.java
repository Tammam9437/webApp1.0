package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dbConnector.ConnectToLikeDB;
import dbConnector.ConnectToUserDB;
import entity.Email;
import entity.Li;
import entity.User;
import exception.UserIsNullException;

@ManagedBean
@SessionScoped
public class UserController {

	private MainController mainController;

	private User user;
	private Email email;

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
		if (user != null) {
			ConnectToUserDB.updateUserPassword(user.getId(), user.getPassword());
			return "successChangePassword";
		} else {
			throw new UserIsNullException("melden sie sich bitte erneut an");
		}

	}

	public boolean confirmLoginData() {
		if (user != null) {
			List<User> identicalUsers;
			identicalUsers = ConnectToUserDB.queryUser(
					"from User u where u.name = '" + user.getName() + "' And u.password= '" + user.getPassword() + "'");

			if (identicalUsers.isEmpty()) {
				return false;
			}
			// da den id blebt wie der id von user der in DB geschpeichert "RequestScobe"
			user.setId(identicalUsers.get(0).getId());
			return true;
		} else {
			throw new UserIsNullException("melden sie sich bitte erneut an");
		}

	}

	public String logOut() {
		this.user = new User();
		this.email = new Email();
		return "login";
	}

	public List<Li> favoritesLinks() {
		if (user != null) {
			List<Li> likes = ConnectToLikeDB.getUserFavoritLinks(user);
			return likes;
		} else {
			throw new UserIsNullException("melden sie sich bitte erneut an");
		}

	}

	public String navigationFromLogin() {
		if (confirmLoginData()) {
			return "userHomePage";
		} else {
			return "loginError";
		}

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