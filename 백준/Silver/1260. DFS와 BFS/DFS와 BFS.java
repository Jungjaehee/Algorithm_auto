import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] adjList;
	static StringBuilder sb = new StringBuilder();
	static boolean[] isVisited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		adjList = new List[N+1];
		isVisited = new boolean[N+1];
		
		Deque<Integer> queue = new ArrayDeque<>();

		
		for (int i = 1; i < N+1; i++) {
			adjList[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		for (int i = 1; i < N+1; i++) {
			Collections.sort(adjList[i]);
		}
		
		isVisited[V] = true;
		dfs(V, 0);
		sb.append("\n");
		
		isVisited = new boolean[N+1];
		//BFS
		queue.offer(V);
		isVisited[V] = true;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current).append(" ");
			for (int num : adjList[current]) {
				if(!isVisited[num]) {
					queue.offer(num);
					isVisited[num] = true;
				}
			}
		}
		System.out.println(sb);
	}
	
	static void dfs(int current, int idx) {
		sb.append(current).append(" ");
		
		for (int num : adjList[current]) {
			if(!isVisited[num]) {
				isVisited[num] = true;
				dfs(num, idx+1);
			}
		}
	}

}