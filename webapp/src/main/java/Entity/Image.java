package Entity;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
	public ArrayList<Image> getAllImages(){
		ArrayList<Image> images = (ArrayList<Image>) ConnectToImageDB.queryImage("from Image");
		
		return images;
	}
	
	public Image getoneimage() {
		ArrayList<Image> imgs = getAllImages();
		return imgs.get(1);
	}
	
	public StreamedContent getImage() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the HTML. Return a stub StreamedContent so that it will
			// generate right URL.
			return new DefaultStreamedContent();
		} else {
					return new DefaultStreamedContent(new ByteArrayInputStream(file));
		}
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
