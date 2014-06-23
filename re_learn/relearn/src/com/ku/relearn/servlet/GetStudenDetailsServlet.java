package com.ku.relearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.jdbc.utils.GettingOracleConnection;

import com.ku.relearn.servlet.Student;

/**
 * Servlet implementation class GetStudenDetailsServlet
 */
public class GetStudenDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudenDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int sid = (Integer.parseInt(request.getParameter("sid")));
		
		try {
			Connection con = GettingOracleConnection.getConnection();
			
			Statement st = con.createStatement();
			String sql  = "select * from students where sid = "+sid;
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				
				
			   int stid = rs.getInt(1);
				String sname = rs.getString(2);
				String squlification = rs.getString(3);
				String coll = rs.getString(4);
				
				
				Student stu = new Student();
				
				stu.setSid(stid);
				stu.setSname(sname);
				stu.setSqulification(squlification);
				stu.setScollege(coll);
				
				request.setAttribute("students",stu );
				
				
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/studentdetails.jsp");
				rd.include(request, response);

				
				
				System.out.println(stid);
				System.out.println(sname);
				System.out.println(squlification);
			System.out.println(coll);
			
			PrintWriter pw = response.getWriter();
			pw.println(stid);
			pw.println(sname);
			pw.println(squlification);
			pw.println(coll);
			
			}
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
