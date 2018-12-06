package entity;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@ManagedBean
@Entity
@Table(name = "Email")
@SessionScoped
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmail")
	private int idEmail;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(targetEntity = Link.class, mappedBy = "category", cascade = CascadeType.ALL)
	private List<Link> links = new ArrayList<Link>();

	@OneToMany(targetEntity = Image.class, mappedBy = "category", cascade = CascadeType.ALL)
	private List<Image> images = new ArrayList<Image>();

	@OneToMany(targetEntity = Pdf.class, mappedBy = "category", cascade = CascadeType.ALL)
	private List<Pdf> pdfs = new ArrayList<Pdf>();
	
	public Category() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Pdf> getPdfs() {
		return pdfs;
	}

	public void setPdfs(List<Pdf> pdfs) {
		this.pdfs = pdfs;
	}

	public int getIdEmail() {
		return idEmail;
	}
	
	
}
