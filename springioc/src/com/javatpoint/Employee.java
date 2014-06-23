package com.javatpoint;

public class Employee {
	
	private int id;
	private String name;
    private Address address;   //Aggregation 
    
	public Employee(){
		System.out.println("def cons");
	}
	
	
	public Employee(int id){
		this.id = id;
	}

	public Employee(String name){
		
		this.name = name;
	}
	
	public Employee(int id, String name, Address address){
		super();
		this.id= id;
		this.name = name;
		this.address = address;
	}
	
	public void show(){
		
		System.out.println(id+ "  " +name);
		System.out.println(address.toString());
	}
}
