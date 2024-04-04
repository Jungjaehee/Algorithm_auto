import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
		int TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] city = new int[N][N];
			int houseCnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					city[i][j] = Integer.parseInt(st.nextToken());
					if(city[i][j] == 1) houseCnt++;
				}
			}
			
			int houseMax = 0;
			int[] pos;
			A: for (int i = 0; i < N; i++) {
				 for (int j = 0; j < N; j++) {				 
					 Queue<int[]> queue = new ArrayDeque<>();
					 boolean[][] isVisited = new boolean[N][N];
					 
					 queue.offer(new int[] {i, j});
					 isVisited[i][j] = true;
					 
					 int house = 0;
					 int houseCost = 0;
					 int k = 1;
					 
					 while(!queue.isEmpty()) {
						int cost = k*k + (k-1)*(k-1);
						int size = queue.size();
						while(size-- > 0) {
							pos = queue.poll();
							if(city[pos[0]][pos[1]] == 1) {
								house++;
								houseCost+=M;
							}
								
							for (int d = 0; d < 4; d++) {
								int nr = pos[0] + deltas[d][0];
								int nc = pos[1] + deltas[d][1];
									
								if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1 || isVisited[nr][nc]) continue;
									
								queue.offer(new int[] {nr, nc});
								isVisited[nr][nc] = true;
							}
						}
						
						if(cost <= houseCost)	houseMax = Math.max(houseMax, house);
						if(houseMax == houseCnt) {
							break A;
						}
						if(k++ == N+1) break;
					}
				}
			}
			sb.append(houseMax).append("\n");
		}
		
		System.out.println(sb);
		
	}

}