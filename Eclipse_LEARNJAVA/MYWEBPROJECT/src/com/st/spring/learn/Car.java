package spring.mylearn;

public class Car {

	private Wheel wheel;
	
	//Car(Wheel wheel){
		//this.wheel = wheel;
	//}

	public Wheel getWheel() {
		return wheel;
	}

	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}
	
	public void move (){
		
		System.out.println("CARS MOVE");
		this.getWheel().rotate();
		
		
		
		
	}//move
	
	
	
}//class
