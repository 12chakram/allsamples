package learn.basics;

public class TwosMultoples {
	public static void main(String args[]){
		
		int[]twosMultiples=new int[10];
		for(int i=0,j=1;i<twosMultiples.length;){
			if(j%2==0){
				twosMultiples[i]=j;
				i++;
				j++;
			}//if
			else{
				j++;
			}//else
		}//for
		
		for(int i: twosMultiples){
			System.out.println(i);
		}
		
	}//main
	

}//class
