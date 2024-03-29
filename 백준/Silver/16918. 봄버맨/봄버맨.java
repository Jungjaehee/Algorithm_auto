import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 시간: 224ms | 메모리 : 60104KB
 */
public class Main {
	static int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	static Queue<int[]> queue = new ArrayDeque<>();
	static int N;
	static int R, C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[][] bomb = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				if(str.charAt(j) == 'O') bomb[i][j]+=2;
			}
		}
		
		int n = 1;
		while(n++ < N) {
			putBomb(bomb);
			
			if(queue.size() > 0) {
				removeBomb(bomb);
			}
		}
		
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(bomb[i][j] == 0? '.' : 'O');
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	
	private static void putBomb(int[][] bomb) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(++bomb[i][j] == 4) queue.offer(new int[] {i, j});
			}
		}
	}
	
	private static void removeBomb(int[][] bomb) {
		int []pos;
		while(!queue.isEmpty()) {
			pos = queue.poll();
			bomb[pos[0]][pos[1]] = 0;
			for (int d = 0; d < 4; d++) {
				int nr = pos[0] + deltas[d][0];
				int nc = pos[1] + deltas[d][1];
				
				if(nr >= 0 && nr < R && nc >=0 && nc < C) bomb[nr][nc] = 0;
			}
		}
		
	}
}