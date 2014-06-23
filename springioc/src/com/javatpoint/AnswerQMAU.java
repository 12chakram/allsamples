package com.javatpoint;

import java.util.Date;

public class AnswerQMAU {
	
	//QMAU Question Map Answer User
	private int id;
	private String answer;
	private Date postedDate;
	
	public AnswerQMAU(){
		
	}
	public AnswerQMAU(int id, String answer, Date postedDate){
		
		super();
		this.id=id;
		this.answer = answer;
		this.postedDate = postedDate;
	}
   public String toString(){
		return "ID:"+id+"Answer:"+answer+"PostedDate:"+postedDate;
	}
	
}
