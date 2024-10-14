import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] deltas = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};		
		
		char[][] map = new char[R][C];
		int[] start = new int[2];
		Queue<int[]> fires = new ArrayDeque<int[]>();
		for (int i = 0; i < R; i++) {
			String inputs = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = inputs.charAt(j);
				map[i][j] = ch;
				if(ch == 'J') {
					start[0] = i;
					start[1] = j;
					map[i][j] = '.';
				}
				else if(ch == 'F') {
					fires.offer(new int[] {i, j});
				}
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(start);
		boolean[][] isVisited = new boolean[R][C];
		isVisited[start[0]][start[1]] = true;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				int[] cur = queue.poll();
				if(map[cur[0]][cur[1]] == 'F') 
					continue;
				
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + deltas[d][0];
					int nc = cur[1] + deltas[d][1];
					
					if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1) {
						System.out.println(cnt+1);
						return;
					}
					if(isVisited[nr][nc] || map[nr][nc] != '.') continue;
					
					isVisited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
			
			int len = fires.size();
			for (int i = 0; i < len; i++) {
				int[] pos = fires.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = pos[0] + deltas[d][0];
					int nc = pos[1] + deltas[d][1];
					
					if(nr < 0 || nr > R-1 || nc <0 || nc > C-1) continue;
					if(map[nr][nc] != '.') continue;
					
					map[nr][nc] = 'F';
					fires.offer(new int[] {nr, nc});
				}
			}
			cnt++;
		}
		
		System.out.println("IMPOSSIBLE");
		
	}

}