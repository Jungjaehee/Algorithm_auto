import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 시간: 460ms | 메모리: 70,716KB
 */
public class Main {
	static int N, M;
	static String[][] researchOrigin;
	static String[][] research;
	static int maxArea = Integer.MIN_VALUE;
	static int[][] walls = new int[3][2];
	static int[][] deltas = {{0, -1},{0, 1},{-1, 0},{1, 0}};
	static Deque<int[]> queue = new ArrayDeque<>();
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		research = new String[N][];
		researchOrigin = new String[N][M];
		
		for (int i = 0; i < N; i++) {
			research[i] = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				researchOrigin[i][j] = research[i][j];
				if(research[i][j].equals("1")) cnt++;
				if(research[i][j].equals("2")) queue.offer(new int[]{i, j});
			}
		}
		
		cnt += 3;
		cnt = N*M-cnt;
		getSafeArea(0, 0);
		
		System.out.println(maxArea);
	}
	private static void getSafeArea(int start, int depth) {
		if(depth == 3) {
			for (int i = 0; i < depth; i++) {
				research[walls[i][0]][walls[i][1]] = "1";				
			}
			
			int twoCnt = bfs();
			maxArea = Math.max(maxArea, cnt-twoCnt);
			
			for (int i = 0; i < N; i++) {
				System.arraycopy(researchOrigin[i], 0, research[i], 0, M);				
			}
			return;
		}
		
		for (int i = start; i < N*M; i++) {
			int r = i/M;
			int c = i%M;
			
			if(!research[r][c].equals("0")) continue;
			
			walls[depth][0] = r;
			walls[depth][1] = c;
			getSafeArea(i+1, depth+1);
		}
		
	}
	private static int bfs() {
		int twoCnt = 0;
		Deque<int[]> queue2 = new ArrayDeque<>();
		queue2.addAll(queue);
		
		while(!queue2.isEmpty()) {
			int[] pos = queue2.poll();
			twoCnt++;
			if(cnt-twoCnt < maxArea) return twoCnt;
			for (int i = 0; i < 4; i++) {
				int nr = pos[0] + deltas[i][0];
				int nc = pos[1] + deltas[i][1];
				
				if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1 || !research[nr][nc].equals("0")) continue;
				research[nr][nc] = "2";
				queue2.offer(new int[] {nr, nc});
			}
		}
		return twoCnt;
	}

}