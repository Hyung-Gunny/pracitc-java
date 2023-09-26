

package chap04_Homework1_22112204_박형건;

public class Sedan extends Vehicle
{
	private int num_seats;
	
	public Sedan(String color, String type, int eng_capa, Person driver, int num_seats) 
	{
		super(color, type, eng_capa, driver);
		// TODO Auto-generated constructor stub
		this.num_seats=num_seats;
	}
	
	public String toString()
	{
		return super.getColor() + " " + super.getVtype() + " (driver : "+super.getDriver()+")";
	}
	

	@Override
	public void forward(int speed) 
	{
		// TODO Auto-generated method stub
		System.out.printf("moving forward at speed %d Km/h; ", speed);
		
	}

	@Override
	public void turn(int angle) 
	{
		// TODO Auto-generated method stub
		System.out.printf("turning %d agree; ", angle);
		
	}

	@Override
	public void stop() 
	{
		// TODO Auto-generated method stub
		System.out.printf("stopping;");
		System.out.println();
		
	}
	
}

