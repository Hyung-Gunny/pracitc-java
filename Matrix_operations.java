package chap02_Homework2_22112204_박형건;

public class Matrix_operations 
{
	public static void printMtrx(String mtrx_name, int n_row, int n_col, double mtrx_data[][])
	{
	// 직접 구현할 것
		System.out.printf("%s=\n", mtrx_name);
		for(int i=0; i<n_row; i++)
		{
			for(int j=0; j<n_col; j++)
			{
				System.out.printf("%5.1f", mtrx_data[i][j]);
				
			}
			System.out.println();
		}
		System.out.println();
	}
	public static double[][] addMtrx(int n_row, int n_col, double mA_data[][], double mB_data[][])
	{
		double [][]r=new double[n_row][n_col];
		for(int i=0; i<n_row; i++)
		{
			for(int j=0; j<n_col; j++)
			{
				r[i][j]=mA_data[i][j]+mB_data[i][j];
				
			}
			
		}
		
		return r;
	// 직접 구현할 것
	}
	public static double[][] subMtrx(int n_row, int n_col, double mA_data[][], double mB_data[][])
	{
		double [][] r= new double[n_row][n_col];
		
		for(int i=0; i<n_row; i++)
		{
			for(int j=0; j<n_col; j++)
			{
				r[i][j]=mA_data[i][j]-mB_data[i][j];
				
			}
			
		}
		return r;
	// 직접 구현할 것
	}
	public static double[][] mulMtrx(int nA_row, int nA_col, int nB_row, int nB_col, double mA_data[][], double mB_data[][])
	{
		double [][]r=new double[nA_row][nB_col];
		
		for(int i=0; i<nA_row; i++)
		{
			for(int j=0; j<nB_col; j++)
			{
				double sum=0.0;
				for(int k=0; k<nA_col; k++)
				{
					sum+=mA_data[i][k]*mB_data[k][j];
				}
				r[i][j]=sum;
			}
		}
		return r;
	// 직접 구현할 것
	}
	public static void main(String[] args)
	{
	int nA_row = 3, nA_col = 5;
	int nB_row = 3, nB_col = 5;
	int nC_row = 5, nC_col = 3;
	double mA[][] = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
	double mB[][] = {{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}};
	double mAddAB[][];
	double mSubAB[][];
	printMtrx("mA", nA_row, nA_col, mA);
	printMtrx("mB", nB_row, nB_col, mB);
	mAddAB = addMtrx(nA_row, nA_col, mA, mB);
	printMtrx("mAddAB", nA_row, nA_col, mAddAB);
	mSubAB = subMtrx(nA_row, nA_col, mA, mB);
	printMtrx("mSubAB", nA_row, nA_col, mSubAB);
	double mC[][] = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {0, 0, 0}, {0, 0, 0}};
	double mMulAC[][];
	printMtrx("mA", nA_row, nA_col, mA);
	printMtrx("mC", nC_row, nC_col, mC);
	mMulAC = mulMtrx(nA_row, nA_col, nC_row, nC_col, mA, mC);
	printMtrx("mMulAC", nA_row, nC_col, mMulAC);
	}

}
