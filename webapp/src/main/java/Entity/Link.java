package Entity;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import DBConnector.ConnectToLikeDB;
import DBConnector.ConnectToLinkDB;

@ManagedBean
@Entity
@Table(name = "Link")
@SessionScoped
public class Link implements Comparable<Link> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlink")
	private int id;

	@Column(name = "url")
	private String url;

	@Column(name = "beschreibung")
	private String beschreibung;

	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Li.class, mappedBy = "link", cascade = CascadeType.ALL)
	private List<Li> likes = new ArrayList<Li>();

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

	}

	public Link(String url, String beschreibung) {
		this.url = url;
		this.beschreibung = beschreibung;
	}

	public List<Link> getLinksFromDB() {
		ArrayList<Link> list = (ArrayList<Link>) ConnectToLinkDB.queryLink("From Link WHERE DTYPE != 'YoutubeLink' ");
		java.util.Collections.sort(list);
		return list;
	}
	
	public void addLike(User user) {
		Li like = new Li();
		like.setLink(this);
		like.setUser(user);
		likes.add(like);
		ConnectToLikeDB.saveLikeInDB(like);
	}
	
	public boolean isUserEnteredLike(User user) {
		List<Li> userLinkLikes = ConnectToLikeDB.getUserLinkLikes(user, this);
		if(userLinkLikes.isEmpty()) {
			return false;
		}
		return true;
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

	public List<Li> getLikes() {
		return likes;
	}

	public void setLikes(List<Li> likes) {
		this.likes = likes;
	}
	public int getLikesNumber() {
		return this.likes.size();
	}
	
	public String youtube() {
		return "https://www.youtube.com/embed/";
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", url=" + url + ", beschreibung=" + beschreibung + "]";
	}

	public int compareTo(Link anotherLink) {
		return anotherLink.getLikes().size() - this.likes.size();
	}

}
