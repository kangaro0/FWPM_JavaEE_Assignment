package servlets.data;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ImageDAO;
import models.Image;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ImageDAO _imageDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        _imageDAO = new ImageDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String imageIdString = request.getParameter( "id" );
		int imageId;
		
		try {
			imageId = Integer.parseInt( imageIdString );
		} catch( NumberFormatException nfe ){
			imageId = -1;
		}
		
		Image img = _imageDAO.GetById( imageId );
		
		byte[] byteData = new byte[0];
		
		try {
			
			Blob data = img.getData();
			int length = ( int )data.length();
			
			 byteData = data.getBytes( 1, length );
			 
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		} catch( NullPointerException npe ){
			npe.printStackTrace();
		}
		
		response.getOutputStream().write( byteData );
		response.setContentType( "image/jpeg" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
