import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Element{  // 큐에 넣을 정보를 관리하는 요소 클래스
		int r;  // 행 좌표
		int c;  // 열 좌표
		int distance;  // 현재 레벨에서의 거리
		boolean isCrush;  // 벽을 부수고 왔는가?
		public Element(int r, int c, int distance, boolean isCrush) {
			this.r = r;
			this.c = c;
			this.distance = distance;
			this.isCrush = isCrush;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] path = new int[N][M];
		Queue<Element> queue = new ArrayDeque<>();
		boolean[][][] isVisited = new boolean[2][N][M];
				
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				path[i][j] = Integer.valueOf(str.charAt(j) - '0');
			}
		}
		
		isVisited[0][0][0] = true;
		queue.offer(new Element(0, 0, 1, false));
		
		while(!queue.isEmpty()) {
			Element e = queue.poll();
			if(e.r == N-1 && e.c == M-1) {  // 제일 먼저 끝에 도착한 요소의 거리 출력(최단거리)
				System.out.println(e.distance);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nr = e.r + deltas[i][0];
				int nc = e.c + deltas[i][1];
				
				if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1) continue;
				
				/* 한번 부수고 진행된 경우, 0일때만 직진 가능 */
				if(e.isCrush && path[nr][nc] == 0 && !isVisited[1][nr][nc]) {
					queue.offer(new Element(nr, nc, e.distance+1, true));
					isVisited[1][nr][nc] = true;
					continue;
				}

				/* 한번도 부수지 않았던 경우 */
				if(!e.isCrush && !isVisited[0][nr][nc]) {
					/* 다음이 1이면 그 벽 부시기 */
					if(path[nr][nc] == 1) {
						queue.offer(new Element(nr, nc, e.distance+1, true));
						isVisited[1][nr][nc] = true;
					}
					/* 그 다음이 0이면 그대로 넣어 */
					else {
						queue.offer(new Element(nr, nc, e.distance+1, false));
						isVisited[0][nr][nc] = true;
						
					}
				}
				
			}
		}
		
		System.out.println(-1);
		
	}
}