import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int order;
		int cnt;

		public void compare(int inputOrder) {
			if(order == inputOrder) {
				if(++cnt > 1) {
					order = inputOrder + 1;
					cnt = 0;
				}
			}
			else if(order < inputOrder) {
				order = inputOrder;
				cnt = 1;
			}
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());

			Node[] nodes = new Node[M + 1];
			List<Integer>[] adjMat = new ArrayList[M + 1];
			int[] counts = new int[M+1];
			
			for (int i = 1; i <= M; i++) {
				adjMat[i] = new ArrayList<Integer>();
				nodes[i] = new Node();
			}
				
			for (int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				adjMat[A].add(B);
				counts[B]++;
			}
			
			Queue<Integer> queue = new ArrayDeque<Integer>();
			for (int i = 1; i <= M; i++) {
				if(counts[i] != 0) continue;
				nodes[i].order = 1;
				nodes[i].cnt = 1;
				queue.offer(i);
			}
			
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				for (int nxt : adjMat[cur]) {
					nodes[nxt].compare(nodes[cur].order);
					if(--counts[nxt] == 0)	
						queue.offer(nxt);
					
				}
			}
			
			sb.append(K).append(" ").append(nodes[M].order).append("\n");
		}
		
		System.out.println(sb);
	}

}