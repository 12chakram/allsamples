package com.jsf.completeref.reg.Mbean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean {
	
	
	private String firstName;
	private String lastName;
	private String gender;
	private Date dob;
	private String email;
	private String serviceLevel;
	
	
	//================================================================================================================================ 
	
	public void validateinputfields(FacesContext context,UIComponent toValidate,Object value) throws ValidatorException{
		
		String uicomponent = toValidate.getId();
		
		if (uicomponent.equals("fname")){
			
			String firstName = (String) value;
			
			if(!(firstName== null)&& (!firstName.equals(""))){
				
				/*if(firstName.contains("  ")){
					FacesMessage message = new FacesMessage("firstName should not be emty");
					throw new ValidatorException(message);

				}*/
				
			 if(firstName.length()<5){
					FacesMessage message = new FacesMessage("firstName should be 5 charcters");
					throw new ValidatorException(message);
				}else if(firstName.length()>10){
					FacesMessage message = new FacesMessage("firstName maximum 10 charcters");
					throw new ValidatorException(message);
				
			}
			
		}else{
			FacesMessage message = new FacesMessage("firstName is Required");
			throw new ValidatorException(message);
		}

		} // -------------------------------------------------------------------
		
		else if (uicomponent.equals("lname")){
			
			String lastName = (String) value;
			
			if(!(lastName== null)&& (!lastName.equals(""))){
				
				/*if(lastName.contains("  ")){
					FacesMessage message = new FacesMessage("lastName should not be emty");
					throw new ValidatorException(message);

				}
				*/
				if(	lastName.length()<5){
					FacesMessage message = new FacesMessage("lastName should be 5 charcters");
					throw new ValidatorException(message);
				}else if(lastName.length()>10){
					FacesMessage message = new FacesMessage("lastName  maximum 10 charcters");
					throw new ValidatorException(message);
				
			}
			
		}else {
			FacesMessage message = new FacesMessage("lastName is Required");
			throw new ValidatorException(message);
		}

		} // ==============================================================================  
		
			/*else if (uicomponent.equals("gender")){
				
				boolean radiobutton =uicomponent.isEmpty();
						
						if(radiobutton){
							FacesMessage message = new FacesMessage("please select gender type");
							throw new ValidatorException(message);
						
						}else{
							 System.out.println("gender selected");
						}
					
						
			} // gender
*/		
		
				else if (uicomponent.equals("email")){
				String emailstr = (String) value;
				if(-1==emailstr.indexOf("@")){
					FacesMessage message = new FacesMessage("Invalid Email address");
					throw new ValidatorException(message);
				}
				} // email
		
				else if (uicomponent.equals("serverl")){
					
					String serviceLevel = (String) value;
					
					if((serviceLevel.equals("")) || serviceLevel==null){
						FacesMessage message = new FacesMessage("please select a service");
						throw new ValidatorException(message);
						
					}else{
						System.out.println("sevice selected");
					}
				
					
		} // service level
	
		}// validateinputfields
	
			
	//================================================================================================================ 
	
	
	
	public String addConfirmedUser() {
		
		boolean added = true; // actual may fail to add user
		FacesMessage doneMessage = null;
		String outcome= null;
		
		if (added){
			
			doneMessage = new FacesMessage("Sucessfully added new User");
			outcome = "done";
		}else{
			doneMessage = new FacesMessage("Fail to add new User");
			outcome="register";
		}
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		return outcome;
		
	}// addConfirmedUser
	
	
	
	public void listener(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
        
        System.out.println(item.getName());
    }
	
	
	
	
	
	
	
	
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getgender() {
		return gender;
	}

	public void setgender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	
	


}
