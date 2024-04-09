import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [ 문제 해석 ]
 *  - 숨바꼭질
 *  - 수빈이는 N에 위치, 동생 K에 위치
 *  - 수빈이는 걷거나 순간 이동 가능
 *  - 수빈이 위치 X -> 걸을 때:X-1, X+1 | 순간이동: 0초 후에 2*X
 *  - 수빈이와 동생의 위치 -> 수빈이가 동생을 찾을 수 있는 가장 빠은 시간?
 *  
 * [ 접근 방법 ]
 *  - BFS?
 *  - DP?
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		final int size = 100001;
		
		int[] count = new int[size];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		boolean[] isVisited = new boolean[size];
		isVisited[N] = true;
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			int twice = num*2;
			if(twice < size) {
				if(isVisited[twice]) {
					if(count[twice] > count[num]) {
						count[twice] = count[num];
						queue.offer(twice);
					}
				}
				else {
					count[twice] = count[num];
					queue.offer(twice);
					isVisited[twice] = true;
				}
			}
			
			
			int minus = num - 1;
			int plus = num + 1;
			
			if(minus >= 0) {
				if(isVisited[minus]) {
					if(count[minus] > count[num]+1) {
						count[minus] = count[num]+1;
						queue.offer(minus);
					}
				}
				else {
					count[minus] = count[num]+1;
					queue.offer(minus);
					isVisited[minus] = true;
				}
			}
			if(plus < size) {
				if(isVisited[plus]) {
					if(count[plus] > count[num]+1) {
						count[plus] = count[num]+1;
						queue.offer(plus);
					}
				}
				else {
					count[plus] = count[num]+1;
					queue.offer(plus);
					isVisited[plus] = true;
				}
			}
			
		}
		
		System.out.println(count[K]);
	}

}