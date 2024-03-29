import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  
 *
 */
public class Main {
	static int N;
	static char[][] colors;
	static boolean[][] isVisited;
	static int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		colors = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				colors[i][j] = str.charAt(j);
			}
		}
		isVisited = new boolean[N][N];
		int normal = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(isVisited[i][j]) continue;
				dfs(colors[i][j], i, j, true);
				normal++;
			}
		}
		
		
		isVisited = new boolean[N][N];
		int unnormal = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(isVisited[i][j]) continue;
				dfs(colors[i][j], i, j, false);
				unnormal++;
			}
		}
		
		System.out.println(normal + " " + unnormal);
	}
	static void dfs(char color, int r, int c, boolean isNormal) {
		if(r < 0 || r > N-1 || c < 0 || c > N-1 || isVisited[r][c] || (isNormal && colors[r][c] != color))
			return;
		if(!isNormal) {
			if(color == 'B' && colors[r][c] != 'B') return;
			if(color != 'B' && colors[r][c] == 'B') return;
		}
		
		isVisited[r][c] = true;
		for (int i = 0; i < 4; i++) {
			dfs(color, r+deltas[i][0], c+deltas[i][1], isNormal);
		}
	}
}