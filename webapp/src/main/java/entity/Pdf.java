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

import dbConnector.ConnectToPdfDB;

@ManagedBean
@Entity
@Table(name = "Pdf")
@SessionScoped
public class Pdf implements Comparable<Pdf>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPdf")
	private int idPdf;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "file")
	private byte [] file;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Li.class, mappedBy = "pdf", cascade = CascadeType.ALL)
	private List<Li> likes = new ArrayList<Li>();
	
	public Pdf() {
		name = " ";
	}
	
	public void saveInDB() {
		ConnectToPdfDB.savePdfInDB(this);
	}

	public byte [] getFile() {
		return file;
	}

	public void setFile(byte [] file) {
		this.file = file;
	}
	
	public List<byte[]> getAllPdfsFromDB(){
		List<byte[]> allPdfsFromDB = new ArrayList<byte[]>();
		List<Pdf> pdfs = ConnectToPdfDB.queryPdf("from Pdf");
		for(Pdf pdf : pdfs) {
			allPdfsFromDB.add(pdf.getFile());
		}
		return allPdfsFromDB;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIdPdf() {
		return idPdf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Image [id=" + idPdf + ", file=" + Arrays.toString(file) + "]";
	}

	public List<Li> getLikes() {
		return likes;
	}

	public void setLikes(List<Li> likes) {
		this.likes = likes;
	}

	@Override
	public int compareTo(Pdf anotherPdf) {
		return anotherPdf.getLikes().size() - this.likes.size();
	}
	
}
