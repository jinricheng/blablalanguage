package Servlets;

import java.io.IOException;
import Utils.userDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private userDAO u;
    private static String HOME_LOGGED = "listEstablishment.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        
        // TODO Auto-generated constructor stub

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String userName = request.getParameter("email");
		  String pass = request.getParameter("pwd");
		  HttpSession session = request.getSession(true);
		  userDAO u = new  userDAO(session); 
		  
	      if(u.existUser(userName, pass)){
	    	  response.setContentType("text/plain");
		      response.getWriter().write(String.format("{\"result\":%s,\"name\":\"%s\"}", "true","Welcome"));
	    	  
	      }else{
	    	  request.getSession().setAttribute("username","");
	    	  //response.setContentType("application/json");	   
	    	  response.setContentType("text/plain");
		      response.getWriter().write(String.format("{\"result\":%s,\"name\":\"%s\"}", "false","Login or Pass wrong!"));
	      }
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  HttpSession session = request.getSession(true);
		  userDAO u = new  userDAO(session); 
		  
		  String userName = request.getParameter("email");
		  String pass = request.getParameter("pwd");
		  
	      if(u.existUser(userName, pass)){	    	  
	    	  session.setAttribute("user",u.getUserByLogin(userName));
	    	  RequestDispatcher view = request.getRequestDispatcher(HOME_LOGGED);		        
		      view.forward(request, response);	    	  
	      }
	}

	
	protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  HttpSession session = request.getSession(true);
		  
		  session.setAttribute("user",null);
	      RequestDispatcher view = request.getRequestDispatcher(HOME_LOGGED);		        
		  view.forward(request, response);	    	  
	    
	}
	
}
