package Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

import Models.User;

public class CrudUser {

	private static final long serialVersionUID = 1L;
    private static final String servletName = "name";
    private static final String servletType = "type";
    private static final String servletLogin = "login";
    private static final String servletPass = "pass";
    private static final String servletSex = "sex";
    private static final String servletBirthday = "birthday";
	private static String databaseName="public"; 
    private Connection connection;

    public CrudUser() {
       connection = null;
    }

    public void addUser(User user, HttpSession session) {
    	connection = DbUtil.getConnection(session);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into "+databaseName+".users"
            		+ "(name, login, pass, sex) values ( ?, ?, ?, ? )");
            // Parameters start with 1

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPass());
            preparedStatement.setString(4, user.getSex());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId, HttpSession session) {
    	connection = DbUtil.getConnection(session);
        try {
        	PreparedStatement preparedStatement1 = connection
                      .prepareStatement("DELETE FROM "+databaseName+".userdefinitions WHERE userid=?");
        	preparedStatement1.setInt(1, userId);
            preparedStatement1.executeUpdate();
        	
        	
        	PreparedStatement preparedStatement2 = connection
                    .prepareStatement("DELETE FROM "+databaseName+".userlanguages WHERE userid=?");
        	preparedStatement2.setInt(1, userId);
            preparedStatement2.executeUpdate();
            
            PreparedStatement preparedStatement3 = connection
                    .prepareStatement("DELETE FROM "+databaseName+".users WHERE id=?");
            // Parameters start with 1
            preparedStatement3.setInt(1, userId);
            preparedStatement3.executeUpdate();
      	
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user, HttpSession session) {
    	connection = DbUtil.getConnection(session);
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE "+databaseName+".users SET login=?, name=?, pass=?, sex=?"+//, birthday=?" +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPass());
            preparedStatement.setString(4, user.getSex());
           // preparedStatement.setString(5, user.getBirthday());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(HttpSession session) {
    	connection = DbUtil.getConnection(session);
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM "+databaseName+".users");
            while (rs.next()) {
                User user = new User();
                user.setBirthday(rs.getString(servletBirthday));
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString(servletLogin));
                user.setName(rs.getString(servletName));
                user.setPass(rs.getString(servletPass));
                user.setSex(rs.getString(servletSex));
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM "+databaseName+".users WHERE id=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	user.setBirthday(rs.getString(servletBirthday));
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString(servletLogin));
                user.setName(rs.getString(servletName));
                user.setPass(rs.getString(servletPass));
                user.setSex(rs.getString(servletSex));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
//    public User getUserByName(String name) {
//        User user = new User();
//        try {
//            PreparedStatement preparedStatement = connection.
//                    prepareStatement("SELECT * FROM "+databaseName+".users  WHERE name=?");
//            preparedStatement.setString(1, name);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//            	user.setBirthday(rs.getString(servletBirthday));
//                user.setId(rs.getInt("id"));
//                user.setLogin(rs.getString(servletLogin));
//                user.setName(rs.getString(servletName));
//                user.setPass(rs.getString(servletPass));
//                user.setSex(rs.getString(servletSex));
//            }
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return user;
//    }
}
