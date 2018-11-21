package Entity;
import java.util.List;

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

import DBConnector.ConnectToDB;

@ManagedBean
@Entity	
@Table(name="Link")
@SessionScoped
public class Link {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idlink")
	private int id;
	
	
	@Column(name="url")
	private String url;
	
	@Column(name="beschreibung")
	private String beschreibung;
	
	@Column(name="likes")
	private int likes;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;

	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Link() {
		this.likes = 0;
	}
	
	
	public Link( String url, String beschreibung) {
		this.url = url;
		this.beschreibung = beschreibung;
	}
	
	public  List<Link> getLinksFromDB(){
		List <Link> list = ConnectToDB.queryLink("From Link");
		return 	list;
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public void likesIncrease() {
		ConnectToDB.updateLink(this.id); ; 
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}


	@Override
	public String toString() {
		return "Link [id=" + id + ", url=" + url + ", beschreibung=" + beschreibung + "]";
	}

	
}
