package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dbConnector.ConnectToLinkDB;
import entity.Link;
import entity.User;
import entity.YoutubeLink;

@ManagedBean
@SessionScoped
public class VideoController {
	
	private MainController mainController;
	
	private YoutubeLink youtubeLink;
	
	private boolean showAddYoutubeVideo;
	
	private boolean showYoutubeVideos;
	
	
	public VideoController(MainController mainController) {
		this.mainController = mainController;
		this.youtubeLink = new YoutubeLink();
		this.showYoutubeVideos = false;
		this.showAddYoutubeVideo = false;
	}
	
	public void showYoutubeVideosToggel() {
		if (showYoutubeVideos) {
			showYoutubeVideos = false;
		} else {
			mainController.closeAll();
			showYoutubeVideos = true;
		}
	}
	
	public void showAddYoutubeVideoToggel() {
		if (showAddYoutubeVideo) {
			showAddYoutubeVideo = false;
		} else {
			mainController.closeAll();
			showAddYoutubeVideo = true;
		}
	}
	
	public void addYoutubeVideo() {
		YoutubeLink add = new YoutubeLink(youtubeLink.getUrl(), youtubeLink.getBeschreibung());
		User user = mainController.getUserController().getUser();
		showAddYoutubeVideo = false;
		showYoutubeVideos = true;
		user.getLinks().add(add);
		add.setUser(user);
		ConnectToLinkDB.saveLinkInDB(add);
		youtubeLink.setUrl("");
		youtubeLink.setBeschreibung("");
		add.setUser(null);
	}
	
	public List<Link> getUserYoutubeLinks() {
		User user = mainController.getUserController().getUser();
		List<Link> list = ConnectToLinkDB.queryLink("From Link WHERE iduser ='" + user.getId() + "'"+" AND DTYPE = 'YoutubeLink'");
		return list;
	}
	
	public List<Link> getYoutubeLinks(){
		List<Link> list = ConnectToLinkDB.queryLink("From Link WHERE DTYPE = 'YoutubeLink'");
		return list;
	}

	public boolean isShowYoutubeVideos() {
		return showYoutubeVideos;
	}

	public void setShowYoutubeVideos(boolean showYoutubeVideos) {
		this.showYoutubeVideos = showYoutubeVideos;
	}

	public MainController getMainController() {
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public YoutubeLink getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(YoutubeLink youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public boolean isShowAddYoutubeVideok() {
		return showAddYoutubeVideo;
	}

	public void setShowAddYoutubeVideo(boolean showAddYoutubeVideo) {
		this.showAddYoutubeVideo= showAddYoutubeVideo;
	}

	public boolean isShowAddYoutubeVideo() {
		return showAddYoutubeVideo;
	}

}
