package view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import controller.MainController;
import entity.Image;
import entity.Pdf;

@ManagedBean
@SessionScoped
public class FileUploadView {

	private MainController mainController;

	private UploadedFile file;
	private Image image;
	private Pdf pdf;
	private String fileName;
	private boolean imageFile;
	private boolean pdfFile;

	public FileUploadView(MainController mainController) {
		this.mainController = mainController;
		imageFile = false;
		pdfFile = false;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void allBooleanValueFalse() {
		imageFile = false;
		pdfFile = false;
	}

	public void upload() {
		String fileType;
		if (file != null) {
			fileType = file.getContentType();
			if (fileType.equals("image/png") || fileType.equals("image/jpeg")) {
				image = new Image();
				image.setFile(file.getContents());
				image.setUser(mainController.getUserController().getUser());
				image.setCategory(mainController.getCategoryController().getCategory());
				image.setName(fileName);
				image.saveInDB();
				allBooleanValueFalse();
				fileName = "";
				mainController.getCategoryController().setCategory(null);
				imageFile = true;
				FacesMessage message = new FacesMessage("Succesful", file.getFileName() + "As Image is uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			if (fileType.equals("application/pdf")) {
				pdf = new Pdf();
				pdf.setFile(file.getContents());
				pdf.setUser(mainController.getUserController().getUser());
				pdf.setCategory(mainController.getCategoryController().getCategory());
				pdf.setName(fileName);
				pdf.saveInDB();
				allBooleanValueFalse();
				pdfFile = true;
				fileName = "";
				mainController.getCategoryController().setCategory(null);
				FacesMessage message = new FacesMessage("Succesful", file.getFileName() + "As Pdf is uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	public boolean isImageFile() {
		return imageFile;
	}

	public void setImageFile(boolean imageFile) {
		this.imageFile = imageFile;
	}

	public boolean isPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(boolean pdfFile) {
		this.pdfFile = pdfFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
