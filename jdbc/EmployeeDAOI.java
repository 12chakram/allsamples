
package com.st.jdbc.dao;
import com.st.jdbc.vo.Employee;

public interface EmployeeDAOI {
	
	//void updateSal(int eno , double sal);
	//void remove(int eno);
	void save(int eno,String en,double salary,int dno) throws Exception; //done
	//void update(Employee e);
	void getEmployee(Employee e) throws Exception;
	//void updateDept(int eno , int dno);

}
