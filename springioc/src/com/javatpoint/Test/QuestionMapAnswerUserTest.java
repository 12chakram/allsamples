package com.javatpoint.Test;

import com.javatpoint.QuestionMapAnswerUser;
import com.javatpoint.service.Servicelocator;

public class QuestionMapAnswerUserTest {
	
	
	public static void main(String[] args) {
      
		QuestionMapAnswerUser q = (QuestionMapAnswerUser) Servicelocator.getService("questionmapansweruser");
		q.displayInfo();
	  
	}

}
