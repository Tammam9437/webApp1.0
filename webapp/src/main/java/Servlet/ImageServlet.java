package Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBConnector.ConnectToDB;
import Entity.Image;

public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// Get last uploaded image
		try {
			// Image bytes
			image = new Image();
			List<byte[]> allImagesFromDB = image.getAllImagesFromDB();

            byte[] imageBytes = allImagesFromDB.get(allImagesFromDB.size()-1);

			resp.getOutputStream().write(imageBytes);
			resp.getOutputStream().close();
			

			

		} catch (Exception e) {
			// Display error message
			resp.getWriter().write(e.getMessage());
			resp.getWriter().close();
		}

	}
}