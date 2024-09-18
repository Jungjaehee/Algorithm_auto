import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int maxVal = 0;
	static int sum = 0;
	static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1},{0, -1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r-1][c-1] = 1;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue;
				sum = 1;
				dfs(i, j);
				maxVal = Math.max(maxVal, sum);
			}
		}
		System.out.println(maxVal);
	}
	
	static void dfs(int r, int c) {
        map[r][c] = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			
			if(nr < 0 || nr > N-1 || nc <0 || nc > M-1 || map[nr][nc] == 0) continue;
			sum++;
			
			dfs(nr, nc);
		}
	}

}