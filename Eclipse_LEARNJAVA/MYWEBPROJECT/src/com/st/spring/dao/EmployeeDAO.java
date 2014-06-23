package com.st.dao.jdbc;

import org.springframework.jdbc.core.*;

import com.st.dao.EmployeeDAOI;
import com.st.vo.Employee;

import java.sql.*;

public class EmployeeDAO implements EmployeeDAOI
{

private JdbcTemplate jt;

 public EmployeeDAO(JdbcTemplate j) //check once 
	{

		jt =j;

	}

public void remove(int eno){
	jt.update("delete from emp where empno="+eno);
}//remove

public void updateDept(int eno, int dno){
	jt.update("updtae emp set deptno =? where empno = ?",new Object []{dno,eno});

}//update


public void updateSal(int eno , double sal){
	jt.update("updtae emp set sal=? where empno=?", new Object[] {sal,eno},new int []{Types.DOUBLE,Types.INTEGER});

}//updatesal

public void update ( final Employee e){

PreparedStatementSetter pss = new PreparedStatementSetter(){
	
	@Override
	public void setValues(PreparedStatement ps) throws SQLException{
	 

ps.setString(1,e.getName());
ps.setDouble(2, e.getSal());
ps.setInt(3,e.getDeptno());
ps.setInt(4,e.getEmpno());


}//set value

};//pss

jt.update("update emp setname=?,sal=?,depno=? where empno = ?", pss);

}//update employee


public void save(final Employee e) {
	/*
	 here also we can use pss as done with the above method - for demo we want to use 
	 PSC for implementing this method 
	**/
	
	PreparedStatementCreator psc = new PreparedStatementCreator(){

		@Override
		public PreparedStatement createPreparedStatement(Connection con)
				throws SQLException {
			
			PreparedStatement ps = con.prepareStatement("insert into emp values (?,?,?,?)");
			
			// set the parameters same as earlier 
			ps.setString(1,e.getName());
			ps.setDouble(2, e.getSal());
			ps.setInt(3,e.getDeptno());
			ps.setInt(4,e.getEmpno());

			
			return ps;
		}
		
	};  //dought 
	
	jt.update(psc);
	
}//save

	
	}//class

