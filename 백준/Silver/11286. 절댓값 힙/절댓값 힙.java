import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	/**
	 * [ 문제 해석 ]
	 *  배열에 정수 x를 넣음
	 *  배열에서 절댓값이 가장 작은 값 출력 -> 그 값 제거
	 *  절댓값이 가장 작은 값이 여러 개 -> 가장 작은 수 출력, 배열에서 제거
	 *  
	 * [ 문제 해결 프로세스 ]
	 *  1. 우선순위 큐 이용
	 *  2. 정렬 순서 -> 람다 함수 이용 구현
	 *  	- 절댓값이 작은 순서로 구현
	 *  	- 절댓값이 같은 경우, 작은 수 우선
	 *  3. 반복문을 돌면서 명령어 입력
	 *    -> 0인 경우, 힙이 비어있는지 검사 후 출력
	 *    -> 아니면 add
	 * 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> {
			if(Math.abs(i1) == Math.abs(i2)) 
				return i1.compareTo(i2);
			return Integer.compare(Math.abs(i1), Math.abs(i2));
		});
				
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			switch(x) {
			case 0:
				if(pq.isEmpty()) sb.append(0).append("\n");
				else sb.append(pq.poll()).append("\n");
				break;
			default:
				pq.add(x);
			}
		}
		System.out.println(sb);
	}

}