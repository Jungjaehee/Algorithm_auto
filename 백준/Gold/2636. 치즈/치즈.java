import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 시간:  | 메모리: 
 */
public class Main {
	static String[][] cheese;
	static int R, C;
	static int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cheese = new String[R][];
		
		for (int i = 0; i < R; i++) {
			cheese[i] = br.readLine().split(" ");
		}
		
		/* BFS 탐색 */
		int lastSize = 0;
		int timer = 0;

		int size = bfs();  // 0인 구간에 대해 탐색 후 구멍있는 치즈 개수 세기
		while(size > 0) {
			lastSize = size;
			size = bfs();
			timer++;
		}
		
		System.out.println(timer);
		System.out.println(lastSize);
	}
	
	// 0인 구간에 대해 BFS 탐색 후 구멍있는 치즈 queue에 저장 및 0으로 만들기
	static int bfs() {
		boolean[][] isVisited = new boolean[R][C];
		Queue<int[]> queue = new ArrayDeque();
		int []pos = new int[] {0, 0};
		isVisited[0][0] = true;
		queue.offer(pos);
		int size = 0;
		while(!queue.isEmpty()) {
			pos = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = pos[0] + deltas[i][0];
				int nc = pos[1] + deltas[i][1];
				
				if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1 || isVisited[nr][nc]) continue;
				if(cheese[nr][nc].equals("1")) {
					size++;
					cheese[nr][nc] = "0";
				}
				else queue.offer(new int[] {nr, nc});
				isVisited[nr][nc] = true;
			}
		}
		return size;
	}

}