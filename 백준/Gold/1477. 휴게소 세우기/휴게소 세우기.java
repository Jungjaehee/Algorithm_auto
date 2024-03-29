import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] shelter = new int[N+2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			shelter[i] = Integer.parseInt(st.nextToken());
		}
		shelter[0] = 0;
		shelter[N+1] = L;
		Arrays.sort(shelter);

		int start = 1;
		int end = L-1;
		int res = 0;
		while(start <= end) {
			int cnt = 0;
			int mid = (start+end)/2;
			for (int i = 1; i < N+2; i++) {
				int len = shelter[i] - shelter[i-1];
				cnt += len/mid;
				if(len % mid == 0) cnt--;
			}
			if(cnt > M) {
				start = mid+1;
			}
			else {
				res = mid;
				end = mid-1;
			}		
		}
		
		System.out.println(res);
	}

}