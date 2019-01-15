package controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import view.FileUploadView;

@ManagedBean
@ApplicationScoped
public class MainController {
	private UserController userController;
	private LikeController likeController;
	private LinkController linkController;
	private FileUploadView fileUploadView;
	private VideoController videoController;
	private UploadController uploadController;
	private ImageController imageController;
	private SendEmailController sendEmailController;
	private CategoryController categoryController;
	private FilterController filterController;
	private CommentController commentController;

	public MainController() {
		this.userController = new UserController(this);
		this.likeController = new LikeController(this);
		this.linkController = new LinkController(this);
		this.fileUploadView = new FileUploadView(this);
		this.videoController = new VideoController(this);
		this.uploadController = new UploadController(this);
		this.imageController = new ImageController(this);
		this.sendEmailController = new SendEmailController(this);
		this.categoryController = new CategoryController(this);
		this.filterController = new FilterController(this);
		this.commentController = new CommentController(this);
	}
	
	public void closeAll(){
		imageController.setShowImages(false);
		imageController.setShowUserImages(false);
		linkController.setShowAddLink(false);
		linkController.setShowFavouriteLinks(false);
		linkController.setShowLinkForm(false);
		uploadController.setShowUpload(false);
		videoController.setShowAddYoutubeVideo(false);
		videoController.setShowYoutubeVideos(false);
		uploadController.setShowPdfs(false);
	}
	

	public SendEmailController getSendEmailController() {
		return sendEmailController;
	}

	public void setSendEmailController(SendEmailController sendEmailController) {
		this.sendEmailController = sendEmailController;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public LikeController getLikeController() {
		return likeController;
	}

	public void setLikeController(LikeController likeController) {
		this.likeController = likeController;
	}

	public LinkController getLinkController() {
		return linkController;
	}

	public void setLinkController(LinkController linkController) {
		this.linkController = linkController;
	}

	public FileUploadView getFileUploadView() {
		return fileUploadView;
	}

	public void setFileUploadView(FileUploadView fileUploadView) {
		this.fileUploadView = fileUploadView;
	}

	public UploadController getUploadController() {
		return uploadController;
	}

	public void setUploadController(UploadController uploadController) {
		this.uploadController = uploadController;
	}

	public VideoController getVideoController() {
		return videoController;
	}

	public void setVideoController(VideoController videoController) {
		this.videoController = videoController;
	}

	public ImageController getImageController() {
		return imageController;
	}

	public void setImageController(ImageController imageController) {
		this.imageController = imageController;
	}

	public CategoryController getCategoryController() {
		return categoryController;
	}

	public void setCategoryController(CategoryController categoryController) {
		this.categoryController = categoryController;
	}

	public FilterController getFilterController() {
		return filterController;
	}

	public void setFilterController(FilterController filterController) {
		this.filterController = filterController;
	}

	public CommentController getCommentController() {
		return commentController;
	}

	public void setCommentController(CommentController commentController) {
		this.commentController = commentController;
	}

}
