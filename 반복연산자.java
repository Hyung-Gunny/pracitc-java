import java.util.Scanner;
class Main {

static public void q1()
{
  Scanner sc=new Scanner(System.in);
  System.out.print("양의 정수를 N을 입력 해주세요:");

  int N=sc.nextInt();
  
  for(int i=0; i<=N; i++)
    {
      for(int j=1; j<=i; j++)
        {
          System.out.print("*");
        }
      System.out.println();
    }
  

  
}

static public void q2()
{
  Scanner sc=new Scanner(System.in);
  System.out.print("양의 정수를 N을 입력 해주세요:");

  int N=sc.nextInt();
  
  for(int i=N; i>=1; i--)
    {
      for(int j=1; j<=i; j++)
        {
          System.out.print("*");
        }
      System.out.println();
    }


  
}

static public void q3()
{
  Scanner sc=new Scanner(System.in);
  System.out.print("양의 정수를 N을 입력 해주세요:");

  int N=sc.nextInt();
   for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= 2*i-1; j++) {
                System.out.print("*");
            }
            System.out.println();  
        }



  
  


  
}

static public void q4()
{
  Scanner sc=new Scanner(System.in);
  System.out.print("양의 정수를 N을 입력 해주세요:");

  int N=sc.nextInt();
  
  for(int i=N; i>=1; i--)
    {
      for(int j=1; j<=2*i-1; j++)
        {
          System.out.print("*");
        }
      System.out.println();
    }



  
}

static public void q5()
{
  Scanner sc=new Scanner(System.in);
  System.out.print("양의 정수를 N을 입력 해주세요:");

  int N=sc.nextInt();

  for(int i=1; i<=N; i++)
    {
      for(int j=1; j<=N-i; j++)
        {
          System.out.print(" ");
        }
      for(int k=1; k<=2*i-1; k++)
        {
          System.out.print("*");
        }
      System.out.println();
    }


  
}

static public void q6()
{
  Scanner sc=new Scanner(System.in);
  System.out.print("정답 입력:");
  int answer=sc.nextInt();

  int guess;
  int temp=0;
  do{
    System.out.print("입력:");
    guess=sc.nextInt();

    temp++;
    if(guess<answer)
    {
      System.out.print(guess + "보다 높습니다.");
    }
    else if(guess>answer)
    {
      System.out.print(guess+ "보다 낮습니다.");
    }
    else{
      System.out.print("정답입니다.");
    }



    
  }while(guess != answer);
  System.out.print("시도 횟수는 "+temp+"입니다");


  
}

static public void q7()
{
  Scanner sc=new Scanner(System.in);
  System.out.print("입력:");
  int x=sc.nextInt();

  for(int i=1; i<=6; i++)
    for(int j=1; j<=6; j++)
      for(int k=1; k<=6; k++)
        if(i+j+k==x)
        {
          System.out.println(i+" "+j+" "+k);
        }


  
}

static public void q8()
{
  Scanner sc=new Scanner(System.in);
  System.out.print("정수 입력 홀수만:");
  int x=sc.nextInt();

  for(int i=1; i<=x; i++)
    {
      for(int j=1; j<=x; j++)
      {
        if(i==j||i+j==x+1)
        {
          System.out.print("X");
        }
        else{
          System.out.print("O");
        }
      }
      System.out.println();
      
    }

  
}

  
  public static void main(String[] args) 
  {

    //q1();
    //q2();
    //q3();
    //q4();
    //q5();
    //q6();
    q7();
    //q8();
  }
}
