package chap04_Homework2_22112204_박형건;

public class Teacher extends Person implements TeacherActivity 
{
	String school;
	String major;
	String lecture;
	
	
	public Teacher(String nm, int ag, String school, String major, String lecture) {
		super(nm, ag);
		this.school=school;
		this.major=major;
		this.lecture=lecture;
		// TODO Auto-generated constructor stub
	}
	public String getMajor()
	{
		return major;
	}
	
	public String  toString()
	{
		String str=String.format("Teacher (%s, %d,", this.getName(), this.getRegID());
		str+=String.format("%s, %s)", this.school, this.major);
		
		return str;
	}
	
	@Override
	public void listen() {
		System.out.printf("teacher (%s, %s) :: listening ...\n", this.getName(), this.major);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void talk() {
		System.out.printf("teacher (%s, %s) :: talking ...\n", this.getName(), this.major);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move() {
		System.out.printf("teacher (%s, %s) :: moveing ...\n", this.getName(), this.major);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void teach() {
		System.out.printf("teacher (%s, %s) :: teaching ...\n", this.getName(), this.major);
		// TODO Auto-generated method stub
		
	}
	

}
