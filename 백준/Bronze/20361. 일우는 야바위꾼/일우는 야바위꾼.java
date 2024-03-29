import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 냐용이의 야바위 게임 ]
 * 
 * 종이컵들의 위치 중 간식이 있는 위치 찾기
 * 종이컵 2개의 자리를 바꿔놓는 작업 반복
 * 
 * - 입력
 *  테케 수
 *  종이컵 -> 200,000 (20만)
 *  왼쪽에서 몇번째에 있는 종이컵인지 알려주는 X  
 *  컵의 위치를 맞바꾸는 횟수 K(100,000 십만)
 *  바꾼 두 컵의 위치
 *  
 *  1. 종이컵 입력
 *  2. k번 만큼 swap
 *  3. 반복문 -> 사탕이 담긴 종이컵 번호 찾아 인덱스 출력
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
			
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
			
		int[] cup = new int[N+1];
		for (int i = 1; i <= N; i++) {
			cup[i] = i;
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());
				
			swap(idx1, idx2, cup);
		}
			
		for (int i = 1; i <=N; i++) {
			if(cup[i] == X) {
				sb.append(i).append("\n");
				break;
			}
		}
		
		System.out.println(sb);
	}

	private static void swap(int idx1, int idx2, int[] arr) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}

}