package swRest;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Models.Establishment;
import Models.Event;
import Models.Language;
import Models.User;
import Utils.CrudEvent;
import Utils.CrudUser;
import Utils.DbUtil;
import Utils.establishmentDAO;

@RequestScoped
@Path("/bla")
public class facadeRest {

	private static String databaseName="public"; 
    private Connection connection;
    
	@Path("/text")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello()
	{
		return "Hello everybody!";
	}
	

	@Path("/json/users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(){
		List<User> allUsers = new ArrayList<>();
		CrudUser cruduser = new CrudUser();
		allUsers = cruduser.getAllUsers(null);	
		return allUsers;
	}
	
	@Path("/json/users/id/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("userId") String uId){
		int userId = Integer.valueOf(uId);
		CrudUser cruduser = new CrudUser();
		User user = cruduser.getUserById(userId);	
		return user;
	}
	
	@Path("/json/users/name/{userName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserByName(@PathParam("userName") String uName){
		List<User> users = new ArrayList();
		connection = DbUtil.getConnection(null);
		PreparedStatement stm;
		String sql = "SELECT u.id,u.login,u.name,u.facebookprofile,u.birthday,u.sex,t.name FROM "+databaseName+".users u, public.usertypes t WHERE u.name ilike ? and u.type = t.id";
		
		try {
			
			stm = connection.prepareStatement(sql);
			stm.setString(1, "%"+uName+"%");
			ResultSet rs = stm.executeQuery();
			while(rs.next())
			{
				User u = new User();
				u.setId(rs.getInt(1));
				u.setLogin(rs.getString(2));
				u.setName(rs.getString(3));
				u.setFacebookProfile(rs.getString(4));
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getDate(5));
				u.setBirthday(date);
				u.setSex(rs.getString(6));
				u.setTypeName(rs.getString(7));
				users.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	@Path("/json/languages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Language> getAllLanguages()
	{	List<Language> allLanguages = new ArrayList();
		connection = DbUtil.getConnection(null);
		try {
			Statement stm = connection.createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM "+databaseName+".languages");
			while(result.next())
			{
				Language lan = new Language();
				lan.setId(result.getInt("id"));
				lan.setName(result.getString("name"));
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getTimestamp("dateinclude"));
				lan.setDate(date);
				allLanguages.add(lan);
			}
			stm.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return allLanguages;	
	}
	
	@Path("/json/languages/id/{languageId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Language getLanguageById(@PathParam("languageId") String lanId){
		int languageId = Integer.valueOf(lanId);
		connection = DbUtil.getConnection(null);
		Language lan = new Language();
		
		PreparedStatement stm;
		try {
			String sql ="SELECT * FROM "+databaseName+".languages WHERE id=?";
			stm = connection.prepareStatement(sql);
			stm.setInt(1,languageId);
			ResultSet result = stm.executeQuery();
			if(result.next())
			{
				lan.setId(languageId);
				lan.setName(result.getString("name"));
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getTimestamp("dateinclude"));
				lan.setDate(date);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lan;
	}
	
	
	
	@Path("/json/events")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getAllMeetings()
	{
		List<Event> allMeetings = new ArrayList();
		CrudEvent crudEvent= new CrudEvent();
		allMeetings = crudEvent.getAllEvents(null);
		return allMeetings;
	}
	
	
	@Path("/json/events/id/{eventId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Event getEventById(@PathParam("eventId") String eId)
	{
		int eventId = Integer.valueOf(eId);
		Event e = new Event();
		CrudEvent crudEvent= new CrudEvent();
		e = crudEvent.getEventById(eventId);
		return e;
	}
	
	
	
	@Path("/json/establishments")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Establishment> getAllEstablishments()
	{
		List<Establishment> allEstablishments = new ArrayList();
		establishmentDAO estDao = new establishmentDAO(null);
		allEstablishments = estDao.getAll();
		return allEstablishments;
	}
	
	@Path("/json/establishments/Id/{establishmentId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Establishment getEstablishmentById(@PathParam("establishmentId") String estabId)
	{
		int establishmentId = Integer.valueOf(estabId);
		Establishment estab = new Establishment();
		establishmentDAO estDao = new establishmentDAO(null);
		estab=estDao.getEstablishmentById(establishmentId);
		return estab;
	}
	
	@Path("/json/establishments/name/{estabName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Establishment> getEstablishmentsByName(@PathParam("estabName") String estabName)
	{
		List<Establishment> result = new ArrayList();
		establishmentDAO estDao = new establishmentDAO(null);
		result = estDao.getEstablishmentByName(estabName);
		return result;
	}
	
	
	@Path("/json/users/id/{userId}/events")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getUserEvents(@PathParam("userId") String uId)
	{	int userId = Integer.valueOf(uId);
		List<Event> result = new ArrayList();
		CrudUser crudUser = new CrudUser();
		result = crudUser.getListEventOfUser(userId);
		return result;
	}
	
	@Path("/json/events/id/{eventId}/users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getEventUsers(@PathParam("eventId") String eId)
	{
		int eventId=Integer.valueOf(eId);
		List<User> result = new ArrayList<>();
		CrudEvent crudEvent = new CrudEvent();
		result = crudEvent.getListUsersOfEvent(eventId);
		return result;
	}
	
	
}

