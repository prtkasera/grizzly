package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.mvc.bean.ProductBean;
import com.mvc.util.DBConnection;


public class AddProductDao {

	public String addProductStatus(ProductBean pb){
		String result="";
		int productId;
		String category;
		String name;
		String description;
		float price;
		
		productId = pb.getProductId();
		category=pb.getCategory();
		name=pb.getCategory();
		description= pb.getDescription();
		price = pb.getPrice();
		
		Connection con ;
		try{
		con = DBConnection.createConnection();
		PreparedStatement ps=con.prepareStatement("insert into product values(?,?,?,?,?)"); 
		ps.setInt(1,productId);
		ps.setString(2, category);
		ps.setString(3,name);
		ps.setString(4,description);
		ps.setFloat(5,price);
		int i=ps.executeUpdate();  
		if(i==0){
			result="error";
		}
		else{
			result ="successful";
		}
		
		}
		catch(Exception e){
			System.out.println(e);
		}
		return result;
		
	}
}
