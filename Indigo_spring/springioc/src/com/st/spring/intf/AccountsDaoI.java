//AccountsDaoI.java

package com.st.spring.intf;

public interface AccountsDaoI {
	
void setBalance(int accno,double amt);

double getBalance(int accno);

}
