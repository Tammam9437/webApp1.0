package Entity;
import java.util.ArrayList;
import java.util.Arrays;
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

import DBConnector.ConnectToImageDB;

@ManagedBean
@Entity
@Table(name = "Image")
@SessionScoped
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idImage")
	private int idImage;
	
	@Column(name = "file")
	private byte [] file;
		
	
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;
	
	public Image() {
		
	}
	
	public void saveInDB() {
		ConnectToImageDB.saveImageInDB(this);
	}
	

	public byte [] getFile() {
		return file;
	}

	public void setFile(byte [] file) {
		this.file = file;
	}

	
	public List<byte[]> getAllImagesFromDB(){
		List<byte[]> allImagesFromDB = new ArrayList<byte[]>();
		List<Image> images = ConnectToImageDB.queryImage("from Image");
		for(Image image : images) {
			allImagesFromDB.add(image.getFile());
		}
		return allImagesFromDB;
	}
	public List<Image> getAllImages(){
		List<Image> images = ConnectToImageDB.queryImage("from Image");
		
		return images;
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

	@Override
	public String toString() {
		return "Image [id=" + idImage + ", file=" + Arrays.toString(file) + "]";
	}
	
	
}
