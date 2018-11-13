package View;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import Entity.Image;
import Entity.Pdf;
 
@ManagedBean
public class fileUploadView {
     
    private UploadedFile file;
    private Image image;
    private Pdf pdf;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
    	System.out.println(file.getContentType());
        if(file != null) {
        	if(file.getContentType().equals("image/png")) {
        		image = new Image();
        		image.setFile(file.getContents());
            	image.saveInDB();
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + "As Image is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
        	}
        	if(file.getContentType().equals("application/pdf")) {
        		pdf = new Pdf();
        		pdf.setFile(file.getContents());
            	pdf.saveInDB();
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + "As Pdf is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
        	}
        	
        }
    }
}
