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
@WebServlet("/Products")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDAO _itemDAO;
	private CategoryDAO _categoryDAO;
	private ManufacturerDAO _manufacturerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsServlet() {
        super();
       
        _itemDAO = new ItemDAO();
        _categoryDAO = new CategoryDAO();
        _manufacturerDAO = new ManufacturerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get possible filter
		ArrayList<Category> categories = _categoryDAO.GetAll();
		ArrayList<Manufacturer> manufacturers = _manufacturerDAO.GetAll();
		
		// push to session
		request.setAttribute( "categories", categories );
		request.setAttribute( "manufacturers", manufacturers );
		
		// apply filter
		String categoryString = request.getParameter( "category" );
		String manufacturerString = request.getParameter( "manufacturer" );
		
		// validate
		int categoryId;
		try {
			categoryId = Integer.parseInt( categoryString );
		} catch( NumberFormatException nfe ){
			categoryId = -1;
		}
		
		int manufacturerId;
		try {
			manufacturerId = Integer.parseInt( manufacturerString );
		} catch( NumberFormatException nfe ){
			manufacturerId = -1;
		}
		
		// get data
		ArrayList<Item> items;
		if( categoryId == -1 && manufacturerId == -1 ){
			items = _itemDAO.GetAll();
		} else if( categoryId == -1 ){
			items = _itemDAO.GetByManufacturerId( manufacturerId );
		} else if( manufacturerId == -1 ){
			items = _itemDAO.GetByCategoryId( categoryId );
		} else {
			items = ItemDAO.Merge( _itemDAO.GetByCategoryId( categoryId ), _itemDAO.GetByManufacturerId( manufacturerId ) );
		}
		
		request.setAttribute( "items", items );
		request.getRequestDispatcher( "/WEB-INF/products.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
