package Vaidator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import Entity.User;

@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {
	User user = new User();
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String username = value.toString();
		if(user.userExsistiert(username)) {
			FacesMessage msg =
		              new FacesMessage("Username ist bereits exsistiert");
		      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		    
		      throw new ValidatorException(msg);	
		}
	}

}
