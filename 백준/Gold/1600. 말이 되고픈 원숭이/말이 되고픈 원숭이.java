import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int r;
		int c;
		int horseCnt;
		int cnt;
		
		public Node(int r, int c, int horseCnt, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.horseCnt = horseCnt;
			this.cnt = cnt;
		}		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] horseMove = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, 2}, {-2, 1}, {-1, -2}, {-2, -1}};
		int[][] monkeyMove = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};		
		
		int[][] map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][][] isVisited = new boolean[K+1][H][W];
		Queue<Node> queue = new ArrayDeque<>();
		
		queue.offer(new Node(0, 0, 0, 0));
		isVisited[0][0][0] = true;
		Node cur = null;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			if(cur.r == H-1 && cur.c == W-1) break;
			if(cur.horseCnt < K) {
				// 말의 이동을 쓰는 경우 (홀스)
				for (int i = 0; i < 8; i++) {
					int nr = cur.r + horseMove[i][0];
					int nc = cur.c + horseMove[i][1];
					
					if(nr < 0 || nr > H-1 || nc < 0 || nc > W-1 || isVisited[cur.horseCnt+1][nr][nc] || map[nr][nc] == 1) continue;
					isVisited[cur.horseCnt+1][nr][nc] = true;
					queue.offer(new Node(nr, nc, cur.horseCnt+1, cur.cnt+1));
				}
					
			}
			// 말의 이동을 쓰지 않는 경우 (몽키)
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + monkeyMove[i][0];
				int nc = cur.c + monkeyMove[i][1];
				
				if(nr < 0 || nr > H-1 || nc < 0 || nc > W-1 || isVisited[cur.horseCnt][nr][nc] || map[nr][nc] == 1) continue;
				
				isVisited[cur.horseCnt][nr][nc] = true;
				queue.offer(new Node(nr, nc, cur.horseCnt, cur.cnt+1));
			}
		}
		
		System.out.println((cur.r == H-1 && cur.c == W-1)? cur.cnt : -1);
	}

}