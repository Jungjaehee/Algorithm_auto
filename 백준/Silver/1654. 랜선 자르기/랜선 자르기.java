import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * N개 랜선
	 * K개 랜선 잘라
	 * 
	 * N개보다 많이 만드는 것도 N개 만드는 것에 포함돔
	 * 
	 * N개로 만들기 위한 랜선의 길이 중 최대 길이?
	 * 
	 * 1. K개 전선 int 배열로 저장
	 * 2. 큰 거 반을 자름 -> 몫을 모두 저장 -> N보다 작으면 더 잘라?
	 * 3. 최소값 출력
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long[] lines = new long[K];
		long cnt = 0;
		long maxVal = 0;
		
		for (int i = 0; i < K; i++) {
			lines[i] = Integer.parseInt(br.readLine());
			maxVal = Math.max(maxVal, lines[i]);
		}
		
		long end = maxVal;
		long start = 1;
		long mid = (start + end)/2;
		
		while(start <= end) {
			cnt = 0;
			for (int i = 0; i < K; i++) {
				cnt += lines[i]/mid;
			}
			if(cnt < N) { 				
				end = mid-1;
				mid = (start+end)/2;
			}
			else {
				start = mid+1;
				mid = (end+start)/2;
//				if(start == mid || end == mid) break;
			}
		}
		
		System.out.println(mid);
	}

}