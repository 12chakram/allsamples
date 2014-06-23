package com.st.spring.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.st.spring.vo.Employee;

public class EmployeeController extends AbstractWizardFormController {

	
	public void validatePage(Object c, Errors error, int page){
		
		//TO DO: validation for the fileds in the given page
		
		
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
		Employee e = (Employee)arg2;
		// TO DO : procedd the form as all the wizards submited
		
		System.out.println("empno:"+e.getEmpno());
		System.out.println("Name:"+e.getName());
		System.out.println("Sal:"+e.getSal());
		System.out.println("Deptno:"+e.getDeptno());
		
		return null;
	}
	

}
