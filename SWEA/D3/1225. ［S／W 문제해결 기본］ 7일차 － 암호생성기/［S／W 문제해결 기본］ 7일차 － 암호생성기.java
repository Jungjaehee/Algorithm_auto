import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	/**
	 * [ 문제 해석 ]
	 * n개 수 처리 -> 8자리 암호 생성
	 * 8개 수 입력 -> 첫번째 빼고 1감소 -> 맨 뒤 입력
	 * -> 두번째 수 빼고 2 감소 -> 맨 뒤
	 * 반복
	 * *숫자 <= 0 => 0으로 맨 뒤 입력 후 프로그램 종료 - 이때의 8자리 암호 출력
	 * 
	 * [ 문제 해결 프로세스 ]
	 *  배열 입력 -> 큐
	 *  while 반복하면 poll->감소->조건검사->offer
	 *  if(v==0)  break
	 * 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = 10;
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			int N = Integer.parseInt(br.readLine());
			Queue<Integer> queue = new ArrayDeque<>(8);
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cnt = 0;
			
			for (int i = 0; i < 8; i++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}

			while(true) {
				int v = queue.poll();
				v -= (cnt%5+1);
				cnt++; 
				if(v <= 0) {
					v = 0;
					queue.offer(v);
					break;
				}
				queue.offer(v);
			}
			sb.append(queue.toString().replaceAll("[^0-9\\s]", "")).append("\n");
		}
		System.out.println(sb);
		
		
	}

}