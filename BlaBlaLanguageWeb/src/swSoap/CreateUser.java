package swSoap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import Models.User;
import Utils.CrudUser;

@WebService
public class CreateUser {

	@WebMethod
	public String CreateUser(String login, String name, String pass, String sex,String birthDay){
		String ret="";
		try{
			User u = new User(name,login,pass,"",sex,birthDay);
			CrudUser cu = new CrudUser();
			if(login.length()==0 || name.length()==0 || pass.length()==0 || sex.length()==0 || birthDay.length()==0)
			{
				throw new Exception("invalid parameters format");
			}
			
			User tmp = cu.getUserByLogin(login);
			if(tmp.getLogin() !=null) throw new Exception("Login already exist");
			
			cu.addUser(u, null);
			ret = u.FormatString();
			
		}catch(Exception ex){			
			ret = "error: "+ ex.getMessage();
		}
		finally {
			return ret;
		}
	}	
}
