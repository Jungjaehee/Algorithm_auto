import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 128,900KB, 776ms
 */
public class Main {
	static class Node implements Comparable<Node>{
		int num;
		int weight;
		public Node(int num, int weight) {
			super();
			this.num = num;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		@Override
		public String toString() {
			return "Node [num=" + num + ", weight=" + weight + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int s = Integer.parseInt(br.readLine());
		List<Node>[] adjList = new ArrayList[V+1];
		boolean[] isVisited = new boolean[V+1];
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int[] distance = new int[V+1];
		
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[a].add(new Node(b, w));
		}
		
		Arrays.fill(distance, Integer.MAX_VALUE);

		
		distance[s] = 0;
		queue.offer(new Node(s, 0));
		int cnt = 0;
		while(!queue.isEmpty()) {
			Node u = queue.poll();
			if(isVisited[u.num]) continue;
			
			isVisited[u.num] = true;
			
			if(++cnt == V) break;
			for (Node n: adjList[u.num]) {
				if(!isVisited[n.num] && n.weight + distance[u.num] < distance[n.num]) {
					distance[n.num] = n.weight + distance[u.num];
					queue.offer(new Node(n.num, distance[n.num]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			sb.append(distance[i] == Integer.MAX_VALUE? "INF" : distance[i]).append("\n");
		}
		System.out.println(sb);
	}
}