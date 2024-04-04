import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int infinite = 1001;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[][] network = new int[N+1][N+1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					network[i][j] = Integer.parseInt(st.nextToken());					
					network[i][j] = network[i][j] == 0? infinite:network[i][j];
				}
			}
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					if(i == k) continue;
					for (int j = 1; j <= N; j++) {
						if(j == k) continue;
						network[i][j] = Math.min(network[i][k] + network[k][j], network[i][j]);
					}
				}
			}
			
			int sum = 0;
			int minVal = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				sum = 0;
				for (int j = 1; j <= N; j++) {
					if(i == j) continue;
					sum += network[i][j];
				}
				minVal = Math.min(minVal, sum);
			}
			
			sb.append("#").append(test_case).append(" ").append(minVal).append("\n");
		}
		System.out.println(sb);
	}

}
