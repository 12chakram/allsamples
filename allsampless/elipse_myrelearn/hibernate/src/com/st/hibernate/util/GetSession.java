package com.st.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class GetSession {
	
	
	
	public static  Session getAnnotationConfig(){
		
		AnnotationConfiguration cfg = new AnnotationConfiguration ();
		cfg.configure();
		
		
		SessionFactory sf= cfg.buildSessionFactory();
		Session session=sf.openSession();
		
		
		
		return session;
		
	}

	public static Session getHibernateSession(){
		Configuration cfg = new Configuration();
		cfg.configure();
		
		SessionFactory sf = cfg.buildSessionFactory();
		
		Session se = sf.openSession();
		
		return se;
		
	}
}
