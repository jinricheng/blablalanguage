package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Models.Event;

import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class CrudEvent {
	private static final long serialVersionUID = 1L;
    private static final String servletEstb = "establishment";
    private static final String servletLang = "language";
    private static final String servletName = "name";
    private static final Timestamp servletDate = new Timestamp(2012, 12, 12, 12, 12, 12, 12);
    
    private static final String databaseName = "public";
  
    private Connection con1;

    
    
    public CrudEvent() {
        con1 = null;  
     }
    
    public void createEvent(Event event,HttpSession session){
    	
    	String strResultado = "No se sabe";
		PreparedStatement st= null;
		int estab = 0;
		int lang = 0;
		int n = -1000;
		List<Event> l = new ArrayList<Event>();
		
		con1 = DbUtil.getConnection(session);
		try{
			String sql1 = "insert into Events(Establishment,Language,Name,DateEvent,Description,DateInclude,Active)"
						+ " VALUES (?,?,?,?,?,?,?)";			
				
					st=con1.prepareStatement(sql1);
					
				    
					estab=this.getEstById(event.getEstab());
				    lang = this.getLanById(event.getLang());
				    
					st.setInt(1, Integer.parseInt(event.getEstab()) );		   
					st.setInt(2,Integer.parseInt(event.getLang()));  
					st.setString(3, event.getName());			   
					st.setTimestamp(4, this.getCurrentTimeStamp());			  
					st.setString(5,event.getDescription());			  
					st.setTimestamp(6, this.getCurrentTimeStamp());			  
					st.setBoolean(7, true);			    			   
					st.executeUpdate();		
					strResultado = "Sí. Operación realizada correctamente.";
					
					st.close();	
					
				}
			catch(Exception e){ e.printStackTrace(); strResultado.concat("No, error al insertar la fila en la BBDD"); }
		
    	
    	
    }
    
   public void updateEvent(Event event, HttpSession session){
    	
    	con1 = DbUtil.getConnection(session);
		PreparedStatement st;
		
		try{	
			String sql1 = "update Events set name=? where id=?";			
			
			st=con1.prepareStatement(sql1);
			
		    
			int estab=this.getEstById(event.getEstab());
		    int lang = this.getLanById(event.getLang());
		    
		    /*st.setInt(1,estab);		   
			st.setInt(2,lang);	
			st.setTimestamp(4, this.getCurrentTimeStamp());			  
			st.setString(5,event.getDescription());
			
			*/	   
			st.setString(1, event.getName());		
			st.setInt(2, event.getId());			
			st.executeUpdate();	
			st.close();

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
 
    public void deleteEvent(int eventId, HttpSession session){
    	
    	con1 = DbUtil.getConnection(session);
		Statement stm1;
		
		try{	
			stm1 = con1.createStatement();
			stm1.executeUpdate("DELETE FROM events WHERE id="+eventId);
			stm1.close();	
			System.out.println("okokokok");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    
    public Event getEventById(int eventId){
    	PreparedStatement st= null;
    	String sql="SELECT * FROM events WHERE id=?";
    	Event e= new Event();
    	try {
			st=con1.prepareStatement(sql);
			st.setInt(1, eventId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt("id"));
				int est = rs.getInt("establishment");
				int langu = rs.getInt("language");
				String lan = this.getLanByName(langu);
				e.setLang(lan);
				e.setName(rs.getString("name"));
				String estName = this.getEstByName(est);
				e.setEstab(estName);
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("dateevent"));
				e.setTim(date);
				e.setDescription(rs.getString("description"));
			}
			
		} catch (SQLException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
    	
    	return e;
    }
    
	 public List<Event> getAllEvents(HttpSession session){
	    	
		 List<Event> list = new ArrayList();
	    	
	    	con1 = DbUtil.getConnection(session);
			Statement stm1;
			try {
				stm1 = con1.createStatement();
				ResultSet rs = stm1.executeQuery("SELECT * FROM "+databaseName+".events");
				
				while (rs.next()){
					Event e = new Event();
					e.setId(rs.getInt("id"));
					int est = rs.getInt("establishment");
					int langu = rs.getInt("language");
					String lan = this.getLanByName(langu);
					String nam = rs.getString("name");
					String estName = this.getEstByName(est);
					e.setLang(lan);
					e.setName(nam);
					e.setEstab(estName);
					String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("dateevent"));
					e.setTim(date);
					list.add(e);	
			}
			stm1.close();
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return list; 	
	    }
	    
	    
	private String getLanByName(int lang){
	    	
	    	String language="";
	    	
	    	String sql2 = "select name from Languages where id= '"+lang+"'";
			Statement stm1;
			try {
				stm1 = con1.createStatement();
			
			ResultSet rs = stm1.executeQuery(sql2);
			
			while (rs.next()){
				language = rs.getString("name");
				
			}
			stm1.close();
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return language;
	    }
	
	private String getEstByName(int est){
		
		String estab="";
		
		String sql2 = "select name from Establishments where id= '"+est+"'";
		Statement stm1;
		try {
			stm1 = con1.createStatement();
		
		ResultSet rs = stm1.executeQuery(sql2);
		
		while (rs.next()){
			estab = rs.getString("name");
			
		}
		stm1.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estab;
	}
	
	
    private int getEstById(String establ){
    	
    	int estab=0;
    	
    	String sql2 = "select id from Establishments where Name= '"+establ+"'";
		Statement stm1;
		try {
			stm1 = con1.createStatement();
		
		ResultSet rs = stm1.executeQuery(sql2);
		
		while (rs.next()){
			estab = rs.getInt("id");
			
		}
		stm1.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estab;
    }
    

    private int getLanById(String lang){
    	
    	int language=0;
    	
    	String sql2 = "select id from Languages where Name= '"+lang+"'";
		Statement stm1;
		try {
			stm1 = con1.createStatement();
		
		ResultSet rs = stm1.executeQuery(sql2);
		
		while (rs.next()){
			language = rs.getInt("id");
			
		}
		stm1.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return language;
    }
   
    private void deleteById( int id) {
    	
    	Statement stmnt=null;
    	String sqlDel = "DELETE FROM Events WHERE id="+id;
		try {
			stmnt = con1.createStatement();
	        stmnt.executeUpdate(sqlDel);
	        stmnt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	 private final Timestamp getCurrentTimeStamp() {

			Calendar cal = Calendar.getInstance();
			java.sql.Date date = new java.sql.Date(cal.getTime().getTime());
			return new Timestamp(date.getTime());

		}

        // Now you can extract all the records
        // to see the remaining records
    }

