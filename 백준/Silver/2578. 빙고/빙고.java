import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 1. 철수가 쓴 수 저장
	 *   1) 1~25 숫자 -> 값을 인덱스로 사용, 2차원 배열에 위치 값 저장
	 *   
	 * 2. 사회자가 부르는 수 체크 (반복문 5*5)
	 *   1) 값으로 인덱스 접근 -> 위치 값 받기
	 *   2) 해당 행과 열의 삭제 개수 증가, 대각선 위치라면 대각선 삭제 개수 증가
	 *   3) 증가된 값들이 5일 때, 전체 빙고 카운트 증가
	 *   4) 카운트가 3이상일 때, 출력 후 종료
	 *     - i=0~4, j=1~5 일 때, 사회자가 부른 수: i*N+j
	 * 
	 * 시간: 76ms | 메모리: 11,532KB
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = 5;
		int[][] locate = new int[N*N+1][2];
		int[] checkR = new int[N];
		int[] checkC = new int[N];
		int[] checkDiag = new int[2];
		int cnt = 0;
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				locate[val][0] = i;
				locate[val][1] = j;
			}
		}
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 5; j++) {
				int ans = Integer.parseInt(st.nextToken());
				int r = locate[ans][0];
				int c = locate[ans][1];
				
				if(++checkR[r] == N) cnt++;
				if(++checkC[c] == N) cnt++;
				if(r==c && ++checkDiag[0] == N) cnt++;
				if((r+c) == (N-1) && ++checkDiag[1] == N) cnt++;
				
 				if(cnt >= 3) {
					System.out.println(i*N+j);
					return;
				}
			}
		}
		
	}
}