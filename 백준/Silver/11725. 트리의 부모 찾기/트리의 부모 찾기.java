import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 
	 * 	72,984KB | 1,348ms
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		List<Integer>[] tree = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			tree[a].add(b);
			tree[b].add(a);
		}
		
		Deque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(1);
		int[] parents = new int[N+1];
		int counts = 1;
		while(!queue.isEmpty() || counts < N) {
			int par = queue.poll();
			for (int child: tree[par]) {
				if(parents[child] != 0) continue;
				parents[child] = par;
				queue.offer(child);
				counts++;
			}
		}
		
		for (int i = 2; i <=N; i++) {
			sb.append(parents[i]).append("\n");
		}
		System.out.println(sb);
	}

}