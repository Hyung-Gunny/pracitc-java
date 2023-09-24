package chap03_Homework1_22112204_박형건;

public class IntStack
{
	private int[] int_array;
	private int max_stack_size;
	private int stack_top = -1; // initial position
	public IntStack(int max_stack_size)
	{ // constructor
		
	 // 직접 구현할 것
		this.max_stack_size=max_stack_size;
		int_array=new int[max_stack_size];
	}
	public int push(int entry)
	{
		if(isFull())
		{
			System.out.println("stack is full");
			
			return -1;
		}
		else
		{
			int_array[++stack_top]=entry;
			return entry;
			
		}
		
	
	}
	public int pop() 
	{
		if(isEmpty()) 
		{
			return -1;
		}
		else
		{
			int popValue=int_array[stack_top];
			stack_top--;
			return popValue;
		}
		
	
	}
	public boolean isEmpty() 
	{
		
		return stack_top==-1;
		
		
	}
	public boolean isFull() 
	{
		return stack_top==max_stack_size-1;
		
	}
	public void print() 
	{
		 if (isEmpty()) 
		 {
			 System.out.println("");
	     } 
		 else 
	     {
			 System.out.print("Stack: ");
	         for (int i = 0; i <= stack_top; i++) 
	         {
	             System.out.print(int_array[i] + " ");
	         }
	         System.out.println();
	      }
	}

}
