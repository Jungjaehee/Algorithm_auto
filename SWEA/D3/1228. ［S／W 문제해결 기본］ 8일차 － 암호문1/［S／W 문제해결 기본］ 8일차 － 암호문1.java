import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	/**
	 * [ 문제 해석 ]
	 *   0~999999 사이의 수 나열
	 *   I(삽입) x, y, s : x위치 다음 y개의 숫자 삽입(s : 숫자들)
	 * 
	 * [ 문제 해결 프로세스 ]
	 *  LinkedList 이용 삽입 
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 1; test_case <= 10; test_case++) {
			sb.append("#").append(test_case).append(" ");

			int N = Integer.parseInt(br.readLine());
			List<String> arr = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr.add(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				String s = st.nextToken();
				int idx = Integer.parseInt(st.nextToken());
				int K = Integer.parseInt(st.nextToken());
				for (int j = idx; j < K+idx; j++) {
					arr.add(j, st.nextToken());
				}
			}
			for (int i = 0; i < 10; i++) {
				sb.append(arr.get(i)).append(" ");				
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}