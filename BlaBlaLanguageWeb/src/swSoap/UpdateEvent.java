package swSoap;

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

@WebService
public class UpdateEvent {
	@SuppressWarnings("finally")
	
	@WebMethod
	public String UpdateEvent(int eventId, int Establishment, int Language, String Name, String DateEvent,String Description){		
		String ret="";
		try{
			
		CrudEvent ce = new CrudEvent();
			
			Event e = new Event();
			
			e.setId(eventId);
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
			
			if(!ce.updateEvent(e, null)) throw new Exception("Error in update event, try to verify the id ranges");
		
	     	//ret = "Sucess";
			ret = "Sucess";
			
		}catch(Exception ex){			
			ret = "error: "+ ex.getMessage();
		}
		finally {
			return ret;
		}
	}
}