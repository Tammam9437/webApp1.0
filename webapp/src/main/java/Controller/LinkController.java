package Controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DBConnector.ConnectToLinkDB;
import Entity.Li;
import Entity.Link;
import Entity.User;

@ManagedBean
@SessionScoped
public class LinkController {

	private MainController mainController;
	private Link link;

	private boolean showLinkForm;
	private boolean showAddLink;
	private boolean showFavouriteLinks;

	public boolean isShowFavouriteLinks() {
		return showFavouriteLinks;
	}

	public void setShowFavouriteLinks(boolean showFavouriteLinks) {
		this.showFavouriteLinks = showFavouriteLinks;
	}

	public LinkController(MainController mainController) {
		this.mainController = mainController;
		this.link = new Link();
		this.showAddLink = false;
		this.showLinkForm = false;
		this.showFavouriteLinks = false;
	}

	public void addLink() {
		Link add = new Link(link.getUrl(), link.getBeschreibung());
		User user = mainController.getUserController().getUser();
		showAddLink = false;
		showLinkForm = true;
		user.getLinks().add(add);
		add.setUser(user);
		ConnectToLinkDB.saveLinkInDB(add);
		link.setUrl("");
		link.setBeschreibung("");
		add.setUser(null);
	}

	public List<Link> getUserLinks() {
		User user = mainController.getUserController().getUser();
		List<Link> list = ConnectToLinkDB.queryLink("From Link WHERE iduser ='" + user.getId() + "'"+" AND DTYPE != 'YoutubeLink'");
		return list;
	}
	

	public void showAddLinkToggel() {
		if (showAddLink) {
			showAddLink = false;
		} else {
			mainController.closeAll();
			showAddLink = true;
		}
	}
	public void showFavouriteLinksToggel() {
		if (showFavouriteLinks) {
			showFavouriteLinks = false;
		} else {
			mainController.closeAll();
			showFavouriteLinks = true;
		}
	}
	
	public void saveLike() {
		Li like = new Li();
		like.saveInDB();
	}

	public void showLinksToggel() {
		if (showLinkForm) {
			showLinkForm = false;
		} else {
			mainController.closeAll();
			showLinkForm = true;
		}
	}

	public MainController getMainController() {
		return mainController;
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

}