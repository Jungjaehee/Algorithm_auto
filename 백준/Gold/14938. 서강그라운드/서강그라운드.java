import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, r;
	static int[] items;
	static int[][] adjmat;
	static int maxCnt = Integer.MIN_VALUE;
	static int cnt;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		items = new int[n+1];
		adjmat = new int[n+1][n+1];
		
		int totalSum = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
			totalSum += items[i];
			for(int j=1; j<= n; j++)
				adjmat[i][j] = -1;
		}
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjmat[s][e] = w;
			adjmat[e][s] = w;
		}
		

		for(int i=1;i<=n;i++) {
			cnt = items[i];
			visited = new boolean[n+1];
			visited[i] = true;
			dfs(i, 0);
			maxCnt = Math.max(maxCnt, cnt);
			if(maxCnt == totalSum) break;
		}
		
		System.out.println(maxCnt);
		
	}
	
	static void dfs(int s, int dis) {
		for (int i = 1; i <=n; i++) {
			if(adjmat[s][i] == -1 || dis+adjmat[s][i] > m) continue;
			if(!visited[i]) 
				cnt += items[i];
			
			visited[i] = true;
			dfs(i, dis+adjmat[s][i]);
				
		}
	}

}