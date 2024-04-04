import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	/**
	 * [ 문제 해석 ]
	 *  N^2 방 -> N X N 형태
	 *  배열 안 수 -> 1 ~ N^2 수
	 * 	상하좌우 이동 -> 방 존재하고 +1인 방 
	 *  어떤 수가 적힌 방에 있어야 가장 많은 개수의 방 이동 가능?
	 * 	
	 * 	N 최대: 1000	
	 *  N^3 -> 1000,000,000..?
	 * [ 문제 해결 프로세스 ]
	 *  N^2 반복문 돌면서 start 저장, DFS 호출, DFS 끝난 후 cnt 최대 구하기
	 *  
	 *  DFS
	 *  경계 체크 & 그 전 수보다 1큰지 확인 => 계속 이동 (cnt+1)
	 * 
	 */
	static int[][] deltas = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int[][] map;
	static int cnt;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
				}
			}
			
			int start;
			int maxCnt = Integer.MIN_VALUE;
			int minStart = N+1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					start = map[i][j];
					cnt = 1;
					dfs(start, i+deltas[0][0], j+deltas[0][1]);
					dfs(start, i+deltas[1][0], j+deltas[1][1]);
					dfs(start, i+deltas[2][0], j+deltas[2][1]);
					dfs(start, i+deltas[3][0], j+deltas[3][1]);
					if(maxCnt == cnt) {
						minStart = Math.min(minStart, start);
					}
					else if(maxCnt < cnt) {
						maxCnt = cnt;
						minStart = start;
					}
				}
			}
			sb.append(minStart).append(" ").append(maxCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int pre, int x, int y) {
		if(x < 0 || x > N-1 || y < 0 || y > N-1 || (pre+1) != map[x][y])
			return;
		cnt++;
		dfs(map[x][y], x+deltas[0][0], y+deltas[0][1]);
		dfs(map[x][y], x+deltas[1][0], y+deltas[1][1]);
		dfs(map[x][y], x+deltas[2][0], y+deltas[2][1]);
		dfs(map[x][y], x+deltas[3][0], y+deltas[3][1]);
		
	}

}