import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] memory = new int[N];
		int[] cost = new int[N];
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			sum += cost[i];
		}
		
		int[][] dp = new int[N][sum+1];
		
		for (int j = 0; j <= sum; j++) {
			if(j - cost[0] >= 0)
				dp[0][j] = memory[0];
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j <= sum; j++) {
				if(j - cost[i] >= 0)
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-cost[i]] + memory[i]);
				
				dp[i][j] = Math.max(dp[i][j],  dp[i-1][j]);
			}
		}
		
		for (int i = 0; i <= sum; i++) {
			if(dp[N-1][i] >= M)
			{
				System.out.println(i);
				break;
			}
		}
	}

}