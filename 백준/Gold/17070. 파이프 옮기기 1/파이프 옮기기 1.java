import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N+1][N+1];
		int[][] deltas = {{0, -1}, {-1, 0}, {-1, -1}};
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[3][N+1][N+1];
		dp[0][1][2] = 1;
		boolean[] flag = new boolean[2];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(map[i][j] == 1) continue;
				flag[0] = false;
				flag[1] = false;
				for (int d = 0; d < 2; d++) {  // 가로, 세로로 들어오는 경우의 수
					int nr = i + deltas[d][0];
					int nc = j + deltas[d][1];
					
					if(nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] == 1) continue;
					flag[d] = true;
					dp[d][i][j] += dp[d][nr][nc] + dp[2][nr][nc];
				}
				// 대각선으로 들어오는 경우의 수
				if(!flag[0] || !flag[1]) continue;

				int nr = i + deltas[2][0];
				int nc = j + deltas[2][1];
				if(nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] == 1) continue;
				dp[2][i][j] += dp[0][nr][nc] + dp[1][nr][nc] + dp[2][nr][nc];
			}
		}
		
		System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
	}

}