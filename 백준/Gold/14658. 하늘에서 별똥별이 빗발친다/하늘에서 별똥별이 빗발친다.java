import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * k가 작으니, k 기준으로 탐색
	 * x = {}, y = {} 100 * 100 = 1000000
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] area = new int[K][2];
		int[] x = new int[K];
		int[] y = new int[K];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
			area[i][0] = x[i];
			area[i][1] = y[i];
		}
		
		int maxCnt = Integer.MIN_VALUE;
		for (int i = 0; i < K; i++) {
			int cur_x = x[i];
			for (int j = 0; j < K; j++) {
				int cur_y = y[j];
				int cnt = 0;
				for (int z = 0; z < K; z++) {
					if(cur_x <= area[z][0] && cur_x + L >= area[z][0] &&
							cur_y <= area[z][1] && cur_y + L >= area[z][1])
						cnt++;
				}
				maxCnt = Math.max(maxCnt, cnt);
			}
		}
		
		System.out.println(K - maxCnt);
		
	}

}