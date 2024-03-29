import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] degrees;
	static List<Integer>[] adjList;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		degrees = new int[N+1];
		adjList = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			degrees[to]++;
		}
		
		bfs();
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		for (int i = 1; i <= N; i++) {
			if (degrees[i] == 0) 
				queue.offer(i);
		}
		
//		int cnt = 0;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append(" ");
//			cnt++;
			for (int i : adjList[cur]) {
				if(--degrees[i] == 0) queue.offer(i);
			}
		}
	}

}