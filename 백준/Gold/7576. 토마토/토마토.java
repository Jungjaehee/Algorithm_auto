import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 
 * @author SSAFY
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int days = 0;
		
		int[][] tomato = new int[N][M];
		
		Deque<Integer> queueR = new ArrayDeque<>();
		Deque<Integer> queueC = new ArrayDeque<>();
		
		int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if(tomato[i][j] == 1) {
					queueR.offer(i);
					queueC.offer(j);
				}
			}
		}
		
		int size = queueR.size();
		int cnt = 0;
		while(!queueR.isEmpty()) {
			if(cnt == size) {
				days++;
				cnt = 0;
				size = queueR.size();
			}
			int r = queueR.poll();
			int c = queueC.poll();
			cnt++;
			for (int d = 0; d < deltas.length; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && tomato[nr][nc] == 0) {
					tomato[nr][nc]++;
					queueR.offer(nr);
					queueC.offer(nc);
				}
			}
		}
		
		
		System.out.println(isPerfect(tomato)? days:-1);
	}

	private static boolean isPerfect(int[][] tomato) {
		int N = tomato.length;
		int M = tomato[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(tomato[i][j] == 0) return false;
			}
		}
		return true;
	}

}