package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Event;
import Utils.CrudEvent;

/**
 * Servlet implementation class EventController
 */
@WebServlet("/Event")
public class EventController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String servletEstb = "establishment";
    private static final String servletLang = "language";
    private static final String servletName = "name";
    private static final Timestamp servletDate = new Timestamp(2012, 12, 12, 12, 12, 12, 12);
    
    private static final String databaseName = "public";
    private static String Create = "/event.jsp";
    private static String Update="/modifyEvent.jsp";
    private static String LIST_Event = "/listEvents.jsp";
 
    private Connection con1;
    private CrudEvent crudEvent;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventController2() {
        super();
        crudEvent=new CrudEvent();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String forward="";
	        String action = request.getParameter("action");
	       
	        if (action.equalsIgnoreCase("deleteEvent")){
	            int eventId = Integer.parseInt(request.getParameter("eventId"));
	            crudEvent.deleteEvent(eventId, request.getSession());
	            forward = LIST_Event;
	            request.getSession().setAttribute("events", crudEvent.getAllEvents(request.getSession()));    
	        } else if (action.equalsIgnoreCase("editEvent")){
	            forward = Update;
	            int eventId = Integer.parseInt(request.getParameter("eventId"));
	            Event e= crudEvent.getEventById(eventId);
	            request.getSession().setAttribute("event", e);
	        } else if (action.equalsIgnoreCase("listAllEvents")){
	            forward = LIST_Event;
	            request.getSession().setAttribute("events", crudEvent.getAllEvents(request.getSession()));
	        } else {
	        	request.getSession().setAttribute("event", new Event());
	            forward = Create;
	        }

	        ServletContext context = getServletContext();
	        
	        RequestDispatcher view = context.getRequestDispatcher(forward);
	        view.forward(request, response);
		
	}
	
	
	
    
	
	private void doHacer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strResultado = "No se sabe";
		PreparedStatement st= null;
		int estab = 0;
		int lang = 0;
		int n = -1000;
		List<Event> l = new ArrayList<Event>();
		
		Event e = new Event();

		
		e.setEstab(request.getParameter("establishment"));
		e.setLang(request.getParameter("language"));
		e.setName(request.getParameter("name"));	
		e.setDescription(request.getParameter("description"));
		
				
		String eventid = request.getParameter("id");
        if(eventid == null || eventid.isEmpty() || eventid.equals("0"))
        {
            crudEvent.createEvent(e, request.getSession());
        
        }else{
        	e.setId(Integer.parseInt(eventid));
        	crudEvent.updateEvent(e, request.getSession());
        }
        ServletContext context = getServletContext();
      
        RequestDispatcher view = context.getRequestDispatcher(LIST_Event);
        request.getSession().setAttribute("events", crudEvent.getAllEvents(request.getSession()));
        view.forward(request, response);
				
	}
	

		
		
				

 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHacer(request, response);
	}
	
	
	

}
