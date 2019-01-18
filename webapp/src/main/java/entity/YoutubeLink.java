package entity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Table;

@ManagedBean
@Entity
@Table(name = "YoutubeVedio")
@SessionScoped
public class YoutubeLink extends Link{

	public YoutubeLink() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public YoutubeLink(String url, String beschreibung) {
		super(url, beschreibung);
		// TODO Auto-generated constructor stub
	}
	
	public static String getYoutubeVedioId(String url) {
		String idVedio = "";
		
		if (url.contains("?v=")) {
			int beginIdVedio = url.indexOf("?v=");
			int endIdVedio = url.indexOf("&");
			idVedio = url.substring(beginIdVedio + 3, endIdVedio);
		} else if (url.contains("tu.be/")) {
			int beginIdVedio = url.lastIndexOf("tu.be/");
			idVedio = url.substring(beginIdVedio + 6);
		}
		
		return idVedio;
	}
	
	public static String getYoutubeVedioUrl(String url) {
		String http = "https://www.youtube.com/embed/";
		String idVedio = getYoutubeVedioId(url);
		return http+idVedio;
	}
	
	@Override
	public void setUrl(String url) {
		String YoutubeUrl = getYoutubeVedioUrl(url);
		super.setUrl(YoutubeUrl);
	}
	
	public void setyoutubeUrl(String url) {
		super.setUrl(url);
	}
	
	
	

}
