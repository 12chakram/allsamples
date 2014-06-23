// ServiceLocator.java 

public class Servicelocator { 

public static UILoginService getUILoginService() { 
	
	uiLoginService = (UILoginService)SpringApplicationContext.getBean("UILoGinService");
	return UiLoginService ;

}

}// class 