/*
Fibonacci Series Java Example
This Fibonacci Series Java Example shows how to create and print
Fibonacci Series using Java.
*/
package internet;
public class JavaFibonacciSeriesExample 
{
	//number of elements to generate in a series
	
	int limit = 20;
	
	long [] series = new long [limit];
	
	//create first 2 series elements
	
	series[0]=0;
	series [1]=0;
	//create the Fibonacci series and store it in an array
	for (int i =2; i<limit;i++)
	{
		series[i]= series[i-1]+series[i-2];
	}
	//print the Fibonacci series numbers
	
	System.out.println("Febinacci serice upto"+limit);
	for(inti = 0;i < length; i++)
	{ System.out.print(series[i]+"");

	}
}
}