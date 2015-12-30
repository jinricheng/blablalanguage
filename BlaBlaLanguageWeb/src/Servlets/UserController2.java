package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Models.User;
import Utils.CrudUser;


/**
 * Servlet implementation class User
 */
@WebServlet(description = "Class user to manage users", urlPatterns = { "/Users" })
public class UserController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String servletName = "name";
    private static final String servletType = "type";
    private static final String servletLogin = "login";
    private static final String servletPass = "pass";
    private static final String servletSex = "sex";
    private static final String servletBirthday = "birthday";

    private static final String databaseName = "public";
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/listUsers.jsp";
    private CrudUser crud;

	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController2() {
        super();
        crud = new CrudUser();
        // TODO Auto-generated constructor stub
    }
    
    


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
       
        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            crud.deleteUser(userId, request.getSession());
            forward = LIST_USER;
            request.getSession().setAttribute("users", crud.getAllUsers(request.getSession()));    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = crud.getUserById(userId);
            request.getSession().setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listAllUser")){
            forward = LIST_USER;
            request.getSession().setAttribute("users", crud.getAllUsers(request.getSession()));
        } else {
        	request.getSession().setAttribute("user", new User());
            forward = INSERT_OR_EDIT;
        }

        ServletContext context = getServletContext();
        
        RequestDispatcher view = context.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        //user.setBirthday(request.getParameter(servletBirthday));
        user.setLogin(request.getParameter(servletLogin));
        user.setName(request.getParameter(servletName));
        user.setPass(request.getParameter(servletPass));
        user.setSex(request.getParameter(servletSex));
        
        
        //user.setUserType(Integer.parseInt(request.getParameter(servletType)));
        String userid = request.getParameter("id");
        if(userid == null || userid.isEmpty() || userid.equals("0"))
        {
           crud.addUser(user, request.getSession());
        }else{
        	user.setId(Integer.parseInt(userid));
        	crud.updateUser(user, request.getSession());
        }
        ServletContext context = getServletContext();
      
        RequestDispatcher view = context.getRequestDispatcher(LIST_USER);
        request.getSession().setAttribute("users", crud.getAllUsers(request.getSession()));
        view.forward(request, response);
    }
}
    