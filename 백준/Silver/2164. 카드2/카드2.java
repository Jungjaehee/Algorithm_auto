import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	/**
	 * [ 문제 해석 ]
	 *  N장의 카드 (1~N)
	 *  1 - 제일 위, N - 제일 아래
	 *  제일 위에 있는 카드 바닥에 버림
	 *  -> 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮김
	 *  => 카드가 한 장 남을 때까지 반복, 제일 마지막에 남는 카드 출력
	 *  
	 *  [ 문제 해결 프로세스 ]
	 *  큐 이용
	 *  
	 *  반복문 -> 1~N부터 삽입
	 *  
	 *  반복문 수행 (남는게 1일 때까지)
	 *   ==> 하나 버리고 조건 검사
	 *   큐.poll() -> 삭제 (head에서)
	 *   조건 검사
	 *   큐.poll() 
	 *   큐.offer -> 삽입 (rear에)
	 *   
	 *   시간 복잡도
	 *   500,000 + 250,000
	 *   
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N==1) {
			System.out.println(1);
			return;
		}
		Queue<Integer> queue = new ArrayDeque<Integer>(N);
		
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		while(true) {
			queue.poll();
			if(queue.size() == 1)
				break;
			queue.offer(queue.poll());
		}
		
		System.out.println(queue.poll());
	}

}