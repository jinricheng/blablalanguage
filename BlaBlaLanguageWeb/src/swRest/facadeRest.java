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
	

	@Path("/json/allusers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(){
		List<User> allUsers = new ArrayList<>();
		CrudUser cruduser = new CrudUser();
		allUsers = cruduser.getAllUsers(null);	
		return allUsers;
	}
	
	@Path("/json/getUser/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("userId") String uId){
		int userId = Integer.valueOf(uId);
		CrudUser cruduser = new CrudUser();
		User user = cruduser.getUserById(userId);	
		return user;
	}
	
	
	@Path("/json/allLanguages")
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
	
	@Path("/json/getLanguage/{languageId}")
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
	
	
	
	@Path("/json/allEvents")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getAllMeetings()
	{
		List<Event> allMeetings = new ArrayList();
		CrudEvent crudEvent= new CrudEvent();
		allMeetings = crudEvent.getAllEvents(null);
		return allMeetings;
	}
	
	
	@Path("/json/allEstablishments")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Establishment> getAllEstablishments()
	{
		List<Establishment> allEstablishments = new ArrayList();
		establishmentDAO estDao = new establishmentDAO(null);
		allEstablishments = estDao.getAll();
		return allEstablishments;
	}
	
	
}

