
public class EmpTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Address a=new Address();
		a.setCity("skht");
		a.setState("hyderabad");
		a.setCountry("india");
		
		Emp emp=new Emp();
		emp.setEno(101);
		emp.setEname("lokesh");
		emp.setAddress(a);
		emp.setSalary(18756.65f);
		
		
		/*System.out.println(emp.getEno()+" "+emp.getEname()+" "+emp.getAddress().getCity()+" "+emp.getSalary());*/
		
		System.out.println(emp.getAddress().getCity());
		
		
		Address a2=new Address();
		a2.setCity("hyd");
		a2.setState("hyderabad");
		a2.setCountry("india");
		
		Emp emp2=new Emp();
		emp2.setEno(102);
		emp2.setEname("lokesh");
		emp2.setAddress(a);
		emp2.setSalary(18756.65f);
		
		
		
	
	}

}
