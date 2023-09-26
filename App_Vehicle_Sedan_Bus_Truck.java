package chap04_Homework1_22112204_박형건;

public class App_Vehicle_Sedan_Bus_Truck
{
	public static void main(String[] args) 
	{
		Person[] drivers = {new Person("Kim", 11111), new Person("Park", 22222), new Person("Choi", 33333)};
		Vehicle[] vehicles = {
				new Sedan("Red", "SUV", 3000, drivers[0], 5),
				new Bus("Yellow", "CityBus", 50000, drivers[1], 50),
				new Truck("Black", "CagoTruck", 100000, drivers[2], 200)
		};
		System.out.print("Vehicles : ");
		for (Vehicle v : vehicles)
		{
			System.out.print(v);
			if (v != vehicles[vehicles.length -1])
				System.out.print(", "); 
		}
		System.out.println();
		System.out.println("\nDriving of Vehicles :");
		for (Vehicle v : vehicles)
		{
			System.out.printf("%s :: ", v);
			v.forward(100);
			v.turn(90);
			v.stop(); 
		} 
	}

}

