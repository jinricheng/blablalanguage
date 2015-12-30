package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import Models.User;;

public class userDAO {

	   private Connection connection;
	   
	   public userDAO(HttpSession session) {
	        connection = DbUtil.getConnection(session);
	    }
	   
	   public boolean existUser(String user, String pass){		   
		   
		   //List<Student> students = new ArrayList<Student>();
		   try {
			   
		   String query = "SELECT * FROM USERS WHERE LOGIN = ? AND PASS = ?";
		   
		   PreparedStatement preparedStatement = connection.prepareStatement(query);  
		   preparedStatement.setString(1, user);
           preparedStatement.setString(2, pass);           
    
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next()) return false;
            else return true;
	               
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	            
	        }
	        catch (Exception ex){
	        	ex.printStackTrace();
	        	return false;
	        }	       
		   
	   }
	   
	   	public User getUserByLogin(String user){		   
		   
		   try {
			   
			    String query = "SELECT Id,Login,Name,DateInclude FROM USERS WHERE LOGIN = ?";
		   
		   		PreparedStatement preparedStatement = connection.prepareStatement(query);  
		   
		   		preparedStatement.setString(1, user);        
    
		   		ResultSet rs = preparedStatement.executeQuery();
            
		   		User u = new User();
            
	        if(!rs.next()) return null;
	        else{
	        	
	        		 u.setId(rs.getInt(1));
	        		 u.setLogin(rs.getString(2));
	        		 u.setName(rs.getString(3));
	        		 u.setDateInclude(rs.getDate(4));            		 
	        		 return u;            	
	        }
		 	
	        } catch (SQLException ex) {
	            ex.printStackTrace();	            
	            
	        }
	        catch (Exception ex){
	        	ex.printStackTrace();	        	
	        }	       
		   
		   return null;		   
	   }	   
	   
	   public void addUser(User u) {
	        try {
	            
	        	PreparedStatement preparedStatement = connection.prepareStatement("insert into users(type, name, login, pass, sex, birthday) values(?,?,?,?,?,?)");          
	            
	            // Parameters start with 1
	            preparedStatement.setInt(1, 1);
	            preparedStatement.setString(2, u.getName());
	            preparedStatement.setString(3, u.getLogin());
	            preparedStatement.setString(4, u.getPass());
	            preparedStatement.setString(5, u.getSex());	            
	            java.sql.Date dt = DbUtil.toSqlDate(u.getBirthday());
	            preparedStatement.setDate(6, dt);
	            
	            preparedStatement.executeUpdate();

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        catch (Exception ex){
	        	ex.printStackTrace();
	        }
	    }
	   
	   /*
	   
	   public void addUser(User user) {
	        try {
	            PreparedStatement preparedStatement = connection
	                    .prepareStatement("insert into users(firstname,lastname,dob,email) values (?, ?, ?, ? )");
	            // Parameters start with 1
	            preparedStatement.setString(1, user.getFirstName());
	            preparedStatement.setString(2, user.getLastName());
	            preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
	            preparedStatement.setString(4, user.getEmail());
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void deleteUser(int userId) {
	        try {
	            PreparedStatement preparedStatement = connection
	                    .prepareStatement("delete from users where userid=?");
	            // Parameters start with 1
	            preparedStatement.setInt(1, userId);
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void updateUser(User user) {
	        try {
	            PreparedStatement preparedStatement = connection
	                    .prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?" +
	                            "where userid=?");
	            // Parameters start with 1
	            preparedStatement.setString(1, user.getFirstName());
	            preparedStatement.setString(2, user.getLastName());
	            preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
	            preparedStatement.setString(4, user.getEmail());
	            preparedStatement.setInt(5, user.getUserid());
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<User> getAllUsers() {
	        List<User> users = new ArrayList<User>();
	        try {
	            Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery("select * from users");
	            while (rs.next()) {
	                User user = new User();
	                user.setUserid(rs.getInt("userid"));
	                user.setFirstName(rs.getString("firstname"));
	                user.setLastName(rs.getString("lastname"));
	                user.setDob(rs.getDate("dob"));
	                user.setEmail(rs.getString("email"));
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return users;
	    }

	    public User getUserById(int userId) {
	        User user = new User();
	        try {
	            PreparedStatement preparedStatement = connection.
	                    prepareStatement("select * from users where userid=?");
	            preparedStatement.setInt(1, userId);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                user.setUserid(rs.getInt("userid"));
	                user.setFirstName(rs.getString("firstname"));
	                user.setLastName(rs.getString("lastname"));
	                user.setDob(rs.getDate("dob"));
	                user.setEmail(rs.getString("email"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return user;
	    }
	}
*/
	   
	   
	   
	
}
