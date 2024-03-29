import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/**
 * 
 * 순환하는 배열마다 큐에 저장
 * 시간: 1252ms | 메모리: 52,328KB
 *
 */
public class Main {
	static class ArrManage{
		Deque<String> queue;
		public ArrManage() {}
		ArrManage(int size){ 
			queue = new ArrayDeque<>(size);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] reverseDeltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		
		String[][] arr = new String[N][M];
		for (int i = 0; i <N ; i++) {
			arr[i] = br.readLine().split(" ");
		}
		
		int K = Math.min(N,  M) / 2;

		
		ArrManage[] queArr = new ArrManage[K];
		boolean[][] check = new boolean[N][M];
		
		int nr=0, nc=-1;
		int cnt = 0;
		for (int i = 0, j = 0; i < K; i++, j+=2) {
			int size = 2*(N-j+M-j)-4;
			queArr[i] = new ArrManage(size);
			for (int k = 0; k < size ; k++) {
				nr += reverseDeltas[cnt][0];
				nc += reverseDeltas[cnt][1];
				if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1 || check[nr][nc]) {
					nr -= reverseDeltas[cnt][0];
					nc -= reverseDeltas[cnt][1];
					cnt++; k--; 
					continue;
				}
				
				queArr[i].queue.offer(arr[nr][nc]);
				check[nr][nc] = true;
			}
			int r = R % size;
			while(r > 0) {
				queArr[i].queue.offer(queArr[i].queue.poll());
				r--;
			}
			cnt = 0;
		}
		
		nr = 0;
		nc =-1;
		cnt = 0;
		int[][] res = new int[N][M];
		for (int i = 0; i < K; i++) {
			while(!queArr[i].queue.isEmpty()){
				nr += reverseDeltas[cnt][0];
				nc += reverseDeltas[cnt][1];
				if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1 || res[nr][nc] != 0) {
					nr -= reverseDeltas[cnt][0];
					nc -= reverseDeltas[cnt][1];
					cnt++; 
					continue;
				}
				res[nr][nc] = Integer.parseInt(queArr[i].queue.poll());
			}
			cnt = 0;
		}
				
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(res[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}