
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class ImageServlet extends HttpServlet {
 
    private static final long serialVersionUID = -6449908958106497977L;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get last uploaded image
        try {
            // Image bytes
        	Image image =new Image();
            byte[] imageBytes = null;
            
            imageBytes = image.getImage();
             
            resp.getOutputStream().write(imageBytes);
            resp.getOutputStream().close();
             
        } catch (Exception e) {
            // Display error message
            resp.getWriter().write(e.getMessage());
            resp.getWriter().close();
        }
         
    }   
}