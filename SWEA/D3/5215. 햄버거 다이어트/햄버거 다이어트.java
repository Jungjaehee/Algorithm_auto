import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	/**
	 * 고객이 원하는 조합으로 햄버거 만들어줌 (재료 조합)
	 * 햄버거 맛 최대 유지, 정해진 칼로리 넘지 않게
	 * 입력: 햄버거 재료에 대한 점수, 재료에 대한 칼로리
	 * => 칼로리 이하인 경우, 맛에 대한 최대값 계산
	 * 
	 * [ 문제 해결 프로세스 ]
	 * 부분 집합 (재귀) -> 칼로리, 맛 계산하여 넘기기(매개변수)
	 * 재귀 종료 시, 칼로리 경계 체크, 맛 최대값 저장
	 */
	static int limit;
	static int N;
	static int max;
	
	static int[][] foods;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			
			foods = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				foods[i][0] = Integer.parseInt(st.nextToken());
				foods[i][1] = Integer.parseInt(st.nextToken());
			}
			max = Integer.MIN_VALUE;
			subSet(0, 0, 0);
			sb.append(max).append("\n");
			
		}
		System.out.println(sb);
		
		
	}

	private static void subSet(int depth, int tasteSum, int calSum) {
		if(calSum > limit) return;
		if(depth == N) {
			max = max < tasteSum? tasteSum:max;
			return;
		}
		subSet(depth+1, tasteSum+foods[depth][0], calSum+foods[depth][1]);
		subSet(depth+1, tasteSum, calSum);
	}

}