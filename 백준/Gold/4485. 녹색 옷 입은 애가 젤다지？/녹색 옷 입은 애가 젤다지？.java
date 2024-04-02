import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		StringBuilder sb = new StringBuilder();
		int idx = 1;
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) {
				System.out.println(sb);
				return;
			}
			
			int[][] map = new int[N][N];
			int[][] cost = new int[N][N];
			boolean[][] isVisited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Queue<int[]> queue = new ArrayDeque<>();
			
			queue.offer(new int[] {0, 0});
			cost[0][0] = map[0][0];
			isVisited[0][0] = true;
			
			int[] pos;
			while(!queue.isEmpty()) {
				pos = queue.poll();
				
				if(pos[0] == N-1 && pos[1] == N-1) continue;
				
				for (int d = 0; d < 4; d++) {
					int nr = pos[0] + deltas[d][0];
					int nc = pos[1] + deltas[d][1];
					
					if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1) continue;
					
					int inCost = cost[pos[0]][pos[1]] + map[nr][nc];
					if(!isVisited[nr][nc]) {
						isVisited[nr][nc] = true;
						cost[nr][nc] = inCost;
						queue.offer(new int[] {nr, nc});
					}
					
					else {
						if(cost[nr][nc] > inCost) {
							cost[nr][nc] = inCost;
							queue.offer(new int[] {nr, nc});							
						}
					}
				}
			}
			
			sb.append("Problem ").append(idx++).append(": ").append(cost[N-1][N-1]).append("\n");
		}
	}

}