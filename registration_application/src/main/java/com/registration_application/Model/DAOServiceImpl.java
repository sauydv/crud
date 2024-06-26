package com.registration_application.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOServiceImpl implements DAOService{
    private Connection con;
	private Statement stmnt;
	@Override
	public void connectDB() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con	=DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_app_100","root","S@2468yadav");
		stmnt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean verifyLogin(String username, String password) {
		try {
			ResultSet result = stmnt.executeQuery("Select * from login where email='"+username+"' and password='"+password+"'");
		    return result.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	    }
	@Override
	public void saveRegistration(String name, String city, String email, String mobile) {
		try {
		stmnt.executeUpdate("insert into registration values('"+name+"','"+city+"','"+email+"','"+mobile+"')");
	    } catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public boolean existByEmail(String email) {
		try {
			ResultSet result = stmnt.executeQuery("Select * from registration where email='"+email+"'");
		    return result.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public ResultSet getAllRegistration() {
	try {
		ResultSet result = stmnt.executeQuery("Select * from registration");
	    return result;
	} catch (Exception e) {
		 e.printStackTrace();
	}
		return null;
	}
	
	@Override
	public void deleteByEmail (String email) {
		try {
			stmnt.executeUpdate("delete from registration where email='"+email+"'");
		    } catch (Exception e) {
				e.printStackTrace();
			}
	 }
	
	@Override
	public void updateRegistration (String email, String mobile) {
		try {
	 stmnt.executeUpdate("UPDATE registration SET mobile ='"+mobile+"' WHERE email='"+email+"'");
		    } catch (Exception e) {
				e.printStackTrace();
			}
		
	}
		
	}



