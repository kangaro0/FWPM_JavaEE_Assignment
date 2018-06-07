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
import db.ItemDAO;
import models.CartItem;
import models.Item;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCart")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO get userId from Session
		int userId = 2;
		CartDAO cartDAO = new CartDAO();
		ItemDAO itemDAO = new ItemDAO();
		ArrayList<CartItem> cart = cartDAO.GetByUserId(userId);
		ArrayList<Item> items = itemDAO.GetByUserCart(cart);
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
