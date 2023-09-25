package chap03_Homework2_22112204_박형건;
import java.util.Scanner;

public class Compare_Sorting_Algorithms 
{
	public static int[] genBigRandIntArray(int size, int offset)
	{
		int[]bigIntArray=new int[size];
		int i,j,temp;
		for(i=0; i<size; i++)
		{
			bigIntArray[i]=i+offset;
		}
		
		return bigIntArray;
		// 강의자료를 참조하여 직접 구현할 것
	}
	public static void printBigArraySample(int[] bigArray, int per_line, int sample_lines)
	{
		// 강의자료를 참조하여 직접 구현할 것
		int last_block_start;
		int size=bigArray.length, count=0;
		
		for(int i=0; i<sample_lines; i++)
		{
			for(int j=0; j<per_line; j++)
			{
				if(count>size)
				{
					System.out.println();
					return;
				}
				System.out.printf("%8d", bigArray[count]);
				count++;
			}
			System.out.println();
		}
		if(count<(size-per_line*sample_lines))
		{
			count=size-per_line*sample_lines;
		}
		
		System.out.println();
		System.out.printf("...........");
		System.out.println();
		
		for(int i=0; i<sample_lines; i++)
		{
			for(int j=0; j<per_line; j++)
			{
				if(count>size)
				{
					System.out.println();
					return;
				}
				System.out.printf("%8d ", bigArray[count]);
				count++;
			}
			System.out.println();
			
		}
		System.out.println();
	}
	public static void shuffle_array(int[]array)
	{
		int j,temp,size=array.length;
		for(int i=0; i<size; i++)
		{
			j=(int)(Math.random()*size);
			if(j==i)
			{
				continue;
			}
			temp=array[i];
			array[i]=array[j];
			array[j]=temp;
		}
	}
	public static void selection_sort(int[] array)
	{
		int temp, min_idx,size=array.length;
		for(int i=0; i<size-1; i++)
		{
			min_idx=i;
			for(int j=i+1; j<size; j++)
			{
				if(array[min_idx]>array[j])
				{
					min_idx=j;

				}
			}
			if(min_idx != i)
			{
				temp=array[i];
				array[i]=array[min_idx];
				array[min_idx]=temp;
			}
		}
		
	}
	public static void insertion_sort(int[] array)
	{
		int temp, in, out, size=array.length;
		
		for(out=1; out<=(size-1); out++)
		{
			temp=array[out];
			in=out;
			for(; in>0&&array[in-1]>=temp; --in)
			{
				array[in]=array[in-1];
				
			}
			array[in]=temp;
		}
	}
	public static void merge_sort(int[] array)
	{
		mergeSort(array, 0, array.length-1);
		
	}
	public static void mergeSort(int[] array, int left, int right )
	{
		if(left<right)
		{
			int mid=(left+right)/2;
			mergeSort(array,left,mid);
			mergeSort(array, mid+1, right);
			merge(array, left, mid, right);
		}
	}
	
	public static void merge(int[] array, int left, int mid, int right)
	{
		int num1=mid-left+1;
		int num2=right-mid;
		
		int[] leftArray=new int[num1];
		int[] rightArray=new int[num2];
		
		for(int i=0; i<num1; i++)
		{
			leftArray[i]=array[left+i];
		}
		for(int i=0; i<num2; i++)
		{
			rightArray[i]=array[mid+1+i];
		}
		
		int i=0, j=0, k=left;
		
		while(i<num1&&j<num2)
		{
			if(leftArray[i]<=rightArray[j])
			{
				array[k]=leftArray[i];
				i++;
				
			}
			else
			{
				array[k]=rightArray[j];
				j++;
			}
			k++;
		}
		
		while(i<num1)
		{
			array[k]=leftArray[i];
			i++;
			k++;
		}
		
		while(j<num2)
		{
			array[k]=rightArray[j];
			j++;
			k++;
		}
	}
	public static void quick_sort(int[] array)
	{
		_quickSort(array, array.length, 0, array.length-1);
	}
	public static int _partition(int[] array, int size, int left, int right, int pivotIndex)
	{
		int pivotValue;
		int newPI;
		int temp,i;
		
		pivotValue=array[pivotIndex];
		
		temp=array[pivotIndex];
		array[pivotIndex]=array[right];
		array[right]=temp;
		
		newPI=left;
		
		for(i=left; i<=(right-1); i++)
		{
			if(array[i]<=pivotValue)
			{
				temp=array[i];
				array[i]=array[newPI];
				array[newPI]=temp;
				newPI=newPI+1;
			}
		}
		
		temp=array[newPI];
		array[newPI]=array[right];
		array[right]=temp;
		
		return newPI;
		// 강의자료를 참조하여 직접 구현할 것
	}
	public static void _quickSort(int[] array, int size, int left, int right)
	{
		// 강의자료를 참조하여 직접 구현할 것
		int pI, newPI;
		
		if(left>=right)
		{
			return;
		}
		pI=(left+right)/2;
		
		newPI=_partition(array,size,left,right,pI);
		
		if(left<(newPI-1))
		{
			_quickSort(array,size,left,newPI-1);
			
		}
		if((newPI+1)<right)
		{
			_quickSort(array,size,newPI+1, right);
			
		}
	}
	public static void main(String[] args) 
	{ 
		Scanner cin = new Scanner(System.in); 
		int big_size;
		int offset = 0;
		int[] bigRandIntArray;
		long t1, t2, elapsed_time_ms;
		while (true)
		{
			System.out.print("input big_size (> 32767) to compare performances of (quick, selection) sorting algorithms (0 to terminate) : "); big_size = cin.nextInt();
			if (big_size == 0)
				break;
			bigRandIntArray = genBigRandIntArray(big_size, offset);
			
			System.out.printf("Before quick_sorting, size = %d, offset = %d\n", big_size, offset);
			printBigArraySample(bigRandIntArray, 10, 2);
			t1 = System.currentTimeMillis();
			quick_sort(bigRandIntArray);
			t2 = System.currentTimeMillis();
			elapsed_time_ms = t2 - t1;
			System.out.printf("After quick_sorting, size = %d, offset = %d\n", big_size, offset); 
			printBigArraySample(bigRandIntArray, 10, 2);
			System.out.printf("Quick_sort() for intArray(size=%d) took %d milliseconds\n", big_size, elapsed_time_ms);
			
			shuffle_array(bigRandIntArray);
			System.out.printf("Before insertion_sorting, size = %d, offset = %d\n", big_size, offset); 
			printBigArraySample(bigRandIntArray, 10, 2);
			t1 = System.currentTimeMillis();
			insertion_sort(bigRandIntArray);
			t2 = System.currentTimeMillis();
			elapsed_time_ms = t2 - t1;
			System.out.printf("After insertion_sorting, size = %d, offset = %d\n", big_size, offset);
			printBigArraySample(bigRandIntArray, 10, 2);
			System.out.printf("Insertion_sort() for intArray(size=%d) took %d milliseconds\n", big_size, elapsed_time_ms);
			
			shuffle_array(bigRandIntArray);
			System.out.printf("Before selection_sorting, size = %d, offset = %d\n", big_size, offset);
			printBigArraySample(bigRandIntArray, 10, 2);
			t1 = System.currentTimeMillis();
			selection_sort(bigRandIntArray);	
			t2 = System.currentTimeMillis();
			elapsed_time_ms = t2 - t1;
			System.out.printf("After selection_sorting, size = %d, offset = %d\n", big_size, offset); 
			printBigArraySample(bigRandIntArray, 10, 2);
			System.out.printf("Selection_sort() for intArray(size=%d) took %d milliseconds\n", big_size, elapsed_time_ms);
			
			shuffle_array(bigRandIntArray);
			System.out.printf("Before merge_sorting, size = %d, offset = %d\n", big_size, offset);
			printBigArraySample(bigRandIntArray, 10, 2);
			t1=System.currentTimeMillis();
			merge_sort(bigRandIntArray);
			t2=System.currentTimeMillis();
			elapsed_time_ms=t2-t1;
			System.out.printf("After merge_sorting, size = %d, offset = %d\n",big_size, offset);
			printBigArraySample(bigRandIntArray, 10, 2);
			System.out.printf("Merge_sort() for intArray(size=%d) took %d milliseconds\n", big_size, elapsed_time_ms);
		
		}
	}
	

}
