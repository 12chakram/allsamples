//AroundAdvice
package com.st.spring.advices;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TxAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation mi) throws Throwable {
		/*implement  code  to execute before the joinpoint execution. that
		 * is before Advice 
		 */
		//here may want to start the transaction 
		
		System.out.println("In TxAdvice before proceed");
		try{
			
			Object o = mi.proceed();
			
			//lets the proxy invoke the join point 
			
			//the 'o' here describe the return value from the joinpoint
			/**implement the code to execute after the sucessfull execution of the 
			  JoinPoint . here we can change the return value also 
			 **/
			
			// we may want to commit the TX here
			System.out.println("In TxAdvice after proceed success");
			
			return o;
	
		}//try
		  catch(Throwable t ){
			  /**implement the code to execute on abnormal 
			   termination of the JoinPoint that is Throws Advice  
			 **/
			  
			  // In this case here to roll back the Tx
			  
			  System.out.println("In TxAdvice after proceed Throwing exception:"+t);
		  /**
		   * now we can throw the same Exception to propagate it to the client 
		   *       OR
		   *   we can throw some other Exception 
		   *            OR
		   *    return same value compatable to the return type of JoinPoint .
		   *    
		   */
		  
		  throw t;
		  
		 }//catch

		  
		  finally {
			  
			  /** code to execute after the JoinPoint execution .irrespective to the 
			   * out come from the JoinPoint
			   */
			  
			  System.out.println("In TxAdvice after the proceed finally ");
			  
			  
			  
			  
			  
		  }//finally
		  
		  
		  
			}// invoke

}//class
