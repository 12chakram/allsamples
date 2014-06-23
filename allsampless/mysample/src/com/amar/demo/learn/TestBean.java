package com.amar.demo.learn;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



@ManagedBean
@RequestScoped
public class TestBean {
	
	public TestBean(){
		System.out.println("inside constructor");
		
	}
/*	private String myparam;
	
	
	public String getMyparam() {
		return myparam;
	}

	public void setMyparam(String myparam) {
		this.myparam = myparam;
	}
*/
	private String name = "kumar";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String method1(){
				name = "vayyala";
		
		return "maintestpage";
	}

public String method2(){
		
		name = "kumarvayyala";
		
		return "maintestpage";
	}

public String method3(){
	
	name = "finish";
	
	return "maintestpage";
}

/*public String commonmethod(){
	
	FacesContext context = FacesContext.getCurrentInstance();HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();HttpSession mySession = myRequest.getSession();      
	
	myparam = myRequest.getParameter("myparam"); 
	
System.out.println("------------------");

	System.out.println(myparam);
	String page =null;
	
	if(myparam.equals("ParamValue1")){
	 
		page = method1();
		
	}else if(myparam.equals("ParamValue2")){
		 
			page = method2();
			
		}else if(myparam.equals("ParamValue3")){
			 
				page = method3();
				
			}
		
		return page;
	}

*/	
}



