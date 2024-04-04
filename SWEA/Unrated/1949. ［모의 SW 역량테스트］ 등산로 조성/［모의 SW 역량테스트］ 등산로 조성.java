import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[][] isVisited;
	static int[][] map;
	static int maxCnt;
	static int N, K;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()) + 1;
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			int[][] maxIdx = new int[5][2];
			int maxVal = 0;
			int size = 0;
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxVal = Math.max(map[i][j], maxVal);
				}
			}
			
			for (int i = 1; i < N; i++) {
				for (int j = 1; j < N; j++) {
					if(maxVal == map[i][j]) {
						maxIdx[size][0] = i;
						maxIdx[size++][1] = j;
					}
				}
			}
			
			maxCnt = 0;
			for (int i = 0; i < size; i++) {
				isVisited = new boolean[N][N];
				isVisited[maxIdx[i][0]][maxIdx[i][1]] = true;
				dfs(maxIdx[i][0], maxIdx[i][1], false, 1);
			}
			
			sb.append("#").append(test_case).append(" ").append(maxCnt).append("\n");
		}
		System.out.println(sb);
	}
	static void dfs(int r, int c, boolean flag, int cnt) {
		maxCnt = Math.max(maxCnt, cnt);
		for (int d = 0; d < 4; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			
			if(nr < 1 || nr > N-1 || nc < 1 || nc > N-1 || isVisited[nr][nc]) continue;
			
			if(map[nr][nc] < map[r][c]) {
				isVisited[nr][nc] = true;
				dfs(nr, nc, flag, cnt+1);
				isVisited[nr][nc] = false;
			}
			else {
				int diff = map[nr][nc] - map[r][c] + 1;
				if(flag || diff > K) continue;

				isVisited[nr][nc] = true;
				int temp = map[nr][nc];
				map[nr][nc] -= diff;
				
				dfs(nr, nc, true, cnt+1);
				
				map[nr][nc] = temp;
				isVisited[nr][nc] = false;
			}
		}
	}

}
