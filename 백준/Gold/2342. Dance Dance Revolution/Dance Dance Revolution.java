import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/**
	 * DP[왼발][오른발][idx]
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] command = br.readLine().split(" ");
		int N = command.length - 1;
		
		int[][][] dp = new int[5][5][N+1];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < N+1; k++) {
					dp[i][j][k] = Integer.MAX_VALUE-5;
				}
			}
		}
		dp[0][0][0] = 0;
		
		for(int i=1; i <= N;i++) {
			int next = Integer.parseInt(command[i-1]);
			for(int l=0;l<5;l++) {
				for(int r = 0; r<5;r++) {
					dp[l][next][i] = Math.min(dp[l][next][i], dp[l][r][i-1] + getScore(r, next));
					dp[next][r][i] = Math.min(dp[next][r][i], dp[l][r][i-1] + getScore(l, next));
				}
			}
			
		}
		
		int answer = Integer.MAX_VALUE;
		int last = Integer.parseInt(command[N-1]);
		for(int i=0;i<5;i++) {
			answer = Math.min(answer, dp[last][i][N]);
			answer = Math.min(answer, dp[i][last][N]);
		}
		
		System.out.println(answer);
		
	}
	static int getScore(int prev, int next) {
		if(prev == next) return 1;
		if(prev == 0) return 2;
		if((prev % 2) == (next % 2)) return 4;
		return 3;
	}
}