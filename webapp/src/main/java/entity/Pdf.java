package entity;
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

import dbConnector.ConnectToPdfDB;

@ManagedBean
@Entity
@Table(name = "Pdf")
@SessionScoped
public class Pdf {
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
	
	public Pdf() {
		name = "";
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

	@Override
	public String toString() {
		return "Image [id=" + idPdf + ", file=" + Arrays.toString(file) + "]";
	}
	
}