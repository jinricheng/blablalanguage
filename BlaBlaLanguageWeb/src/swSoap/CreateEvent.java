package swSoap;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import Models.Event;
import Models.User;
import Utils.CrudEvent;
import Utils.CrudUser;
import Utils.establishmentDAO;

@WebService
public class CreateEvent {
	@SuppressWarnings("finally")
	
	@WebMethod
	public String CreateEvent(int Establishment, int Language, String Name, String DateEvent,String Description){		
		String ret="";
		try{
			CrudEvent ce = new CrudEvent();
			
			List<Event> l = new ArrayList<Event>();
			
			Event e = new Event();
			
			e.setEstabId(Establishment);
			e.setLangId(Language);
			e.setName(Name);	
			e.setDescription(Description);
			
			//String string_date = "14/01/2016 12:10:00";
			String string_date = DateEvent.toString().trim();

			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date d = f.parse(string_date);
			long milliseconds = d.getTime();
			
			e.setTim(String.valueOf(milliseconds));	
			
			if(!ce.createEvent(e, null)) throw new Exception("Error in create event, try to verify the id ranges");
		
	     	ret = "Sucess";
			
		}catch(Exception ex){			
			ret = "error: "+ ex.getMessage();
		}
		finally {
			return ret;
		}
	}
}