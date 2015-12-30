package Utils;

import Models.Establishment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class establishmentDAO {

	   private Connection connection;
	   
	   public establishmentDAO(HttpSession session) {
	        connection = DbUtil.getConnection(session);
	    }
	   
	   
	   public void addEstablishment(Establishment e) {
	        try {
	            PreparedStatement preparedStatement = connection
	            					.prepareStatement("insert into Establishments(ownerid,name,address,telephone,placesavailable) values(?,?,?,?,?)");          
	            
	            int ownerId = e.getOwner().getId();
	            // Parameters start with 1
	            preparedStatement.setInt(1, ownerId );
	            preparedStatement.setString(2, e.getName());
	            preparedStatement.setString(3, e.getAddress());
	            preparedStatement.setString(4, e.getTelephone());
	            preparedStatement.setInt(5, e.getPlacesAvailable());
	            
	            preparedStatement.executeUpdate();

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        catch (Exception ex){
	        	ex.printStackTrace();
	        }
	    }
	   
	   
	   public void updtEstablishment(Establishment e) {
	        try {
	            
	        	PreparedStatement preparedStatement = connection
	            					.prepareStatement("update Establishments"
					            		        			+ "	set name = ?,"
					            		        			+ "	address = ?,"
					            		        			+ "	telephone =  ?,"
					            		        			+ "	placesavailable = ?"
					            		        			+ "where id = ?");         
	            
	        	 // Parameters start with 1
	            preparedStatement.setString(1, e.getName());
	            preparedStatement.setString(2, e.getAddress());
	            preparedStatement.setString(3, e.getTelephone());
	            preparedStatement.setInt(4, e.getPlacesAvailable());
	            preparedStatement.setInt(5, e.getId());
	            
	            preparedStatement.executeUpdate();

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        catch (Exception ex){
	        	ex.printStackTrace();
	        }
	    }
	   
	   
	   public void delEstablishment(Establishment e) {
	        try {
	            
	        	PreparedStatement preparedStatement = connection
	            					.prepareStatement("delete from Establishments where id = ?");         
	            
	        	 // Parameters start with 1
	            preparedStatement.setInt(1, e.getId());
	            
	            preparedStatement.executeUpdate();

	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        }
	        catch (Exception ex){
	        	ex.printStackTrace();
	        }
	    }
	   
	  	public List<Establishment> getAll(){		   
			   
			   try {
				   
				    String query = "select e.id,e.name,e.telephone,e.address,e.placesavailable,u.Name as \"OwnerName\" from Establishments e"
				    		+ "	inner join Users u	on u.id = e.ownerid order by e.id asc";
				    
				    
			   
			   		PreparedStatement preparedStatement = connection.prepareStatement(query);  
			   
			   		ResultSet rs = preparedStatement.executeQuery();
	            
			   	    List<Establishment> lst = new ArrayList<Establishment>();
			   	 
	            while(rs.next()){
	            	
	            	 Establishment e = new Establishment();
	            	 e.setId(rs.getInt(1));
	        		 e.setName(rs.getString(2));
	        		 e.setTelephone(rs.getString(3));
	        		 e.setAddress(rs.getString(4));
	        		 e.setPlacesAvailable(rs.getInt(5));
	        		 e.ownerName = rs.getString(6);
	            	
	            	lst.add(e);
	            }
			   	 
	            return lst;		       
			 	
		        } catch (SQLException ex) {
		            ex.printStackTrace();	            
		            
		        }
		        catch (Exception ex){
		        	ex.printStackTrace();	        	
		        }	       
			   
			   return null;		   
		   }
		   
	  	
		public List<Establishment> getEstablishmentsByOwnerId(int OwnerId){		   
			   
			   try {
				   
				    String query = "select e.id,e.name,e.telephone,e.address,e.placesavailable,u.Name as \"OwnerName\" from Establishments e"
				    		+ "	inner join Users u	on u.id = e.ownerid where ownerid = ? order by e.id asc";
				    
				     
			   
			   		PreparedStatement preparedStatement = connection.prepareStatement(query);  

			   		preparedStatement.setInt(1, OwnerId);
			   		
			   		ResultSet rs = preparedStatement.executeQuery();
	            
			   	    List<Establishment> lst = new ArrayList<Establishment>();
			   	 
	            while(rs.next()){
	            	
	            	 Establishment e = new Establishment();
	            	 e.setId(rs.getInt(1));
	        		 e.setName(rs.getString(2));
	        		 e.setTelephone(rs.getString(3));
	        		 e.setAddress(rs.getString(4));
	        		 e.setPlacesAvailable(rs.getInt(5));
	        		 e.ownerName = rs.getString(6);
	            	
	            	lst.add(e);
	            }
			   	 
	            return lst;		       
			 	
		        } catch (SQLException ex) {
		            ex.printStackTrace();	            
		            
		        }
		        catch (Exception ex){
		        	ex.printStackTrace();	        	
		        }	       
			   
			   return null;		   
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
