package entity;
import java.util.ArrayList;
import java.util.Arrays;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dbConnector.ConnectToImageDB;
import dbConnector.ConnectToLikeDB;

@ManagedBean
@Entity
@Table(name = "Image")
@SessionScoped
public class Image implements Comparable<Image>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idImage")
	private int idImage;
	
	@Column(name = "file")
	private byte [] file;
	
	@Column(name = "name")
	private String name;
		
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idcategory")
	private Category category;
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Li.class, mappedBy = "image", cascade = CascadeType.ALL)
	private List<Li> likes = new ArrayList<Li>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity = Comment.class, mappedBy = "image", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();
	
	
	
	public Image() {
		
	}
	
	public void saveInDB() {
		ConnectToImageDB.saveImageInDB(this);
	}
	
	public List<byte[]> getAllImagesFromDB(){
		List<byte[]> allImagesFromDB = new ArrayList<byte[]>();
		List<Image> images = ConnectToImageDB.queryImage("from Image");
		for(Image image : images) {
			allImagesFromDB.add(image.getFile());
		}
		return allImagesFromDB;
	}
	
	public boolean isUserEnteredLike(User user) {
		List<Li> userLinkLikes = ConnectToLikeDB.getUserImageLikes(user, this);
		if (userLinkLikes.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void addLike(User user) {
		Li like = new Li();
		like.setImage(this);
		like.setUser(user);
		likes.add(like);
		ConnectToLikeDB.saveLikeInDB(like);
	}
	

	public byte [] getFile() {
		return file;
	}

	public void setFile(byte [] file) {
		this.file = file;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIdImage() {
		return idImage;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Li> getLikes() {
		return likes;
	}

	public void setLikes(List<Li> likes) {
		this.likes = likes;
	}
	@Override
	public int compareTo(Image anotherImage) {
		return anotherImage.getLikes().size() - this.likes.size();
	}

	public String getName() {
		return name;
	}
	public int getLikesNumber() {
		return this.likes.size();
	}
	
	public int getcommentsNumber() {
		return this.comments.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Image [id=" + idImage + ", file=" + Arrays.toString(file) + "]";
	}


	
	
}
