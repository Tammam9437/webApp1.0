package Controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DBConnector.ConnectToLinkDB;
import Entity.Link;
import Entity.User;
import View.FileUploadView;

@ManagedBean
@SessionScoped
public class UserController2 {

	private User user;
	private Link link;
	FileUploadView fileUploadView;
	//Added Link
	private boolean showLinkForm;
	private boolean showAddLink;
	//**************
	//Added Upload
	private boolean showUpload;

	public UserController2() {
//		this.fileUploadView = new FileUploadView(this);
		this.user = new User();
		this.link = new Link();
		this.showAddLink = false;
		this.showLinkForm = false;
		this.showUpload = false;
	}

	// Added
	public void addLink() {
		Link add = new Link(link.getUrl(), link.getBeschreibung());
		showAddLink = false;
		showLinkForm = true;
		add.setUser(user);
		user.getLinks().add(add);
		ConnectToLinkDB.saveLinkInDB(add);
		link.setUrl("");
		link.setBeschreibung("");
	}
	//Add Upload
	public void showUploadToggel() {
		if (showUpload) {
			showUpload = false;
		} else {
			showUpload = true;
		}
	}
	//Added Upload
	public String onClickImage() {
		return "http://localhost:8080/webapp/image";
	}
	//Added Link
	public List<Link> getUserLinks() {
		List<Link> list = ConnectToLinkDB.queryLink("From Link WHERE iduser ='" + user.getId() + "'");
		return list;
	}
	//Add Link
	public void showAddLinkToggel() {
		if (showAddLink) {
			showAddLink = false;
		} else {
			showAddLink = true;
		}
	}
	//Added Link
	public void showLinksToggel() {
		if (showLinkForm) {
			showLinkForm = false;
		} else {
			showLinkForm = true;
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

	public boolean isshowUpload() {
		return showUpload;
	}

	public void setshowUpload(boolean showUpload) {
		this.showUpload = showUpload;
	}

	public FileUploadView getFileUploadView() {
		return fileUploadView;
	}

	public void setFileUploadView(FileUploadView fileUploadView) {
		this.fileUploadView = fileUploadView;
	}

	public boolean isShowUpload() {
		return showUpload;
	}

	public void setShowUpload(boolean showUpload) {
		this.showUpload = showUpload;
	}

}
