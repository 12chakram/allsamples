package com.javatpoint.Test;

import com.javatpoint.Question;
import com.javatpoint.service.Servicelocator;

public class QuestionTest {
	
	
	public static void main(String[] args) {
      
		Question q = (Question) Servicelocator.getService("questions");
		q.displayInfo();
	  
	}

}
