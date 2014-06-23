package com.st.spring.daoI;
import java.util.*;
import com.st.spring.vo.Employee;


public interface EmpDAO {

	void createEmp(Employee e) throws Exception;
	
	Employee getEmpById(int eno) throws Exception;
	
	List getEmpsByDept(int dno) throws Exception;
	
	void updateEmp(Employee e) throws Exception;
	
	

}//interface 
