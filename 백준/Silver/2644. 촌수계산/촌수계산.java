import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
	
		int m = Integer.parseInt(br.readLine());
		int[][] adjMatrix = new int[n+1][n+1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b] = 1;
			adjMatrix[b][a] = 1;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[n+1];
		
		queue.offer(x);
		isVisited[x] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size -- > 0) {
				int k = queue.poll();
				if(k==y) {
					System.out.println(cnt);
					return;
				}
				for (int i = 1; i < n+1; i++) {
					if(adjMatrix[k][i] == 0 || isVisited[i]) continue;
					queue.offer(i);
					isVisited[i] = true;
				}

			}
			cnt++;
		}
		System.out.println(-1);
	}

}