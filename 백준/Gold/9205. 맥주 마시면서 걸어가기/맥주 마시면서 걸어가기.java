import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		A: for (int test_case = 0; test_case < t; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int[][] pos = new int[n+2][2];
			
			boolean[] isVisited = new boolean[n+2];
			
			for (int i = 0; i < n+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
			}
			
			Queue<Integer> queue = new ArrayDeque<>();
			queue.offer(0);
			isVisited[0] = true;
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				for (int i = 1; i < n+1; i++) {
					if(isVisited[i]) continue;
					int dis = Math.abs(pos[cur][0]-pos[i][0]) + Math.abs(pos[cur][1]-pos[i][1]);
					if(dis <= 1000) {
						queue.offer(i);
						isVisited[i] = true;
					}
				}
				int dis = Math.abs(pos[cur][0]-pos[n+1][0]) + Math.abs(pos[cur][1]-pos[n+1][1]);
				if(dis <= 1000) {
					sb.append("happy").append("\n");
					continue A;
				}
			}
			sb.append("sad").append("\n");
			
		}
		System.out.println(sb);
	}

}