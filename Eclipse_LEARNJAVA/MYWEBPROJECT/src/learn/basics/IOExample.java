package learn.basics;
//Writing to a File Example
	
	
														
import java.io.*;
	
public class IOExample {
	
 public static void main( String[] args ) {
	
   FileOutputStream fos;
	
   try {
	
     fos = new FileOutputStream( args[ 0 ] );
	
     while ( true ) {
	
     int n = System.in.available();
	
     if ( n > 0 ) {
	
        byte[] b = new byte[ n ];
	
        int result = System.in.read( b );
	
        if ( result == -1 )break;
	
          fos.write( b, 0, result );
	
     }
	
     } // end while
	
   } catch ( IOException e ) { System.err.println( e ); }
 } //end main
	
} //end class
	