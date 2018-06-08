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
		for( CartItem c : cart ){
			Item current = itemDAO.GetById( c.getItemId() );
			c.setItem( current );
		}
		
		request.setAttribute("items", cart );
		request.getRequestDispatcher( "/WEB-INF/cart.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
