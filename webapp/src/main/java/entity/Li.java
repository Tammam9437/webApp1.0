package entity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dbConnector.ConnectToLikeDB;

@ManagedBean
@Entity
@Table(name = "Li")
@SessionScoped
public class Li {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlike")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idlink")
	private Link link;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iduser")
	private User user;
	
	public Li() {
		
	}
	
	public void saveInDB() {
		ConnectToLikeDB.saveLikeInDB(this);;

}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

}
