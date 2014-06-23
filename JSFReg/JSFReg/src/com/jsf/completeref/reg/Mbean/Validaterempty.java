package com.jsf.completeref.reg.Mbean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.jsf.completeref.reg.Mbean.Validaterempty")
public class Validaterempty implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		
String uicomponent = (String) value;
		
		if (uicomponent.equals("fname")){
			
			String firstName = (String) value;
			
			if(!(firstName== null)&& (!firstName.equals(""))){
				
				if(firstName.contains("  ")){
					FacesMessage message = new FacesMessage("firstName should not be emty");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);

				}
				
			
			} 
			
		}else if(uicomponent.equals("lname")){
			
			String lastName = (String) value;
			
			if(!(lastName== null)&& (!lastName.equals(""))){
			
				if(lastName.contains("  ")){
					FacesMessage message = new FacesMessage("lastName should not be emty");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);

				}
			
				
	}
		
	}
	
	
	}
	

}//class
