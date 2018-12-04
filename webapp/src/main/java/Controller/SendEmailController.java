package Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Entity.Email;

@ManagedBean
@SessionScoped
public class SendEmailController {
	
	private MainController mainController;
	
	private Email email;
	
	public SendEmailController(MainController mainController) {
		this.mainController = mainController;
		this.email = new Email();
	}



	public MainController getMainController() {
		return mainController;
	}



	public Email getEmail() {
		return email;
	}



	public void setEmail(Email email) {
		this.email = email;
	}
	
	
}
