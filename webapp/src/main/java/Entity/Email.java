package Entity;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import DBConnector.ConnectToEmailDB;

@ManagedBean
@Entity
@Table(name = "Email")
@SessionScoped
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idemail")
	private int id;

	@Column(name = "name")
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iduser")
	private User user;

	public Email() {
	}

	public boolean emailExsistiert(String mail) {
		List<Email> identicalEmails;
		identicalEmails = ConnectToEmailDB.queryEmail("from Email e where e.name ='" + mail + "'");

		if (identicalEmails.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String email) {
		this.name = email;
	}

}