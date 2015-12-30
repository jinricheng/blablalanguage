package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Utils.establishmentDAO;
import Utils.userDAO;
import Models.Establishment;
import Models.User;

/**
 * Servlet implementation class establishmentController
 */
@WebServlet("/userController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   // private static String INSERT_OR_EDIT = "/createEstablishment.jsp";
    private static String LIST = "/listEstablishment.jsp";
    private static String START = "/index.jsp";
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
	

    private userDAO dao;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        
        // TODO Auto-generated constructor stub
    }
    
   
/*    class U(){
    	
    	public int id;
    	public String name;
    	public 
    	
    }*/
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 HttpSession session = request.getSession(true);
		 userDAO dao = new userDAO(session);
     	 User u = (User) session.getAttribute("user"); 
     	 
		 String action = request.getParameter("action");
		 
		 //List<Establishment> lst = new ArrayList<Establishment>();
		 /*
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         response.setContentType("application/json");

         if (action.equals("list")) 
         {
                 try
                 {   
                	 List<Establishment> lst;	 
                 if(u==null){
                     lst = dao.getAll();
                 }
                 else{
                	 lst = dao.getEstablishmentsByOwnerId(u.getId());
                 }
                	 
                 //Return in the format required by jTable plugin
                 JSONROOT.put("Result", "OK");
                 JSONROOT.put("Records", lst);
                 
                 // Convert Java Object to Json
                 String jsonArray = gson.toJson(JSONROOT);
                 System.out.println(jsonArray);

                 response.getWriter().print(jsonArray);
                 
                 }
                 catch(Exception ex){
                         JSONROOT.put("Result", "ERROR");
                         JSONROOT.put("Message", ex.getMessage());
                         String error = gson.toJson(JSONROOT);
                         response.getWriter().print(error);
                 }                               
         }
         if (action.equals("update")) 
         {
                 try
                 {                 	 
	                 
	                 Establishment e = new Establishment();
	                 String t = request.getParameter("id");	                
	                 
	                 e.setId(Integer.parseInt(t));
	                 e.setName(request.getParameter("name"));
	            	 e.setAddress(request.getParameter("address"));
	            	 e.setTelephone(request.getParameter("telephone"));
	            	 e.setPlacesAvailable(Integer.parseInt(request.getParameter("placesAvailable")));
	            	 e.setOwner(u);
	            	 dao.updtEstablishment(e);
	            	 
	            	  JSONROOT.put("Result", "OK");
	                  JSONROOT.put("Record", e);          
	                  
	                  // Convert Java Object to Json
	                  String jsonArray = gson.toJson(JSONROOT);
	                  System.out.println(jsonArray);

	                  response.getWriter().print(jsonArray);
                 }
                 catch(Exception ex){
                         JSONROOT.put("Result", "ERROR");
                         JSONROOT.put("Message", ex.getMessage());
                         String error = gson.toJson(JSONROOT);
                         response.getWriter().print(error);
                 }                               
         }         
         else if (action.equals("delete")) 
         {
                 try
                 {   
                	 
                     Establishment e = new Establishment();
	                 String t = request.getParameter("id");
	                 
	                 e.setId(Integer.parseInt(t));
	              
	            	 dao.delEstablishment(e);
	            	 
	            	  JSONROOT.put("Result", "OK");

	            	  // Convert Java Object to Json
	                  String jsonArray = gson.toJson(JSONROOT);
	                  System.out.println(jsonArray);

	                  response.getWriter().print(jsonArray);
                 
                 }
                 catch(Exception ex){
                         JSONROOT.put("Result", "ERROR");
                         JSONROOT.put("Message", ex.getMessage());
                         String error = gson.toJson(JSONROOT);
                         response.getWriter().print(error);
                 }                               
         }
         */
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(true);
		userDAO dao = new userDAO(session);
		//User u = (User) session.getAttribute("user");
	   
	  
		String action  = request.getParameter("action");
		
		if(action!=null && action != ""){
			doGet(request, response);
		
		}else{	
		   
		   User tmp = new User();
		       
		   tmp.setName(request.getParameter("name"));
		   tmp.setBirthday(request.getParameter("bday"));
		   tmp.setLogin(request.getParameter("em"));
		   tmp.setPass(request.getParameter("pwd"));
		   tmp.setSex("M");
	        
	        dao.addUser(tmp);
	        
	        //saving in the session
	        session.setAttribute("user", dao.getUserByLogin(tmp.getLogin()));
	        
	        RequestDispatcher view = request.getRequestDispatcher(LIST);
	        view.forward(request, response);
	        
		   }
       
	}

}
