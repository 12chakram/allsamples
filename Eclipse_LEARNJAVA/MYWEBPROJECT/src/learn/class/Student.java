/* write a programe of student class, which contain the 
 * following sno,sname,sadd,m1,m2,m3
 * intilize those property print on the sreen &
 *  calculate total marks
 */
package class_notes;

class Student 
{ 
	int sno;
	String sname,sadd;
	int m1,m2,m3;
	
void setdata()
	{
		sno=101;
		sname="kumar";
		sadd="guntakrindapalli village";
		m1=66;
		m2=66;
		m3=87;
	}
void dispdata()
	{
		System.out.println("sno is:"+sno);	
		System.out.println("sname is:"+sname);
		System.out.println("sadd is:"+sadd);
		System.out.println("m1 is:"+m1);
		System.out.println("m2 is:"+m2);
		System.out.println("m3 is:"+m3);
	}
void total()
	{	
		System.out.println("the total marks are:"+(m1+m2+m3));
	}

	public static void main(String []args)
	{
		Student s =new Student();
		s.setdata();
		//s.dispdata();
		s.total();
	}

}
