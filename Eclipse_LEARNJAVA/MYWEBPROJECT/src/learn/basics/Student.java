package learn.basics;

class Student{
 int sno;
 String sname,sadd;
 int m1,m2,m3;
 
 void setdata()
 {
	 sno=101;
	 sname="ku";
	 sadd="hyd";
		 m1=9;
		 m2=9;
		 m3=99;
	 
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
System.out.println("total marks are:"+(m1+m2+m3));
}
 public static void main(String[] args)
{
	Student s1=new Student();
	s1.setdata();
	s1.dispdata();
	s1.total();
}

}


