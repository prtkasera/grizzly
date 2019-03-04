package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.ProductBean;
import com.mvc.dao.ViewDescriptionDao;


public class ViewDescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//public static int i=0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RequestDispatcher rd = request.getRequestDispatcher("/ViewDescription.jsp");
		//rd.forward(request,response);
		ProductBean pb = new ProductBean();
		//ProductBean pc1;
		pb.setProductId(Integer.parseInt(request.getParameter("productId")));
		
		ViewDescriptionDao vdd = new ViewDescriptionDao();
		//pc1 =vdd.viewDescriptionData(pb);
	
		//String des= pc1.getDescription();
		//String pri = String.valueOf(pc1.getPrice());
		
request.setAttribute("description",vdd.viewDescriptionData(pb).getDescription() );
request.setAttribute("price", vdd.viewDescriptionData(pb).getPrice());
//i++;
//request.setAttribute("i",i);
RequestDispatcher rde = request.getRequestDispatcher("/ViewDescription.jsp");
rde.include(request,response);

	}

}
