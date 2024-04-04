import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= 10; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			Map<Integer, Set<Integer>> graph = new HashMap<>();
			Set<Integer> isVisited = new HashSet<Integer>();
			Deque<Integer> queue = new ArrayDeque<>();
			
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N/2; i++) {
				int key = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				
				if(!graph.containsKey(key))
					graph.put(key, new HashSet<Integer>());
				graph.get(key).add(val);
			}
			
			int size = queue.size();
			int cnt = 0;
			int maxVal = V;
			int curMax = V;
			
			queue.offer(V);
			isVisited.add(V);
			while(!queue.isEmpty()) {
				if(cnt == size) {
					cnt = 0;
					maxVal = curMax;
					curMax = 0;
					size = queue.size();
				}
				int cur = queue.poll();
				cnt++;
				if(!graph.containsKey(cur)) continue;
				for (int next : graph.get(cur)) {
					if(isVisited.contains(next)) continue;
					queue.offer(next);
					isVisited.add(next);
					curMax = Math.max(curMax, next);
				}
			}
			
			sb.append(maxVal).append("\n");
			
		}
		System.out.println(sb);
	}

}