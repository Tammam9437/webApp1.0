package vaidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import entity.Email;

@FacesValidator("notExistsEmailValidator")
public class NotExistsEmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Email email = new Email();
		if (!email.emailExsistiert(value.toString())) {
			FacesMessage msg = new FacesMessage("Das eingegebene Email ist exsistiert nicht");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}

	}

}
