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
		try {
				User user = ConnectToUserDB.getUserFromDB(Integer.parseInt(userId));
				List<Image> userImages = ConnectToImageDB.getUserImagesFromDB(user);
				byte[] imageBytes = userImages.get(userImages.size()-1).getFile();
				resp.getOutputStream().write(imageBytes);
				resp.getOutputStream().close();

		} catch (Exception e) {
			// Display error message
			resp.getWriter().write(e.getMessage());
			resp.getWriter().close();
		}

	}
}