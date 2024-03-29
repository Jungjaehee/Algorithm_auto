import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 
 */
public class Main {
	static int R, C;
	static char[][] alphabet;
	static int[][] deltas = {{0, -1},{0, 1},{1, 0},{-1, 0}};
	static int maxCnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alphabet = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String str= br.readLine();
			for (int j = 0; j < C; j++) {
				alphabet[i][j] = str.charAt(j);
			}
		}
		
		dfs(0, 0, 0, 0);
		System.out.println(maxCnt);
	}

	private static void dfs(int r, int c, int flag, int cnt) {
		if(r < 0 || r > R-1 || c < 0 || c > C-1 || (flag & 1 << (alphabet[r][c])) != 0) {
			maxCnt = Math.max(maxCnt, cnt);
			return;			
		}
		
		flag |= 1 << (alphabet[r][c]);
		for (int i = 0; i < 4; i++) {
			int nr = r + deltas[i][0];
			int nc = c + deltas[i][1];
			
			dfs(nr, nc, flag, cnt+1);
		}
	}

}