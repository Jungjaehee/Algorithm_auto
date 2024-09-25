import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 인터넷으로 투 포인터 방법 찾아봄
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		long totalSum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			totalSum += arr[i];
			if(arr[i] >= S) {
				System.out.println(1);
				return;
			}
		}
		if(totalSum < S) {
			System.out.println(0);
			return;
		}
		
		int left = 0, right = 0;
		long sum = arr[0];
		int minLen = Integer.MAX_VALUE;
		
		while(true) {
			if(sum < S) {
				right ++;
				if(right == N) break;
				sum += arr[right]; 
			}
			else {
				sum -= arr[left];
				minLen = Math.min(minLen, right - left + 1);
				left++;
			}
		}
		System.out.println(minLen);
		
	}

}