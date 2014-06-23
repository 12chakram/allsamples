package com.ku.learn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class URLReaderSB{
	
	StringBuffer sb;
	
public void urlReaderAndWriteIntoFile() throws Exception{
	
	int count = 0;
	

	
URL oracle = new URL("http://blog.covestor.com/feed");



//URL oracle = new URL("http://investing.covestor.com/feed");


	try{
		

	BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
	String inputLine;
	boolean addFlag = false;

	 sb = new StringBuffer();
	
	while((inputLine = in.readLine())!=null){
		if(inputLine.trim().contains("<content:encoded>") || addFlag)
			addFlag = true;
		
		if(inputLine.trim().contains("</content:encoded>"))	
			 addFlag = false;
		
		if(addFlag || inputLine.endsWith("</content:encoded>") ){
			
			System.out.println(inputLine);
			
			sb.append(inputLine);
			
			if(inputLine.endsWith("</content:encoded>"))
				count = count+1;
			
		}
		
		
}
	System.out.println(count);
   in.close();

	  System.out.println("Done");
	
}catch(Exception e){
	e.printStackTrace();
}

}


/*public void readLinebyLineStringBuffer(){
	
	String a[] = sb.toString().split("\n");
	
	System.out.print(a[0]);
}
*/
public static void main(String[] args) {
	
	URLReaderSB urb = new URLReaderSB();
	
	try {
		urb.urlReaderAndWriteIntoFile();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//urb.readLinebyLineStringBuffer();
}

}//urlreader