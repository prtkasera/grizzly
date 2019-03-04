

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String user=request.getParameter("user_name");
		String pass=request.getParameter("password");
		boolean st=false;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			String sql="Select * from login where user_name=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs=ps.executeQuery();
			st=rs.next();
			HttpSession session=request.getSession(true);
			session.setAttribute("user_name",user);
			session.setAttribute("password",pass);
			if(!st)
			{
				 request.setAttribute("errmsg","Incorrect username");
				 RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		         rd.forward(request, response);
			}
			while(st)
			{
				String uname=rs.getString(1);
				String password=rs.getString(2);
				int c=rs.getInt(4);
				String status=rs.getString(3);
				if(status.equals("blocked"))
				{
					request.setAttribute("errmsg","You Have been Blocked");
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			        rd.forward(request, response);
				}
				else
				{
					if(password.equals(pass))
					{
						response.sendRedirect("Welcome.jsp");
					}
					else
					{
						if (c==3)
						{
							PreparedStatement stmt=con.prepareStatement("update login set status='blocked' where user_name=?");
							stmt.setString(1, user);
							rs=stmt.executeQuery();
							request.setAttribute("errmsg","BLOCKED!! You have Done 3 unsuccessful login attempts");
							RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					        rd.forward(request, response);
					        stmt.close();
						}
						else
						{
							PreparedStatement stmt=con.prepareStatement("update login set  count=count+1 where user_name=?");
							stmt.setString(1, user);
							rs=stmt.executeQuery();
							request.setAttribute("errmsg","Password Is incorrect!! You Have left "+(3-c)+" Successful attempts");
							RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					        rd.forward(request, response);
						}
						
					}
				}
				
			}
			
			ps.close();
			rs.close();
			con.close();
			
			
		}
		catch(Exception e)
		{
	
			pw.println("Failed");
			e.printStackTrace();
		}
		
		
		
	}

}
