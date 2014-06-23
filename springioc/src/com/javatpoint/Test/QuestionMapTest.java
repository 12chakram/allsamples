package com.javatpoint.Test;

import com.javatpoint.QuestionMap;
import com.javatpoint.service.Servicelocator;

public class QuestionMapTest {
	
	
	public static void main(String[] args) {
      
		QuestionMap q = (QuestionMap) Servicelocator.getService("questionMap");
		q.displayInfo();
	  
	}

}
