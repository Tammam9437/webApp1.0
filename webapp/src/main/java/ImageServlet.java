
import java.io.IOException;
import java.util.List;

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
			Image image = new Image();
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