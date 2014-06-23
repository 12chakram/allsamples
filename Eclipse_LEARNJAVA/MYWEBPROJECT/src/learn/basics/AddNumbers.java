package learn.basics;

public class AddNumbers{
	public static void main(String[] args) {
		System.out.println("Addition of two numbers!");
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int sum = a + b;
		System.out.println("Sum: " + sum);
	}
}