import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] forest = new char[R][C];
		int[] start = {0 , 0};
		int[] end = {0, 0};
		Queue<int []> water = new ArrayDeque<>();
		Queue<int []> hed = new ArrayDeque<>();
		int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				forest[i][j] = str.charAt(j);
				if(forest[i][j] == 'S') start = new int[] {i, j};
				else if(forest[i][j] == 'D') end = new int[] {i, j};
				else if(forest[i][j] == '*') water.offer(new int[] {i, j});
			}
		}
		
		boolean[][] isVisited = new boolean[R][C];
		
		isVisited[start[0]][start[1]] = true;
		hed.offer(start);
		forest[start[0]][start[1]] = '.';
		int []cur = start;
		int cnt = 0;
		
		A: while(!hed.isEmpty()) {			
			int size = water.size();
			while(size-- > 0) {
				cur = water.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + deltas[d][0];
					int nc = cur[1] + deltas[d][1];
					
					if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1 || forest[nr][nc] != '.') continue;
					forest[nr][nc] = '*';
					water.offer(new int[] {nr, nc});				
				}
			}
			
			size = hed.size();
			while(size -- > 0) {
				cur = hed.poll();
				if(cur[0] == end[0] && cur[1] == end[1]) break A;
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + deltas[d][0];
					int nc = cur[1] + deltas[d][1];
					
					if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1 || isVisited[nr][nc] || forest[nr][nc] == 'X' || forest[nr][nc] == '*') continue;
					hed.offer(new int[] {nr, nc});
					isVisited[nr][nc] = true;
					
				}
			}
			cnt++;
		}
		System.out.println(forest[cur[0]][cur[1]] == 'D'? cnt: "KAKTUS");
	}
	
}