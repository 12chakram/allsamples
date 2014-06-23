package com.st.spring.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.st.spring.vo.Employee;


import com.st.spring.daoI.EmpDAO;


public class OracleEmpDAO implements EmpDAO {

	Connection con;
	private Connection getConnection ()throws Exception{
		
		if(con == null){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
		}
		return con;
	}
	

	public void createEmp(Employee e) throws Exception {
	
		try {
			Connection con = getConnection();
			
			PreparedStatement ps = con.prepareStatement
			("insert into emp (empno,ename,sal,deptno)values(?,?,?,?)");
			
			// set the values to preparedstatement
			
			ps.setInt(1,e.getEmpno());
			ps.setString(2,e.getName());
			ps.setDouble(3,e.getSal() );
			ps.setInt(4,e.getDeptno() );
			
			//Now, execute the statement
			
			int count = ps.executeUpdate();
			if (count == 1 || count==Statement.SUCCESS_NO_INFO)
				return;
			else
				throw new Exception("Unable to insert a new record reason unknown");
			
			
		}//tyr 
		catch (SQLException  seqe ) {
			
			seqe.printStackTrace();
		}//catch
		finally{
			try{
				con.close();
			}//try
			catch(SQLException e1){
				System.out.println("error message:"+e1.getMessage());
				
			}//catch
			
			
			}//finally
		
		}//createemp

	public Employee getEmpById(int eno) throws Exception {
		
		Employee emp = null;
		try{
			Connection con = getConnection();
			
			PreparedStatement ps = con.prepareStatement("select ,ename,sal,deptno from emp where empno=?");
			//set the value to prepared statement 
			
			ps.setInt(1,eno);
			// Now ,execute the statement
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				emp = new Employee();
				emp.setEmpno(eno);
				emp.setName(rs.getString(1));
				emp.setSal(rs.getDouble(2));
				emp.setDeptno(rs.getInt(3));
		}//if
					
	}//try
    catch (SQLException seqe ) {
			
		System.out.println("Error:"+seqe);
		
		}//catch
    finally{
		try{
			con.close();
		}//try
		catch(SQLException e1){
			System.out.println("error message:"+e1.getMessage());
			
		}//catch
		
		
		}//finally
		return emp;
	
	}//getEmpById
	
    
    
  
	public List getEmpsByDept(int dno) throws Exception {
		
		List emps = null;
		
		try{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select empno,ename,sal, from emp where deptno=?");
		
		// set the values to prepared statement 
			ps.setInt(1, dno);
			
			// Now execute the statement 
			
			ResultSet rs = ps.executeQuery();
			
			emps = new ArrayList();
			
			while(rs.next()){
				
				Employee emp = new Employee();
				
				emp.setDeptno(dno);
				emp.setEmpno(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setSal(rs.getDouble(3));
				
				}//while

			
		}//try
		catch(SQLException seqe){
			System.out.println(seqe);
		}
		finally{
			try{
				con.close();
			}//try
			catch(SQLException e1){
				System.out.println("error message:"+e1.getMessage());
				
			}//catch
			
			}//finally
		
		return emps;
		
		}//getEmpByDeptno
	

	public void updateEmp(Employee e) throws Exception {
		
		
		try{

		Connection con = getConnection();
		
		PreparedStatement ps = con.prepareStatement
		("update emp set ename+?,sal=?,deptno=? where empno=?");
		
		//set the value to prepared statement
		ps.setString(1,e.getName());
		ps.setDouble(2,e.getSal());
		ps.setInt(3,e.getDeptno());
		ps.setInt(4,e.getEmpno());
		
		// now ,execute the statement 
		
		int count=ps.executeUpdate();
		
		if(count==1 || count==Statement.SUCCESS_NO_INFO) return;
		else
			throw new Exception("unable to update the detaild , reason unkoen");
		
		}//try

		catch(SQLException seqe){
			System.out.println("Error:"+seqe);
		
		}//catch
		
		finally{
			try{
				con.close();
			}//try
			catch(SQLException e1){
				System.out.println("error message:"+e1.getMessage());
				
			}//catch
			
			}//finally
		
		
		
	}//updateEmp

}//class 


