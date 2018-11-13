package Servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = -6449908958106497977L;
	private boolean isMultipart;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//New Code
		isMultipart = ServletFileUpload.isMultipartContent(req);
		ServletOutputStream out = resp.getOutputStream();
		 ServletFileUpload upload = new ServletFileUpload();
		 
		  if( !isMultipart ) {
		         out.println("<html>");
		         out.println("<head>");
		         out.println("<title>Servlet upload</title>");  
		         out.println("</head>");
		         out.println("<body>");
		         out.println("<p>No file uploaded</p>"); 
		         out.println("</body>");
		         out.println("</html>");
		         return;
		      }
		try {
			List<FileItem> fileItems = upload.parseRequest(req);
			Iterator<FileItem> iter = fileItems.iterator();
			out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         while ( iter.hasNext () ) {
	             FileItem fi = (FileItem)iter.next();
	             if ( !fi.isFormField () ) {
	                // Get the uploaded file parameters
	                String fieldName = fi.getFieldName();
	                String fileName = fi.getName();
	                String contentType = fi.getContentType();
	                System.out.println(contentType);
	               
	                out.println("Uploaded Filename: " + fileName + "<br>");
	             }
	          }
	          out.println("</body>");
	          out.println("</html>");
	         
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//************
		// Get last uploaded image
//		try {
//			// Image bytes
//			Image image = new Image();
//			List<byte[]> allImagesFromDB = image.getAllImagesFromDB();
//
//            byte[] imageBytes = allImagesFromDB.get(allImagesFromDB.size()-1);
//
//			resp.getOutputStream().write(imageBytes);
//			resp.getOutputStream().close();
//			
//
//			
//
//		} catch (Exception e) {
//			// Display error message
//			resp.getWriter().write(e.getMessage());
//			resp.getWriter().close();
//		}

	}
}