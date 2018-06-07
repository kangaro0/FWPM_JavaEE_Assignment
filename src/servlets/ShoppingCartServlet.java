package servlets;

import java.awt.CardLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//TODO get userId from Session
		int userId = 2;
		
		//get all Item Objects in the Cart
		ArrayList<CartItem> cart = cartDAO.GetByUserId(userId);
		ArrayList<Item> items = itemDAO.GetByUserCart(cart);
		
		//set Item fields
		for(Item i : items){
			Description desc = descDAO.GetById(i.getDescriptionId());
			Manufacturer man = manDAO.GetById(i.getManufacturerId());
			i.setDescription(desc);
			i.setManufacturer(man);
		}
		request.setAttribute("items", items);

		
		request.getRequestDispatcher( "/WEB-INF/cart.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
