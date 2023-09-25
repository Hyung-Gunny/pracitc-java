package chap01_Homework2_22112204_박형건;

import java.util.Scanner;

public class Dyn_Fibonacci {
    final static int max_n = 1000;
    static double[] fb_n_tbl = new double[max_n];
    static boolean fb_n_tbl_initialized = false;

    public static double yn_Fibonacci(int n) 
    {
        
        if (!fb_n_tbl_initialized) {
            //학생들이 채워야 할 부
        }

     
        if (n < max_n && fb_n_tbl[n] != 0) {
            return fb_n_tbl[n];
        }


        for (int i = 2; i <= n; i++) {
           //학생들이 채워야 할 부분 
        }

        return fb_n_tbl[n];
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n;
        double fibo_n;
        
        while (true) {
            System.out.print("input n (>= 0) to find 0 ~ nth fibo_n (-1 to terminate) : ");
            n = cin.nextInt();
            
            if (n == -1)
                break;
            
            for (int i = 0; i <= n; i++) {
                fibo_n = yn_Fibonacci(i);
                System.out.printf("%3d-th Fibonacci Series = %25.0f\n", i, fibo_n);
            }
        }
        
        cin.close();
    }
}
