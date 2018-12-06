package streamer;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import dbConnector.ConnectToImageDB;
import entity.Image;

@ManagedBean
@ApplicationScoped
public class ImageStreamer {

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the HTML. Return a stub StreamedContent so that it will
			// generate right URL.
			return new DefaultStreamedContent();
		} else {
			// So, browser is requesting the image. Return a real StreamedContent with the
			// image bytes.
			String imageId = context.getExternalContext().getRequestParameterMap().get("imageId");
			System.out.println(imageId);
			Image image = ConnectToImageDB.getImageFromDB(Integer.parseInt(imageId));
			return new DefaultStreamedContent(new ByteArrayInputStream(image.getFile()));
		}
	}

}