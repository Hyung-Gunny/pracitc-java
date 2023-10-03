package chap04_Homework2_22112204_박형건;

public class Student extends Person implements StudentActivity {
	private int st_id; // data member, student_id
	private double gpa; // data member, grade point average
	private String school;
	private String major;
	
	
	public Student(String nm, int reg_id, String school,String major, int st_id, double st_gpa) 
	{
		super(nm, reg_id);
		
		this.school=school;
		this.major=major;
		this.st_id=st_id;
		this.gpa=st_gpa;
		
		// TODO Auto-generated constructor stub
	}
	public int getStID() 
	{
		return st_id;
		
	}
	
	public double getGPA()
	{
		return gpa;
	}
	public String toString() 
	{
		String str=String.format("Student(%s, ", this.getName());
		str+=String.format("%d, %s,",this.getRegID(), this.school);
		str+=String.format(" %s, %d)", this.major, this.st_id);
		
		
	
		return str;
		
	}

	@Override
	public void listen()
	{
		System.out.printf("student (%s, %d) :: listening ...\n", this.getName(), this.st_id);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.printf("student (%s, %d) :: talking ...\n", this.getName(), this.st_id);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.printf("student (%s, %d) :: moveing ...\n", this.getName(), this.st_id);
	}

	@Override
	public void study() {
		// TODO Auto-generated method stub
		System.out.printf("student (%s, %d) :: studying ...\n", this.getName(), this.st_id);
		
	}
}
