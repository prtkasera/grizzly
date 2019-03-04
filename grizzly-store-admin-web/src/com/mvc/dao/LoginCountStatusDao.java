package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mvc.bean.LoginBean;
import com.mvc.util.DBConnection;

public class LoginCountStatusDao {

	public void updateCount(LoginBean loginbean){
		
		Connection con;
		//int count=loginbean.getCount();
		String username = loginbean.getUserName();
		//String status;
		//System.out.println(count);
		//System.out.println(username);
		try{
			con=DBConnection.createConnection();
			PreparedStatement ps = con.prepareStatement("update login set  count=count+1 where user_name=?");
			//ps.setInt(1, count);
			ps.setString(1, username);
			int rs = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
	}
		
	}
	public void updateStatus(LoginBean loginbean){
		Connection con;
		String username = loginbean.getUserName();
		String status = loginbean.getSataus();
		
		try{
			con=DBConnection.createConnection();
			PreparedStatement ps = con.prepareStatement("update Login set status ='Blocked' where user_name=?");
			//ps.setString(1, status);
			ps.setString(1,username);
			int rs = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		}
	public int getUpdateCount(LoginBean loginbean){
		Connection con;
		int count=0;
		String username = loginbean.getUserName();
		
		//String status;
		//System.out.println(count);
		//System.out.println(username);
		try{
			con=DBConnection.createConnection();
			PreparedStatement ps = con.prepareStatement("select * from login  where user_name=?");
			//ps.setInt(1, count);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(4);
			
		}catch(Exception e){
			e.printStackTrace();
	}
		return count;
	}
}
