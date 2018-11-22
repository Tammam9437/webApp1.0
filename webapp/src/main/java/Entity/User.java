package Entity;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import DBConnector.ConnectToUserDB;

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

	@OneToMany(targetEntity = Link.class, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Link> links = new ArrayList<Link>();
	
	@OneToMany(targetEntity = Image.class, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Image> images = new ArrayList<Image>();
	
	@OneToMany(targetEntity = Pdf.class, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Pdf> pdfs = new ArrayList<Pdf>();

	public User() {
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String saveInDB() {
		ConnectToUserDB.saveUserInDB(this);
		return "login";
	}

	public boolean userExsistiert(String username) {
		List<User> identicalUsers;
		identicalUsers = ConnectToUserDB.queryUser("from User u where u.name ='" + username + "'");

		if (identicalUsers.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public String getPassword() {
		return password;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Pdf> getPdfs() {
		return pdfs;
	}

	public void setPdfs(List<Pdf> pdfs) {
		this.pdfs = pdfs;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
