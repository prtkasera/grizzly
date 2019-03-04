package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mvc.bean.ProductBean;
import com.mvc.controller.ViewDescriptionServlet;
import com.mvc.util.DBConnection;

public class ViewDescriptionDao {

	public ProductBean viewDescriptionData(ProductBean productbean){
		//ProductBean productb = productbean;
		//ViewDescriptionServlet.i++;
		int productId = productbean.getProductId();
		Connection con;
		try{
			
			con =DBConnection.createConnection();
			PreparedStatement ps = con.prepareStatement("select * from product where product_Id=?");
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
			productbean.setDescription(rs.getString(4));
			productbean.setPrice(rs.getFloat(5));
			}
		}catch(Exception e){
			
		}
		
		
		return productbean;
	
		
	}
}
