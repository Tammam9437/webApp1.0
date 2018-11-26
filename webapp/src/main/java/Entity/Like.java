package Entity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import DBConnector.ConnectToLikeDB;

@ManagedBean
@Entity
@Table(name = "Likes")
@SessionScoped
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlike")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idlink")
	private Link link;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;
	
	public Like() {
		
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
