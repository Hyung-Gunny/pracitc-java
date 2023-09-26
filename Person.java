package chap04_Homework1_22112204_박형건;

public class Person 
{
	private String name;
	private int reg_ID;
	static int num_person;
	
	public Person() {}
	public Person(String nm, int reg_ID)
	{
		this.name=nm;
		this.reg_ID=reg_ID;
		
		num_person+=1;
	}
	
	public String toString()
	{
		String str=String.format("%s", this.name);
		return str;
		
	}
	protected String getName()
	{
		return this.name;
	}
	protected int getRegID() {
		return this.reg_ID;
	}

	
	
}
