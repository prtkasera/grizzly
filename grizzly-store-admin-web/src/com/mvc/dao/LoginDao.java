package com.mvc.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;


import com.mvc.bean.LoginBean;
 import com.mvc.util.DBConnection;
 public class LoginDao {
 public String authenticateUser(LoginBean loginBean)
 {
	 
String userName = loginBean.getUserName(); //Keeping user entered values in temporary variables.
 String password = loginBean.getPassword();
 //int count1=loginBean.getCount();
Connection con = null;
 Statement statement = null;
 ResultSet resultSet = null;
 
String userNameDB = "";
 String passwordDB = "";
 String result="";
 int countdb=0;
 //int count=0;
 //loginBean.setCount(count);
 String status="";
 
 
try
 {
 con = DBConnection.createConnection(); //establishing connection
 PreparedStatement ps= con.prepareStatement("select * from login where user_name=?"); //Statement is used to write queries. Read more about it.
 ps.setString(1, userName);
 resultSet  = ps.executeQuery();//Here table name is users and userName,password are columns. fetching all the records and storing in a resultSet.
 
while(resultSet.next())
{
	userNameDB = resultSet.getString(1); //fetch the values present in database
  passwordDB = resultSet.getString(2);
  countdb=resultSet.getInt(4);
  status = resultSet.getString(3);
  System.out.println(resultSet.getString(1));
  //count=countdb;
  //System.out.println(countdb);
 }

  if(countdb<=3&&status.equals("UnBlocked")){
	  if(userName.equals(userNameDB) )
	   { if(password.equals(passwordDB))
	   		{		
      result="SUCCESS";
      //count=0;
      //loginBean.setCount(count);
      //new LoginCountStatusDao().updateCount(loginBean);
      //If the user entered values are already present in database, which means user has already registered so I will return SUCCESS message.
	   		return result;
	   		}
	   	else{
	   			
	   			//count++;
	   			//System.out.println(count);
	   			new LoginCountStatusDao().updateCount(loginBean);
	   			result = "password is not correct" + new LoginCountStatusDao().getUpdateCount(loginBean);
	   			System.out.println(new LoginCountStatusDao().getUpdateCount(loginBean));
	   			//loginBean.setCount(count);
	   			if(new LoginCountStatusDao().getUpdateCount(loginBean)>3){
	   				new LoginCountStatusDao().updateStatus(loginBean);
	   			result ="You have been blocked";
	   			return result;
	   			}
	   	//System.out.println(new LoginCountStatusDao().getUpdateCount(loginBean));		
	   	}
	   			
	   		}
	   }
  		
  			else{
		   result ="username is not correct";
	   }
 
 }
 catch(SQLException e)
 {
 e.printStackTrace();
 }
 return result; // Just returning appropriate message otherwise
 }
 }