import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * '.' : 언제나 이동 가능
 * '#' : 이동 불가
 * 'a' ~'f' : 열쇠
 * 'A' ~ 'F' : 문
 * '0' : 현재 위치
 * '1' : 출구
 */
public class Main {
	static class Node{
		int r;
		int c;
		int key;
		
		public Node() { }
		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		public Node(int r, int c, int key) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
		}
		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", key=" + key + "]";
		}
		
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		char[][] map = new char[N][];
		
		Queue<Node> queue = new ArrayDeque<>(); 
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(map[i][j] == '0') queue.offer(new Node(i, j));
			}
		}
		
		boolean[][][] isVisited = new boolean[64][N][M];
		int cnt = 0;
		Node node;
		isVisited[0][queue.peek().r][queue.peek().c] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size -- > 0) {
				node = queue.poll();
				
				for(int d=0;d<4;d++) {
					int nr = node.r + deltas[d][0];
					int nc = node.c + deltas[d][1];
					
					if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1 || map[nr][nc] == '#') continue;

					if(map[nr][nc] == '1') {
						System.out.println(cnt+1);
						return;
					}
					
					if(map[nr][nc] == '.' || map[nr][nc] == '0') {
						if(isVisited[node.key][nr][nc]) continue;
						isVisited[node.key][nr][nc] = true;
						queue.offer(new Node(nr, nc, node.key));
						continue;
					}
					
					int val = Integer.valueOf(map[nr][nc]);
					int judge = val-'a';
					if(judge >=0 && judge < 6) {  // 키
						int newKey = (node.key | (1 << judge));
						if(isVisited[newKey][nr][nc]) continue;
						isVisited[newKey][nr][nc] = true;
						queue.offer(new Node(nr, nc, newKey));
						continue;
					}
					
					else {
						if(isVisited[node.key][nr][nc]) continue;
						judge = val - 'A';
						if((node.key & (1 << judge)) != 0) { //해당 키 가지고 있음
							isVisited[node.key][nr][nc] = true;
							queue.offer(new Node(nr, nc, node.key));
						}
					}
				}
			}
			cnt++;
		}
		System.out.println(-1);
		
	}

}