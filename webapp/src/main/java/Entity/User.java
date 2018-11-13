package Entity;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import DBConnector.ConnectToDB;

@ManagedBean
@Entity
@Table(name = "User")
@SessionScoped
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	public User() {
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String saveInDB() {
		ConnectToDB.saveUserInDB(this);
		return "login";
	}

	public boolean confirmLoginData() {

		List<User> identicalUsers;
		identicalUsers = ConnectToDB
				.queryUser("from User u where u.name = '" + name + "' And u.password= '" + password + "'");

		if (identicalUsers.isEmpty()) {
			return false;
		}
		ConnectToDB.displayUsers(identicalUsers);
		// da den id blebt wie der id von user der in DB geschpeichert "RequestScobe"
		setId(identicalUsers.get(0).getId());
		return true;
	}

	public boolean userExsistiert(String username) {
		List<User> identicalUsers;
		identicalUsers = ConnectToDB.queryUser("from User u where u.name ='" + username + "'");

		if (identicalUsers.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public String navigationFromLogin() {
		if (confirmLoginData()) {
			return "userHomePage";
		}
		return "error";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
