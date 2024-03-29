import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 시간: 284ms | 메모리: 19,124KB
	 */
	static int[][] arrOri;    // 가지고 있을 원본 배열
	static int[][] arr;      // 돌릴 배열
	static int[][] command;   // 명령을 저장한 배열
	static int N;
	static int M;
	static int K;
	static int[] res;        // 순열 배치한 결과를 가지고 있을 배열
	static int minSum = Integer.MAX_VALUE;
	static int[][] deltas = {{1, 0}, {0, 1},{-1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arrOri = new int[N+1][M+1];
		arr = new int[N+1][M+1];
		command = new int[K][];
		res = new int[K];
		
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arrOri[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = arrOri[i][j];
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			command[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		permutation(0, 0);
		
		System.out.println(minSum);
	}
	
	static void permutation(int depth, int flag) {
		if(depth == K) {  // 명령들에 대한 순열 결정 후 돌려, 행의 합의 최소값 찾기
			for (int i = 0; i < depth; i++) {
				rotate(command[res[i]][0], command[res[i]][1], command[res[i]][2]);
			}
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= M; j++) {
					sum += arr[i][j];
					arr[i][j] = arrOri[i][j];  // 원본 배열 복구
				}
				minSum = Math.min(minSum, sum);
			}
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if((flag & 1 << i)!= 0) continue;
			res[depth] = i;
			permutation(depth+1, flag | 1<< i);
		}
	}
	
	// 배열 돌리기, 당기기 방법 수행
	static void rotate(int r, int c, int s) {
		
		int n = (2*s+1);  
		for (int j = 0; j < s; j++) {
			int sR = r-s+j;
			int sC = c-s+j;
			int temp = arr[sR][sC];
			int pr = sR;
			int pc = sC;
			int nr= sR, nc = sC;
			int des = n-j*2;
			for (int d = 0; d < deltas.length; d++) {
				while(true) {
					nr += deltas[d][0];
					nc += deltas[d][1];
					if(nr < sR || nr >= (sR+des) || nc < sC || nc >= (sC+des) ) {
						nr -= deltas[d][0];
						nc -= deltas[d][1];
						break;
					}
					arr[pr][pc] = arr[nr][nc];
					pr = nr;
					pc = nc;
				}
			}
			arr[pr][pc+1] = temp;
		}
	}

}