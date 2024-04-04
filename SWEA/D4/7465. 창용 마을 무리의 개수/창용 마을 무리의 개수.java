import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] adjList;
	static int[] size;
	static boolean[] isVisited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			adjList = new int[N+1][N];
			size = new int[N+1];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adjList[a][size[a]++] = b;
				adjList[b][size[b]++] = a;
			}
			
			isVisited = new boolean[N+1];
			int cnt = 0;
			for (int i = 1; i <=N; i++) {
				if(isVisited[i]) continue;
				isVisited[i] = true;
				dfs(i);
				cnt++;				
			}
			sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int i) {
		for (int j = 0; j < size[i]; j++) {
			if(isVisited[adjList[i][j]]) continue;
			isVisited[adjList[i][j]] = true;
			dfs(adjList[i][j]);
			
		}
	}

}