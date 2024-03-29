import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] T = new int[N];
		int[] P = new int[N];
		int[] dp = new int[N+1];
		
		/* input */
		for(int i=0;i<N;i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
		
		for(int i=N-1;i>-1;i--) {  
			int nxt_days = i + T[i];  
			if(nxt_days > N) dp[i] = dp[i+1]; 
			else { 
				dp[i] = Math.max(P[i] + dp[nxt_days], dp[i+1]);
			}
		}
		System.out.println(dp[0]);	
	}

}
