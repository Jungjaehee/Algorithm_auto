import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] office;
	static int[][] position;
	static int[] caseCnt;
	static int minCnt = Integer.MAX_VALUE;
	static int cnt = 0;
	static int K = 0;
	static int zeroCnt = 0;
	static boolean[][] isVisited;
	static int[] direct;
	static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		office = new int[N][M];
		position = new int[N*M][2];
		caseCnt = new int[N*M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if(office[i][j] > 0 && office[i][j] < 6) {  // CCTV 배열 저장
					position[K][0] = i;
					position[K][1] = j;
					switch(office[i][j]) {
					case 5:
						caseCnt[K] = 1;
						break;
					case 2:
						caseCnt[K] = 2;
						break;
					default:
						caseCnt[K] = 4;
					}
					K++;
					
				}
				else if(office[i][j] == 6) zeroCnt++;
			}
		}
		direct = new int[K];
		zeroCnt = N*M - zeroCnt - K;
		getDirections(0);
		System.out.println(minCnt);
	}
	
	/* 4개 중 K를 뽑는 중복 순열 */
	static void getDirections(int depth) {
		if(depth == K) {
			isVisited = new boolean[N][M];
			cnt = 0;
			for(int i=0 ; i<K;i++) {
				int r = position[i][0];
				int c = position[i][1];
				switch(office[r][c]) {
					case 1:   // CCTV가 1번인 경우, 선택된 방향으로 감시
						CCTV(r, c, direct[i]);
						break;
					case 2:  // 2번인 경우, 선택된 방향과 반대 방향으로 감시
						CCTV(r, c, direct[i]);
						CCTV(r, c, (direct[i]+2)%4);
						break;
					case 3:  // 3번인 경우, 선택된 방향과 직각 방향 감시
						CCTV(r, c, direct[i]);
						CCTV(r, c, (direct[i]+3)%4);
						break;
					case 4:  // 4번인 경우, 선택된 방향과 나머지 2방향 감시
						CCTV(r, c, direct[i]);
						CCTV(r, c, (direct[i]+2)%4);
						CCTV(r, c, (direct[i]+3)%4);
						break;
					case 5:  // 5번인 경우, 항상 모든 방향 감시
						CCTV(r, c, 0);
						CCTV(r, c, 1);
						CCTV(r, c, 2);
						CCTV(r, c, 3);
						break;
				}
			}
			
			minCnt = Math.min(minCnt, zeroCnt-cnt);
			return;
		}
		
		for (int i = 0; i < caseCnt[depth]; i++) {
			direct[depth] = i;
			getDirections(depth+1);
		}
	}

	private static void CCTV(int r, int c, int d) {
		int nr = r + deltas[d][0];
		int nc = c + deltas[d][1];
		while(nr >= 0 && nr < N && nc >= 0 && nc < M) {
			if(!isVisited[nr][nc] && office[nr][nc] == 0) {
				cnt++;
				isVisited[nr][nc] = true;
			}
			else if(office[nr][nc] == 6) {
				break;
			}
			nr += deltas[d][0];
			nc += deltas[d][1];			
		}
	}

}