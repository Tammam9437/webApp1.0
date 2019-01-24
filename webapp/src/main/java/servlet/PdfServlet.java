package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbConnector.ConnectToPdfDB;
import dbConnector.ConnectToUserDB;
import entity.Pdf;
import entity.User;

public class PdfServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("id");
		// Get last uploaded Pdf
		try {
			User user = ConnectToUserDB.getUserFromDB(Integer.parseInt(userId));
			List<Pdf> userPdfs = ConnectToPdfDB.getUserPdfsFromDB(user);
			byte[] pdfBytes = userPdfs.get(userPdfs.size()-1).getFile();
			resp.getOutputStream().write(pdfBytes);
			resp.getOutputStream().close();
		} catch (Exception e) {
			// Display error message
			resp.getWriter().write(e.getMessage());
			resp.getWriter().close();
		}

	}
}