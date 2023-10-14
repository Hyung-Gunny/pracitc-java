import java.util.Scanner;

class Main {


  static void q1()
  {

            Scanner scanner = new Scanner(System.in);
            int[] numbers = new int[5];

            
      System.out.print("정수를 입력하세요: ");
            for (int i = 0; i < 5; i++)
            {
                
                numbers[i] = scanner.nextInt();
            }

           
            int sum = 0;
            for (int j=0; j<5; j++)
            {
                sum += numbers[j];
            }
            double average = (double) sum / 5;

        
            System.out.print("평균보다 큰 정수: ");
            for (int k=0; k<5; k++)
              {
                if (numbers[k]>average)
                {
                    System.out.print(numbers[k] + " "+numbers[k]);
                }
            }

            scanner.close();





  }
  static void q2()
  {
    Scanner sc=new Scanner(System.in);
    System.out.printf("문자열을 입력하세요: ");
    String input=sc.next();

    int count=0;
    for(int i=0; i<input.length()-2; i++){
      if(input.substring(i, i+3).equals("cat"))
      {
        count++;
      }
         
    }

     System.out.println(count);

  }
  static void q3()
  {
    Scanner sc=new Scanner(System.in);
    System.out.printf("정수 다섯개 입력:");
    int[] num=new int[5];

    for(int i=0; i<5; i++){
      num[i]=sc.nextInt();
    }
    int max=num[0];
    int max2=num[1];

    for(int j=0; j<5; j++){
      if(num[j]>max)
      {
        max2=max;
        max=num[j];
      }
      else if(num[j]>max2 && max!= num[j]){
        max2=num[j];
      }

      
    }
    System.out.println(max+"and " + max2);
      

    
      

  }
  static void q4()
  {
    Scanner scanner=new Scanner(System.in);
    int[] num=new int[10];

    System.out.printf("정수 입력:");
    for(int i=0; i<num.length; i++){
      num[i]=scanner.nextInt();
    }

    int count1=0, count2=0, count3=0;
    for(int j=0; j<num.length; j++){
      if(num[j]==1)
      {
        count1++;
      }
      else if(num[j]==2)
      {
        count2++;
      }
      else
      {
        count3++;
      }
      
    }

    System.out.println("1의개수:");
    for(int k=0; k<count1; k++)
      {
        System.out.printf("*");
      }
    System.out.println();
    System.out.println("2의개수:");
      for(int k=0; k<count2; k++)
        {
          System.out.printf("*");
        }
    System.out.println();

    System.out.println("3의개수:");
    for(int k=0; k<count3; k++)
      {
        System.out.printf("*");
      }


  }
  static void q5()
  {
    Scanner scanner=new Scanner(System.in);

    System.out.printf("영어문자를 입력해주세요:");
    String input=scanner.next();

    for(int i=0; i<input.length(); i++){
      for(int j=0; j<input.length();j++){
        int index=(input.length()+j-i)%input.length();
        System.out.print(input.charAt(index));
      }
      System.out.println();
      
    }
    scanner.close();


  }
  static void q6()
  {
    Scanner scanner=new Scanner(System.in);
    String [] answer=new String[3];
    String guess;

    System.out.printf("정답:");
    for(int i=0; i<3; i++)
      {
        answer[i]=scanner.next();
      }

    System.out.printf("%s%s%s -> 정답\n",answer[0],answer[1],answer[2]);

    String [] answerguess={"_", "_", "_"};
    int count=0;

    System.out.println("입력:");
    while(count<5)
      {
        guess=scanner.next();
        if(guess.equals(answer[0]))
        {
           answerguess[0]=answer[0];
           System.out.printf("%s%s%s\n", answerguess[0], answerguess[1],answerguess[2]);
           count++;
        }
        else if(guess.equals(answer[1]))
        {
          answerguess[1]=answer[1];
          System.out.printf("%s%s%s\n", answerguess[0],answerguess[1],answerguess[2]);
          count++;
        }
        else if(guess.equals(answer[2]))
        {
          answerguess[2]=answer[2];
          System.out.printf("%s%s%s\n", answerguess[0],answerguess[1],answerguess[2]);
          count++;
          
        }
        else{
          System.out.printf("%s%s%s\n",answerguess[0],answerguess[1],answerguess[2]);
          count++;
        }
      
      }
        
          

      
  }

      
  public static void main(String[] args) {

   //q1();
   //q2();
   //q3();
    //q4();
    //q5();
    q6();
    
    
  }
}
