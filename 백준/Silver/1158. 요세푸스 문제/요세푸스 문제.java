import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
	/**
	 * [ 문제 해석 ]
	 *  1~N번까지 N명의 사람 -> 원
	 *  양의 정수 K => K번째 사람 제거
	 *  N명의 사람이 모두 제거될 때까지 진행
	 *  제거되는 순서 : (N, K) - 요세푸스 순열
	 * 
	 * [ 문제 해결 프로세스 ]
	 *  LinkedList 사용 (K+1)
	 *  nk = 0;
	 *  nk = nk + k -1 % N--;
	 * 
	 * 
	 * 시간 : 144ms  |  메모리: 12,724KB
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		List<Integer> circle = new LinkedList<>();   // 1~N 값을 저장할 배열
		
		int[] result = new int[N];
		int idx = 0;
		for (int i = 1; i < N+1; i++) {   // 값 입력
			circle.add(i);
		}
		
		int nk = 0;
		while(!circle.isEmpty()) {
			nk = (nk + k -1) % circle.size();
			result[idx++] = circle.get(nk);
			circle.remove(nk);
		}
		System.out.println(Arrays.toString(result).replace("[", "<").replace("]", ">"));
	}

}