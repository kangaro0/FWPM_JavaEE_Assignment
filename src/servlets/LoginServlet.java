package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.StrongPasswordEncryptor;

import db.UserDAO;
import models.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO _userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        _userDAO = new UserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getRequestDispatcher( "/WEB-INF/login.jsp" ).forward( request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter( "username" );
		String password = request.getParameter( "password" );
		
		User current = _userDAO.GetByUserName( username );
		
		HttpSession session = request.getSession();
		// check if user exists && password is correct
		if( current != null && current.getPassword().equals( password ) ){
			session.setAttribute( "loggedIn", true );
			session.setAttribute( "userId", current.getId() );
			
			// Check if user wanted to navigate to protected page before login
			Object destinationUri = session.getAttribute( "destinationUri" );
			if( destinationUri == null ){
				// if not, send to "Home"
				response.sendRedirect( request.getContextPath() );
				return;
			}
			else {
				session.removeAttribute( "destinationUri" );
				response.sendRedirect( ( String ) destinationUri );
				return;
			}
		} else {
			session.setAttribute( "Error", "Login failed!" );
		}
		
		doGet(request, response);
	}

}
