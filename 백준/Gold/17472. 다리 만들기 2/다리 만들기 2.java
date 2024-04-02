import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] parents;	
	static boolean[][] isVisited;
	static int size, N, M;
	static int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0, -1}};
	
	static class Node implements Comparable<Node>{
		int from;
		int to;
		int weight;
		public Node(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		@Override
		public String toString() {
			return "Node [from=" + from + ", to=" + to + ", weight=" + weight + "]\n";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		parents = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/* 섬 개수 세기 */
		size = 0;
		isVisited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(isVisited[i][j] || map[i][j] == 0) continue;
				parents[i][j] = ++size;
				dfs(i, j, size);
			}
		}
				
		size++;
		int[][] weight = new int[size][size];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue;
				for (int d = 0; d < 4; d++) {
					int nr = i;
					int nc = j;
					int len = 0;
					while(true) {
						nr += deltas[d][0];
						nc += deltas[d][1];
						if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1) break;
						len++;
						if(map[nr][nc] == 1) {
							if(parents[nr][nc] == parents[i][j]) break;
							if(len < 3) break;

							int w = weight[parents[i][j]][parents[nr][nc]];
							weight[parents[i][j]][parents[nr][nc]] = w==0? len-1:Math.min(w, len-1);
							break;
						}
					}
				}
			}
		}
		
		List<Node> list = new ArrayList<>();
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if(weight[i][j] == 0) continue;
				list.add(new Node(i, j, weight[i][j]));
			}
		}
		Collections.sort(list);
		int[] sets = new int[size];
		make(sets);
		int cost = 0;
		int cnt = 1;
		for (Node e : list) {
			if(!union(sets, e.from, e.to)) continue;
			cost += e.weight;
			if(++cnt == size) break;
		}
		
		 for(int i = 1; i < size; i++) {
	            for(int j = i + 1; j < size; j++) {
	                if( find(sets[i], sets) != find(sets[j], sets)) {
	                    System.out.println(-1);
	                    return;
	                }
	            }
	        }

		System.out.println(cost);
		
	}
	private static void dfs(int r, int c, int size) {
		for (int d = 0; d < 4; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			
			if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1 || isVisited[nr][nc] || map[nr][nc] == 0) continue;
			
			parents[nr][nc] = size;
			isVisited[nr][nc] = true;
			dfs(nr, nc, size);
		}
	}
	
	static void make(int[] sets) {
		for (int i = 1; i < sets.length; i++) {
			sets[i] = i;
		}
		
	}
	
	static int find(int a, int[] sets) {
		if(sets[a] == a) return a;
		return sets[a] = find(sets[a], sets);
	}
	
	static boolean union(int[] sets, int a, int b) {
		int p = find(b, sets);
		if(find(a, sets) == p) return false;
		sets[p] = a;
		return true;
	}
}