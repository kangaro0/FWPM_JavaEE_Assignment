package servlets;

import java.awt.CardLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.CartDAO;
import db.DescriptionDAO;
import db.ItemDAO;
import db.ManufacturerDAO;
import models.CartItem;
import models.Description;
import models.Item;
import models.Manufacturer;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCart")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static CartDAO cartDAO;
	private static ItemDAO itemDAO;
	private static DescriptionDAO descDAO;
	private static ManufacturerDAO manDAO;
	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet() {
        super();
        cartDAO = new CartDAO();
		itemDAO = new ItemDAO();
		descDAO = new DescriptionDAO();
		manDAO = new ManufacturerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		//get all Item Objects in the Cart
		ArrayList<CartItem> cart = cartDAO.GetByUserId((int) session.getAttribute("userid"));
		
		// fill items
		float grandTotal = 0;
		for( CartItem c : cart ){
			Item current = itemDAO.GetById( c.getItemId() );
			current.setManufacturer( manDAO.GetById( current.getManufacturerId() ) );
			c.setItem( current );
			
			grandTotal += ( c.getQuantity() * current.getPrize() );
		}
		
		request.setAttribute( "grandtotal", grandTotal );
		request.setAttribute("items", cart );
		request.getRequestDispatcher( "/WEB-INF/cart.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String origUri = (String) request.getAttribute("javax.servlet.forward.request_uri");
		System.out.println( origUri );
		
		String itemIdString = request.getParameter( "itemid" );
		String quantityString = request.getParameter( "quantity" );
		int userId = (int) session.getAttribute( "userid" );
		
		int itemId;
		try {
			itemId = Integer.parseInt( itemIdString );
		} catch( NumberFormatException nfe ){
			itemId = -1;
		}
		
		int quantity;
		try {
			quantity = Integer.parseInt( quantityString );
		} catch( NumberFormatException nfe ){
			quantity = -1;
		}
		
		if( itemId == -1 && quantityString == null )
			// nothing to do
			response.sendRedirect( origUri );
		else if( itemId > 0 && quantity == -1 ){
			// add to cart
			ArrayList<CartItem> items = cartDAO.GetAll();
			Item toAdd = itemDAO.GetById( itemId );
			cartDAO.Add( toAdd, userId);
		} else if( itemId > 0 && quantity == 0 ){
			// remove
			CartItem c = cartDAO.GetByUserIdAndItemId( userId, itemId );
			cartDAO.Delete( c );
		} else {
			// update quantity
			CartItem c = cartDAO.GetByUserIdAndItemId( userId, itemId );
			c.setQuantity( quantity );
			cartDAO.Update( c );
		}
		
		doGet( request, response );
	}

}
