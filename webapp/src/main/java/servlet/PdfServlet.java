package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Pdf;

public class PdfServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pdf pdf;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Get last uploaded Pdf
		try {
			// Pdf bytes
			pdf = new Pdf();
			List<byte[]> allPdfsFromDB = pdf.getAllPdfsFromDB();

            byte[] PdfBytes = allPdfsFromDB.get(allPdfsFromDB.size()-1);

			resp.getOutputStream().write(PdfBytes);
			resp.getOutputStream().close();
			

			

		} catch (Exception e) {
			// Display error message
			resp.getWriter().write(e.getMessage());
			resp.getWriter().close();
		}

	}
}