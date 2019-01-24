package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbConnector.ConnectToImageDB;
import dbConnector.ConnectToUserDB;
import entity.Image;
import entity.User;

public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("id");
		byte[] imageBytes;
		if (userId != null) {
			User user = ConnectToUserDB.getUserFromDB(Integer.parseInt(userId));
			List<Image> userImages = ConnectToImageDB.getUserImagesFromDB(user);
			imageBytes = userImages.get(userImages.size() - 1).getFile();
		} else {
			String idImage = req.getParameter("idImage");
			Image image = ConnectToImageDB.getImageFromDB(Integer.parseInt(idImage));
			imageBytes = image.getFile();
		}
		try {
			resp.getOutputStream().write(imageBytes);
			resp.getOutputStream().close();

		} catch (Exception e) {
			// Display error message
			resp.getWriter().write(e.getMessage());
			resp.getWriter().close();
		}

	}
}