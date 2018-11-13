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
import javax.persistence.Table;

import DBConnector.ConnectToDB;

@ManagedBean
@Entity
@Table(name = "Image")
@SessionScoped
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "file")
	private byte [] file;
	
	public Image() {
		
	}
	
	public void saveInDB() {
		ConnectToDB.saveImageInDB(this);
	}

	public byte [] getFile() {
		return file;
	}

	public void setFile(byte [] file) {
		this.file = file;
	}

//	public byte[] getImage() {
//		return ConnectToDB.getImageFromDB(2).getFile();	
//	}
	
	public List<byte[]> getAllImagesFromDB(){
		List<byte[]> allImagesFromDB = new ArrayList<byte[]>();
		List<Image> images = ConnectToDB.queryImage("from Image");
		for(Image image : images) {
			allImagesFromDB.add(image.getFile());
		}
		return allImagesFromDB;
	}


	@Override
	public String toString() {
		return "Image [id=" + id + ", file=" + Arrays.toString(file) + "]";
	}
	
}
