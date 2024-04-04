import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, W, H;
	static int[][][] map;
	static int[] picked;
	static int[][] top;
	static int[] temp;
	static int sum;
	static int maxVal;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static class Node{
		int r;
		int c;
		int power;
		public Node(int r, int c, int power) {
			super();
			this.r = r;
			this.c = c;
			this.power = power;
		}
	}
	static Queue<Node> queue = new ArrayDeque<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			sum = 0;
			picked = new int[N];
			map = new int[N+1][H][W];
			top = new int[N+1][W];
			temp = new int[H];
			
			
			for (int i = 0; i < W; i++) {
				top[0][i] = H;
			}
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[0][i][j] = Integer.parseInt(st.nextToken());
					if(top[0][j] == H && map[0][i][j] > 0) top[0][j] = i;
					if(map[0][i][j] > 0) sum++;
				}
			}
			maxVal = Integer.MIN_VALUE;
			perm(0, 0);
			sb.append("#").append(test_case).append(" ").append(sum-maxVal).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void perm(int depth, int cnt) {
		if(depth == N) {
			maxVal = Math.max(maxVal, cnt);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			picked[depth] = i;
			perm(depth+1, cnt + simulation(depth+1));
		}
		
	}
	
	private static int simulation(int n) {
		for (int i = 0; i < H; i++) {
			System.arraycopy(map[n-1][i], 0, map[n][i], 0, W);
		}
		int cnt = delete(n, picked[n-1]);
		pull(n);
		return cnt;
	}
	
	private static int delete(int n, int c) {
		int r = top[n-1][c];
		if(r == H) return 0;
		
		queue.offer(new Node(r, c, map[n][r][c]));
		map[n][r][c] = 0;
		int cnt = 1;
		Node node;
		while(!queue.isEmpty()) {
			node = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = node.r;
				int nc = node.c;
				for (int i = 1; i < node.power; i++) {
					nr += deltas[d][0];
					nc += deltas[d][1];
					
					if(nr < 0 || nr > H-1 || nc < 0 || nc > W-1) break;
					
					if(map[n][nr][nc] == 0) continue;
					if(map[n][nr][nc] > 1) 
						queue.offer(new Node(nr, nc, map[n][nr][nc]));
					map[n][nr][nc] = 0;
					cnt++;
				}		
			}
		}
		return cnt;
		
	}
	private static void pull(int n) {
		for (int i = 0; i < W; i++) {
			int size = 0;
			
			for (int j = H-1; j >= 0; j--) {
				if(map[n][j][i] != 0) {
					temp[size++] = map[n][j][i];
					map[n][j][i] = 0;
				}
			}
						
			for (int j = 0; j < size ; j++) {
				map[n][H-j-1][i] = temp[j];
				temp[j] = 0;
			}
			
			top[n][i]= H - size;
		}
	}

}