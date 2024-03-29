import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 카드 합 21 내에서 카드 합을 최대한 크게
	 * 김정인 VS 상근, 창영이
	 * 
	 * 양의 정수가 적힌 N장의 카드 -> 숫자 M을 외침 -> 플레이어: N 중 3장의 카드 선택
	 * M을 넘지 않으면서 M과 최대한 가깝게 만들기
	 * 
	 * 
	 * 조합 NC3 -> 조합 만들면서 sum 업데이트
	 * sum이 M보다 크면 종료, 같으면 합 출력 후 시스템 종료, 작으면 M과의 차이의 최소값을 가지면 결과에 저장
	 * 
	 * 
	 * @param args
	 */
	
	static int diff = Integer.MAX_VALUE;
	static int maxSum = 0;
	static int[] cards;
	static int M, N;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cards = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		combination(0, 0, 0);
		System.out.println(maxSum);
	}
	private static void combination(int start, int depth, int sum) {
		if(sum > M) return;
		if(depth == 3) {
			if(sum == M) {
				System.out.println(sum);
				System.exit(0);
			}
			int current = M - sum;
			if(current < diff) {
				diff = current;
				maxSum = sum;
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			combination(i+1, depth+1, sum+cards[i]);
		}
	}

}