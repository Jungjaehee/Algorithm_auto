import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Node o) {
			return this.r == o.r? Integer.compare(this.c, o.c):Integer.compare(this.r, o.r);
		}
	}
	static int[][] sea;
	static int[][] deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static int N, depth, shark;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sea = new int[N][N];
		
		shark = 2;
		depth = 0;
		int eat = 0;
		int r = 0, c = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sea[i][j] = Integer.parseInt(st.nextToken());
				if(sea[i][j] == 9) {
					r = i; c = j;
					sea[i][j] = 0;
				}
			}
		}
		
		while(true) {
			Node res = bfs(r, c);
			if(res == null) break;
			
			if(++eat == shark) {
				shark++;
				eat = 0;
			}
			sea[res.r][res.c] = 0;
			r = res.r;
			c = res.c;
		}
		
		System.out.println(depth);
		return;
		
	}
	static Node bfs(int r, int c) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N][N];
		Node node;
		
		int cnt = 0;
		queue.offer(new Node(r, c));
		isVisited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				node = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = node.r + deltas[d][0];
					int nc = node.c + deltas[d][1];
					
					if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1 || isVisited[nr][nc]) continue;
					
					if(sea[nr][nc] > 0 && sea[nr][nc] < shark) {
						isVisited[nr][nc] = true;
						pq.offer(new Node(nr, nc));
					}
					else if(sea[nr][nc] == 0 || sea[nr][nc] == shark) {
						isVisited[nr][nc] = true;
						queue.offer(new Node(nr, nc));
					}
				}
			}
			cnt++;
			if(!pq.isEmpty()) {
				depth += cnt;
				return pq.poll();
			}
		}
		
		return null; 
	}

}