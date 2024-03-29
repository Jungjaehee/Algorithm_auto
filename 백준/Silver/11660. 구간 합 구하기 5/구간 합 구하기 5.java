import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] sum = new int[N+1][N+1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				int num = sc.nextInt();
				sum[i][j] = sum[i][j-1] + sum[i-1][j] + num - sum[i-1][j-1];
			}
		}

		for (int k = 0; k < M; k++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			System.out.println(sum[x2][y2]-sum[x1-1][y2]-sum[x2][y1-1]+sum[x1-1][y1-1]);
		}
		
		
	}

}