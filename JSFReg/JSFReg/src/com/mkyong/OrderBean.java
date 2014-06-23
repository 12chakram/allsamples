package com.mkyong;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name="order")
@SessionScoped
public class OrderBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public static String typeboolaen = "javax.el.ValueExpression(must evaluate to java.lang.Boolean)";
	public static String typeobject = "javax.el.ValueExpression(must evaluate to java.lang.Object) ";
	public static String stringtype = "	javax.el.ValueExpression(must evaluate to java.lang.String) ";
	
	
	
	private static final ArrayList<Order> orderList = 
		new ArrayList<Order>(Arrays.asList(
	
		new Order("id", "false", "	true","	java.lang.String","The component identifier for this component. This value must be unique within the closest parent component that is a naming container."),
		new Order("includeViewParams", "false", "false","javax.el.ValueExpression(must evaluate to java.lang.Boolean) ","Whether to include page parameters in the target URI."),
		new Order("outcome", "false", "	false",typeboolaen,"The logical outcome used to resolve a navigation case."),
		new Order("rendered", "false", "false",typeboolaen,"Flag indicating whether or not this component should be rendered (during Render Response Phase), or processed on any subsequent form submit. The default value for this property is true."),
		new Order("value", "false", "false",typeobject,"The current value of this component."),
		
		
		new Order("accesskey", "false", "false",stringtype,"Access key that, when pressed, transfers focus to this element."),
		new Order("charset", "false", "false",stringtype,"The character encoding of the resource designated by this hyperlink."),
		new Order("coords", "false", "false",stringtype,"The position and shape of the hot spot on the screen (for use in client-side image maps)."),
		new Order("dir", "false", "false",stringtype,"Direction indication for text that does not inherit directionality. Valid values are LTR (left-to-right) and RTL (right-to-left)."),
		new Order("disabled", "false", "false",typeboolaen,"Flag indicating that this element must never receive focus or be included in a subsequent submit."),

	
		new Order("fragment", "false", "false",stringtype,"The identifier of the page fragment which should be brought into focus when the target page is rendered. The value of this attribute is appended to the end of target URL following a hash (#) mark. This notation is part of the standard URL syntax."),
		new Order("hreflang", "false", "false",stringtype,"The language code of the resource designated by this hyperlink."),
		new Order("lang", "false", "false",stringtype,"Code describing the language used in the generated markup for this component.")));	
		
	 
	public ArrayList<Order> getOrderList() {
 
		return orderList;
 
	}
	
	public String deleteAction(Order order) {
	    
		orderList.remove(order);
		return null;
	}
 
	public static class Order{
		
		
	 String required;
	 String AttributeName;
	 String realtime_Required;
	 public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}

	String type;
	 String Description;
	 
   public Order(String AttributeName, String required, String realtime_Required,String type, String Description) {
			this.AttributeName = AttributeName;
			this.required = required;
			this.realtime_Required = realtime_Required;
			this.type = type;
			this.Description = Description;
			
			
	}// constructor

   

   // setters and getters
   
public String getRequired() {
	return required;
}

public void setRequired(String required) {
	this.required = required;
}

public String getAttributeName() {
	return AttributeName;
}

public void setAttributeName(String attributeName) {
	AttributeName = attributeName;
}

public String getRealtime_Required() {
	return realtime_Required;
}

public void setRealtime_Required(String realtime_Required) {
	this.realtime_Required = realtime_Required;
}

public String getDescription() {
	return Description;
}

public void setDescription(String description) {
	Description = description;
}
   
   
   
   

	}
		
	}
