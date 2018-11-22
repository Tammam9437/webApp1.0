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

@ManagedBean
@Entity
@Table(name = "Like")
@SessionScoped
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlike")
	private int id;

	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;

	@ManyToOne
	@JoinColumn(name = "idlink")
	private Link link;

	public Like(User user, Link link) {
		this.user = user;
		this.link = link;
	}

	public Like() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public int getId() {
		return id;
	}

}