package vaidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.model.UploadedFile;

import entity.User;

@FacesValidator("fileUploadValidator")
public class FileUploadValidator implements Validator {
	User user = new User();

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if (value != null) {
			UploadedFile file = (UploadedFile) value;
			String fileType = file.getContentType();
			System.out.println(fileType);
			if (!fileType.equals("image/png") && !fileType.equals("image/jpeg")
					&& !fileType.equals("application/pdf")) {
				FacesMessage msg = new FacesMessage("Der Datei muss entweder einen Pdf oder eine Image sein");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);

				throw new ValidatorException(msg);

			}

		}
	}

}
