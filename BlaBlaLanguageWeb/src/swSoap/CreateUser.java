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
			/*
				u.setLogin(login);
				u.setName(name);
				u.setPass(pass);
				u.setSex(sex);
			//u.setBirthday(birthday);	*/
			
			//cu.addUser(u, null);
			ret = u.FormatString();
			
		}catch(Exception ex){			
			ret = "error: "+ ex.getMessage();
		}
		finally {
			return ret;
		}
	}	
}
