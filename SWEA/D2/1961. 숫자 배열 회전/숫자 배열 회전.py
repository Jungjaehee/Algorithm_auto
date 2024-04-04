import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++)
					arr[i][j] = sc.nextInt();
			
			System.out.println("#" + test_case);
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					System.out.print(arr[N-1-j][i]);
				}
				System.out.print(" ");
				for(int j=0;j<N;j++) {
					System.out.print(arr[N-1-i][N-1-j]);
				}
				System.out.print(" ");
				for(int j=0;j<N;j++) {
					System.out.print(arr[j][N-1-i]);
				}
				System.out.println();
			}
		}
	}
}