package swSoap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import Models.User;
import Utils.CrudUser;

@WebService
public class CreateEvent {
	@SuppressWarnings("finally")
	
	@WebMethod
	public String CreateEvent(int Establishment, int Language, String Name, String DateEvent,String Description){		
		String ret="";
		try{
			ret = "Sucess";
			
		}catch(Exception ex){			
			ret = "error: "+ ex.getMessage();
		}
		finally {
			return ret;
		}
	}
}