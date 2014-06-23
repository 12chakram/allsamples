package com.javatpoint;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class QuestionMapAnswerUser {
	
	private int id;
	private String name;
	
	
	private Map<AnswerQMAU,User> answers;
	public QuestionMapAnswerUser() {
		
	}
	
public QuestionMapAnswerUser(int id, String name, Map<AnswerQMAU,User>answers){
		
		super();
		this.id=id;
		this.name=name;
		this.answers = answers;
	}
	
	public void displayInfo(){
		System.out.println("Question id:"+id);
		System.out.println("Question name:"+name);
		System.out.println("Answers are..........:");
		
		System.out.println("Map Size is:"+answers.size());
		
		Set<Entry<AnswerQMAU,User>> set = answers.entrySet();
		Iterator<Entry<AnswerQMAU, User>> itr = set.iterator();
		
		while (itr.hasNext()) {
			
			Entry<AnswerQMAU, User> entry = itr.next();
			System.out.println("Answer:"+entry.getKey()+"Posted by:"+entry.getValue());
		}
		
		}
	}
	

