package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.ProductBean;
import com.mvc.dao.AddProductDao;


public class AddProductServlet extends HttpServlet {
	   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
		int productId = Integer.parseInt(request.getParameter("productId"));
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		
		ProductBean pb = new ProductBean();
		AddProductDao apd = new AddProductDao();
		
		pb.setProductId(productId);
		pb.setCategory(category);	
		pb.setName(name);
		pb.setDescription(description);
		pb.setPrice(price);
		String result =apd.addProductStatus(pb);
		if(result.equals("successful")){
			RequestDispatcher rd = request.getRequestDispatcher("/ViewProduct.jsp");
			out.println("record inserted");
			rd.include(request,response);
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("/AddProduct.jsp");
			out.println("record not inserted");
			rd.include(request,response);
			
		}
	}

}
