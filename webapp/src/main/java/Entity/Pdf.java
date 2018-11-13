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
@Table(name = "Pdf")
@SessionScoped
public class Pdf {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPdf")
	private int idPdf;
	
	@Column(name = "file")
	private byte [] file;
	
	public Pdf() {
		
	}
	
	public void saveInDB() {
		ConnectToDB.savePdfInDB(this);
	}

	public byte [] getFile() {
		return file;
	}

	public void setFile(byte [] file) {
		this.file = file;
	}
	
	public List<byte[]> getAllPdfsFromDB(){
		List<byte[]> allPdfsFromDB = new ArrayList<byte[]>();
		List<Pdf> pdfs = ConnectToDB.queryPdf("from Pdf");
		for(Pdf pdf : pdfs) {
			allPdfsFromDB.add(pdf.getFile());
		}
		return allPdfsFromDB;
	}


	@Override
	public String toString() {
		return "Image [id=" + idPdf + ", file=" + Arrays.toString(file) + "]";
	}
	
}
