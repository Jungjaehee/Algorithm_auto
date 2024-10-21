import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] student;
	static boolean[] isVisited, currentVisited;
	static int cycleCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			n = Integer.parseInt(br.readLine());
			student = new int[n+1];
			isVisited = new boolean[n+1];
			currentVisited = new boolean[n+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= n; i++) {
				student[i] = Integer.parseInt(st.nextToken());
			}
			
			cycleCnt = 0;
			for (int i = 1; i <= n; i++) {
				if(isVisited[i]) continue;
				dfs(i);
				
			}
			
			sb.append(n-cycleCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int i) {
		if(isVisited[i]) return;
		if(currentVisited[i]) {
			isVisited[i] = true;
			cycleCnt++;
		}
		
		currentVisited[i] = true;
		dfs(student[i]);
		isVisited[i] = true;
		currentVisited[i] = false;
	}

}