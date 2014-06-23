package com.st.jdbc.daoI;

import java.util.Collection;


import com.st.jdbc.vo.EmployeeVO;

public interface EmployeeDAOI {
	
	 void remove(int eno) throws Exception;
	public void updateEmp(int eno) throws Exception;
	  void getEmp() throws Exception;
	void saveEmployee(int eno,String en, double salary,int dno)throws Exception;
     void save() throws Exception;

     //Collection<EmployeeVO> getAllEmployee() throws Exception;

    



}



