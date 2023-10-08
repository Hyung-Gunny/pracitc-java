package chap05_Homework1_22112204_박형건;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StudentArray 
{
	private Student[] students;
    private int num_students;
    
	public StudentArray() 
	{
		
		students = new Student[100]; // 최대 100명의 학생을 저장할 수 있는 배열 생성
        num_students = 0;
		// TODO Auto-generated constructor stub
	}
	
	 public void fget_students(Scanner fin) 
	 {
		 int numStudents = fin.nextInt();
		    fin.nextLine(); // 줄 바꿈 문자를 읽어서 버림

		    // 학생 데이터를 읽어옴
		    for (int i = 0; i < numStudents; i++) {
		        String line = fin.nextLine();
		        String[] data = line.split(" ");

		        if (data.length == 6) {
		            int st_id = Integer.parseInt(data[1]);
		            String name = data[0];
		            int reg_id = Integer.parseInt(data[2]);
		            String dept = data[3];
		            String school = data[4];
		            double gpa = Double.parseDouble(data[5]);

		            students[num_students] = new Student(name, reg_id, st_id, dept, school, gpa);
		            num_students++;
		        }
		    }
	 }
	 public void print_students()
	 {
	        for (int i = 0; i < num_students; i++)
	        {
	            System.out.println(students[i]);
	        }
	 }
	 public void fprint_students(FileWriter fout) throws IOException
	 {
	        for (int i = 0; i < num_students; i++) {
	        	String studentStr = students[i].toString();
	            fout.write(studentStr + "\n"); // 파일에 출력
	           
	        }
	 }
	 public void sort(String key_attr, String sorting_order) {
		    if (key_attr.equals("st_id")) {
		        if (sorting_order.equals("increasing")) {
		            // 학생 ID 오름차순 정렬 (삽입정렬)
		            for (int i = 1; i < num_students; i++) {
		                Student currentStudent = students[i];
		                int j = i - 1;
		                while (j >= 0 && students[j].getSt_id() > currentStudent.getSt_id())
{
		                    students[j + 1] = students[j];
		                    j--;
		                }
		                students[j + 1] = currentStudent;
		            }
		        } else if (sorting_order.equals("decreasing")) {
		            // 학생 ID 내림차순 정렬 (삽입정렬)
		            for (int i = 1; i < num_students; i++) {
		                Student currentStudent = students[i];
		                int j = i - 1;
		                while (j >= 0 && students[j].getSt_id() < currentStudent.getSt_id()) {
		                    students[j + 1] = students[j];
		                    j--;
		                }
		                students[j + 1] = currentStudent;
		            }
		        }
		    } else if (key_attr.equals("name")) {
		        if (sorting_order.equals("increasing")) {
		            // 이름 오름차순 정렬 (삽입정렬)
		            for (int i = 1; i < num_students; i++) {
		                Student currentStudent = students[i];
		                int j = i - 1;
		                while (j >= 0 && students[j].getName().compareTo(currentStudent.getName()) > 0) {
		                    students[j + 1] = students[j];
		                    j--;
		                }
		                students[j + 1] = currentStudent;
		            }
		        } else if (sorting_order.equals("decreasing")) {
		            // 이름 내림차순 정렬 (삽입정렬)
		            for (int i = 1; i < num_students; i++) {
		                Student currentStudent = students[i];
		                int j = i - 1;
		                while (j >= 0 && students[j].getName().compareTo(currentStudent.getName()) < 0) {
		                    students[j + 1] = students[j];
		                    j--;
		                }
		                students[j + 1] = currentStudent;
		            }
		        }
		    } 
		    else if (key_attr.equals("GPA")) {
		        if (sorting_order.equals("increasing")) {
		            // GPA 오름차순 정렬 (삽입정렬)
		            for (int i = 1; i < num_students; i++) {
		                Student currentStudent = students[i];
		                int j = i - 1;
		                while (j >= 0 && students[j].getGPA() > currentStudent.getGPA()) {
		                    students[j + 1] = students[j];
		                    j--;
		                }
		                students[j + 1] = currentStudent;
		            }
		        } else if (sorting_order.equals("decreasing")) {
		            // GPA 내림차순 정렬 (삽입정렬)
		            for (int i = 1; i < num_students; i++) {
		                Student currentStudent = students[i];
		                int j = i - 1;
		                while (j >= 0 && students[j].getGPA() < currentStudent.getGPA()) {
		                    students[j + 1] = students[j];
		                    j--;
		                }
		                students[j + 1] = currentStudent;
		            }
		        }
		    }

		    
	 }
}


