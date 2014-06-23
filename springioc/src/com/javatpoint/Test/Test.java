package com.javatpoint.Test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.javatpoint.Student;

public class Test {
	
	
	public static void main(String[] args) {
		
		
		/**
		 * the Resource object represent the information of applicationContext.xml file.
		 * The Resource is the interface
		 * The ClassPathResource is the implementation class of the Resource interface.
		 * 
		 * The Beanfactory is responsible to return the bean.
		 * The XmlBeanFactory is the implementation class of the BeanFactory . 
		 * There are many methods in the BeanFactory interface . one method id getBean(), which returns the 
		 * object of the associated class.
		 */
		
		/*Resource resource = new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		
		Student student = (Student) factory.getBean("studentbean");
		student.displayInfo();
		*/
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Student student=(Student)context.getBean("studentbean");
		student.displayInfo();
		
	}
	
	

}
