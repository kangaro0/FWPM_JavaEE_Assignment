package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(filterName="AuthenticationFilter", urlPatterns = { "/ShoppingCart"})
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        
    	// register password encryption class
        try {
        	Class.forName( "org.jasypt.util.password.StrongPasswordEncryptor" );
        } catch( ClassNotFoundException cnfe ){
        	cnfe.printStackTrace();
        }
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = ( HttpServletRequest ) request;
		HttpServletResponse res = ( HttpServletResponse ) response;
		HttpSession session = req.getSession();
		
		String loginUri = req.getContextPath() + "/Login";
		String destinationUri = req.getRequestURI();
		
		boolean loggedIn = session != null && session.getAttribute( "loggedIn" ) != null;
		boolean loginRequest = req.getRequestURI().equals( loginUri );
		
		if( loggedIn || loginRequest )
			chain.doFilter( request, response );
		else {
			session.setAttribute( "destinationUri", destinationUri );
			res.sendRedirect( loginUri );
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
