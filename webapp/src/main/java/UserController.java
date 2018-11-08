import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserController {

	private User user;
	private Link link;
	private boolean showLinkForm;
	private boolean showAddLink;
	private boolean showAddImage;
	
	public UserController(User user, Link link) {
		this.user = user;
		this.link = link;
	}
	public UserController() {
		this.user=new User();
		this.link= new Link();
		this.showAddLink = false;
		this.showLinkForm = false;
		this.showAddImage = false;
	}
	
	public void addLink() {
		Link add = new Link(link.getUrl(), link.getBeschreibung());
		showAddLink = false;
		showLinkForm = true;
		ConnectToDB.saveLinkInDB(add);
	}
	
	public void showAddImageToggel() {
		if(showAddImage) {
			showAddImage=false;
		}else {
			showAddImage=true;
		}
	}
	
	public String onClickImage() {
		return "http://localhost:8080/webapp/image";
	}
	
	
	public void showAddLinkToggel() {
		if(showAddLink) {
			showAddLink=false;
		}else {
			showAddLink=true;
		}
	}
	public void showLinksToggel() {
		if(showLinkForm) {
			showLinkForm=false;
		}else {
			showLinkForm=true;
		}
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
	public boolean isShowLinkForm() {
		return showLinkForm;
	}
	public void setShowLinkForm(boolean showLinkForm) {
		this.showLinkForm = showLinkForm;
	}
	public boolean isShowAddLink() {
		return showAddLink;
	}
	public void setShowAddLink(boolean showAddLink) {
		this.showAddLink = showAddLink;
	}
	public boolean isShowAddImage() {
		return showAddImage;
	}
	public void setShowAddImage(boolean showAddImage) {
		this.showAddImage = showAddImage;
	}

	
	
	
}
