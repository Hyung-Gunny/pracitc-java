package chap04_Homework1_22112204_박형건;

public class Truck extends Vehicle
{
	private int cargo_capa;

	public Truck(String color, String type, int eng_capa, Person driver, int cargo_capa) 
	{
		super(color, type, eng_capa, driver);
		this.cargo_capa=cargo_capa;
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		return super.getColor() + " " + super.getVtype() + " (driver : "+super.getDriver()+")";
	}

	@Override
	public void forward(int speed) {
		// TODO Auto-generated method stub
		System.out.printf("moving forward at speed %d Km/h; ", speed);
		
	}

	@Override
	public void turn(int angle) {
		// TODO Auto-generated method stub
		System.out.printf("turning %d angle; ", angle);
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.printf("stopping;");
		System.out.println();
		
	}

}

