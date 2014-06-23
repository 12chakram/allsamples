package com.mkyong;

public class EmployeeServicebean {

	
	public EmployeeServicebean(){
		
		
		System.out.println("some code....");
	}
	
	
	
	public boolean flag;
	
	
	
	int A = 1;
	
	int B = 2;
	
	int C = 3;
	
	
	public boolean somevalidations(){
		
		if(C==B+A){
			
			flag = true;
		}else if(A==C-A){
			
			flag = false;
		
		}
	
	return flag;
	
	
	
	
}

}