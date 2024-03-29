import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] sum;
	static int[] colors = new int[2];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		sum = new int[N+1][N+1];
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				sum[i][j] = Integer.parseInt(st.nextToken());
				sum[i][j] += sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
			}
		}
		splitSearch(1, 1, N);
		System.out.println(colors[0]);
		System.out.println(colors[1]);
	}
	private static void splitSearch(int r, int c, int n) {
		int partSum = sum[r+n-1][c+n-1] - sum[r+n-1][c-1] - sum[r-1][c+n-1] + sum[r-1][c-1];
		if(n == 1 || partSum == n*n || partSum == 0) {
			colors[partSum/(n*n)]++;
			return;
		}
		
		int newN = n/2;
		splitSearch(r, c, newN);
		splitSearch(r+newN, c, newN);
		splitSearch(r, c+newN, newN);
		splitSearch(r+newN, c+newN, newN);
	}

}