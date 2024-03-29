import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine());
		int[] val = new int[N+1];
		for (int i = 1; i <= N; i++) {
			val[i] = Integer.parseInt(br.readLine());
		}
		int[] dp = new int[N+1];
		dp[1] = val[1];
		if(N > 1) dp[2] = val[1] + val[2];
		
		for (int i = 3; i < N+1; i++) {
			dp[i] = Math.max(Math.max(dp[i-1], dp[i-2]+val[i]), dp[i-3]+val[i-1]+val[i]);
		}
		System.out.println(dp[N]);
	}
}