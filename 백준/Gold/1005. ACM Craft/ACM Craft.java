import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		for (int test_case = 0; test_case < T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] times = new int[N+1];
			int[] parents = new int[N+1];
			long[] totalTimes = new long[N+1];
			List<Integer>[] adjMat = new ArrayList[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				times[i] = Integer.parseInt(st.nextToken());
				adjMat[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				parents[e]++;
				adjMat[s].add(e);
			}
			
			Queue<Integer> queue = new ArrayDeque<Integer>();
			for (int i = 1; i <= N; i++) {
				if(parents[i] == 0) {
					queue.offer(i);
					totalTimes[i] = times[i];
				}
			}
			
			int W = Integer.parseInt(br.readLine());
			
			while(!queue.isEmpty()) {
				int size = queue.size();
				while(size-- > 0) {
					int cur = queue.poll();
					for (int nxt : adjMat[cur]) {
						totalTimes[nxt] = Math.max(totalTimes[nxt], totalTimes[cur] + times[nxt]);
						if(--parents[nxt] == 0) {
							queue.offer(nxt);
						}
					}
				}
			}
			sb.append(totalTimes[W]).append("\n");
		}
		System.out.println(sb);
	}

}