package com.st.jdbc.operations;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateEmpSalry  extends SqlUpdate{

	public UpdateEmpSalry(DataSource ds){
		
		super(ds,"update emp set sal =? where empno = ?");
		
		declareParameter(new SqlParameter (Types.DOUBLE));
		declareParameter(new SqlParameter (Types.INTEGER));
		
		compile();
		/*
		once compile it became thread safe and 
		can't do any operation 
		Freeze the object 
		and constructor it self is thread safe 
		*/
		
	}//constructor 
	
}//class
