/*
 * 파일명: chap01_Homework1
 * 프로그램의 목적 및 기능 : size로 지정된 개수의 정수형 데이터를 저장할 수 있는 배열를 생성하고 지정된 개수의 정수형 데이터를 한줄로 입력 받고,
					배열에 저장한 후, 이 배열의 reference를 반환하는 멤버 함수를 구현하여라
 * 프로그램 작성자 : 박형건 (2023/09/08)
 * 학번 : 22112204
 * 최종 수정일: 2023.09.08
 */
import java.util.Scanner;//java의 쓰일 것들을 import한다

public class intArray_Ex //class생성
{
	public static void print_array(int[] array, int size)//배열을 출력하는 함수
	{
	
		for(int i=0; i< size; i++)//반복문을 사용하여
		{
			System.out.print(array[i] + " ");//배열의 값을 출력하여 준다. 하나씩 출력하고 띄어준다
			if((i+1)%10==0)//만약 한줄의 10개가 넘어간다면
			{
				System.out.print("\n");//줄바꿈 하여 준다
			}
		}
		System.out.printf("\n");// 마지막으로 줄바꿈 하여 준다
	}
	public static int[] get_int_array(Scanner cin, int size)//배열을 받는 함수
	{
		// 세부 내용은 직접 구현할 것
		int[] array=new int[size];//배열을 입력 받는 사이즈만큼 크기로 만들어준다
		
		for(int i=0; i<size; i++)//배열을 size크기만큼 반복하여 준다
		{
			array[i]=cin.nextInt();//배열의 각 원소에 정수형 값을 받아준다
			
		}
		
		return array;//배열을 반환한다.
	}
	public static void insertion_sort(int[] array, int size)//정렬 하는 함수
	{
	/* sort given array with insertion_sort algorithm */
	// 세부 내용은 직접 구현할 것
		for (int i=0; i<size; i++)//size의 크기만큼 받아준다
		{
			int temps= array[i];//배열의 원소를 담아준다
			int j= i-1;//i-1째를 j에 담아준다
			
			while(j>=0 && array[j]>temps)//만약 j가 0보다 크거나 같고 배열의 j번째 값이 담아둔 값보다 크다면
			{
				array[j+1]=array[j];//j+1번째 값에 j의 값을 넣어주고
				j=j-1;//j-1을 하여 준다
			}
			array[j+1]=temps;// 배열에 담아둔 값을 넣어준다
		}
	}
	public static void main(String args[]) 
	{ 
	Scanner cin = new Scanner(System.in);//값을 받아주기 위해 scanner을 사용하여 준다
	System.out.print("Please input number of integers to process : ");
	int num_data = cin.nextInt();//정수형으로 값을 받아준다
	int[] int_array;//정수형 배열을 생성하여준다
	System.out.print("Please input " + num_data + " integers in a line, separated with space :"); 
	int_array = get_int_array(cin, num_data);//배열을 생성한다
	System.out.print("Input data : ");
	print_array(int_array, num_data);//데이터를 받아준다
	insertion_sort(int_array, num_data);//정렬한다
	System.out.print("Sorted input data : ");
	print_array(int_array, num_data);//배열을 프린트하여 준다.
	}

}
