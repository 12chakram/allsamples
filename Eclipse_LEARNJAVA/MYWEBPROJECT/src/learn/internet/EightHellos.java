package internet;
//EightHellos.java


//Below is the syntax highlighted version of EightHellos.java from §1.3 Conditionals and Loops.


/*************************************************************************
 *  Compilation:  javac EightHellos.java
 *  Execution:    java EightHellos
 *
 *  Print your nth hello for n = 1 to 8. Illustrates using a loop.
 *
 *  % java EightHellos
 *  1st Hello
 *  2nd Hello 
 *  3rd Hello
 *  4th Hello
 *  5th Hello
 *  6th Hello
 *  7th Hello
 *  8th Hello
 *
 *************************************************************************/

public class EightHellos { 
    public static void main(String[] args) {
        System.out.println("1st Hello");
        System.out.println("2nd Hello");
        System.out.println("3rd Hello");

        int i = 4;
        while (i <= 8) {
            System.out.println(i + "th Hello");
            i = i + 1;
        }

    }
}
