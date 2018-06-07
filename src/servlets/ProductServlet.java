package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CategoryDAO;
import db.ItemDAO;
import db.ManufacturerDAO;
import models.Category;
import models.Manufacturer;
import models.Item;

/**
 * Servlet implementation class ProductsServlet
 */
@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDAO _itemDAO;
	private CategoryDAO _categoryDAO;
	private ManufacturerDAO _manufacturerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
       
        _itemDAO = new ItemDAO();
        _categoryDAO = new CategoryDAO();
        _manufacturerDAO = new ManufacturerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String itemIdString = request.getParameter( "id" );
		int itemId;
		
		try {
			itemId = Integer.parseInt( itemIdString );
		} catch( NumberFormatException nfe ){
			itemId = -1;
		}
		
		// if no item
		if( itemId == -1 )
			response.sendRedirect( request.getContextPath() + "/Products" );
		
		Item item = _itemDAO.GetById( itemId );
		
		// if id is invalid
		if( item == null )
			response.sendRedirect( request.getContextPath() + "/Products" );
		
		request.setAttribute( "item", item );
		request.getRequestDispatcher( "/WEB-INF/products.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
