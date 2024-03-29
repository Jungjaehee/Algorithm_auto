import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] adjList = new int[N+1][N];
		int[] size = new int[N+1];
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a][size[a]++] = b;
			adjList[b][size[b]++] = a;			
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[N+1];
		
		isVisited[1] = true;
		queue.offer(1);
		int cnt = 0;
		while(!queue.isEmpty()) {
			int n = queue.poll();
			for (int i = 0; i < size[n]; i++) {
				int m = adjList[n][i];
				if(isVisited[m]) continue;
				isVisited[m] = true;
				queue.offer(m);
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}