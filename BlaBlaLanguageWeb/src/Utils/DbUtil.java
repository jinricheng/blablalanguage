package Utils;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class DbUtil {

    private static Connection connection = null;
    private static  String XADatasource = "java:jboss/blablalanguage";
    
  public static java.sql.Date toSqlDate(String s){
    	
    	String dt = s.split("/")[2]+"-"+s.split("/")[1]+"-"+s.split("/")[0];
    	
    	return java.sql.Date.valueOf(dt);
    	
    } 
 
    public static Connection getConnection(HttpSession Session) {
    	InitialContext initialContext;
		try {
			
			if (connection != null)
	            return connection;
	        else {
	        	initialContext = new InitialContext();
				if(initialContext != null){
					DataSource ds = (DataSource) initialContext.lookup(XADatasource);
					
					if(ds == null){
						System.err.println("datasource name is not correct");
					}else{
						connection = ds.getConnection();
						return connection;
					}
				}
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return connection;
		
    }
}