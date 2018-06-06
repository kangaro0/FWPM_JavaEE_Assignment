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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO _userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        
        _userDAO = new UserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher( "/WEB-INF/register.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String username = request.getParameter( "username" );
		String firstName = request.getParameter( "firstname" );
		String lastName = request.getParameter( "lastname" );
		String address = request.getParameter( "address" );
		String city = request.getParameter( "city" );
		String postCode = request.getParameter( "postCode" );
		String password = request.getParameter( "password" );
		
		// encrypt password
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encrypted = passwordEncryptor.encryptPassword( password );
		
		// check if user exists already
		User existing = _userDAO.GetByUserName( username );
		if( existing == null ){
			// if not -> create User
			User newUser = new User( 0, username, firstName, lastName, address, city, Integer.parseInt( postCode ), encrypted );
			_userDAO.Create( newUser );
			
			// Send to login page
			response.sendRedirect( request.getContextPath() + "/Login" );
			
		} else {
			session.setAttribute( "Error" , "User already exists." );
		}
		
		doGet(request, response);
	}

}
