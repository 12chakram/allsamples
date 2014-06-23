package learn.basics;

public class TwoDimensionalArrayDemo {

	public static void main(String args[]){
	 int[][] markes= new int[3][6];
	 
	 for(int i=0;i<markes.length;i++){
		 for(int j=0;j<markes[i].length;j++){
			 markes[i][j]=(int)(Math.random()*100);
		 }//for j
		 
		 
	 }//for
		
	 for(int i=0;i<markes.length;i++){
		 for(int j=0;j<markes[i].length;j++){
		
			 System.out.println("Markes In subject:"+markes[i][j]);
			 
		 }
	 }
		
	}//main
	
	
	
	
	
	
}//class
