import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	
	public Link() { }
	
	
	public Link( String url, String beschreibung) {
		this.url = url;
		this.beschreibung = beschreibung;
	}
	
	public  List<Link> getLinksFromDB(){
		List <Link> list = ConnectToDBLink.queryLink("From Link");
		return 	list;
	}

//	public void addLink(int userid) {
//		Link link = new Link(userid, this.url, this.beschreibung);
//		ConnectToDBLink.saveLinkInDB(link);
//	}
	


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
	
	@Override
	public String toString() {
		return "Link [id=" + id + ", url=" + url + ", beschreibung=" + beschreibung + "]";
	}

	
}
