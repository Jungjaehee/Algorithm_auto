import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			int[][] graph = new int[N+1][N+1];
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a][b] = 1;
				graph[b][a] = 2;
			}
			
			
			for (int k = 1; k < N+1; k++) {
				for (int i = 1; i < N+1; i++) {
					if(k == i) continue;
					for (int j = 1; j < N+1; j++) {
						if(i == j) continue;
						if(graph[i][k] == 1 && graph[k][j] == 1) {
							graph[i][j] = 1;
							graph[j][i] = 2;
						}
					}
				}
			}
			
			int cnt = 0;
			for (int i = 1; i < N+1; i++) {
				int len = 0;
				for (int j = 1; j < N+1; j++) {
					if(graph[i][j] != 0) len++;
				}
				if(len == N-1) cnt++;
			}
			
			sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
			
		}
		
		System.out.println(sb);
	}
	

}