import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] lis = new int[N];
			
			for(int i=0;i<N;i++) {
				lis[i] = 1;
				for(int j=0;j<i;j++) {
					if(arr[i] > arr[j] && lis[i] < lis[j]+1)
						lis[i] = lis[j]+1;
				}
			}
			int maxVal = 0;
			for(int i=0;i<N;i++) {
				maxVal = Math.max(maxVal, lis[i]);
			}
			
			sb.append("#").append(test_case).append(" ").append(maxVal).append("\n");
		}
		System.out.println(sb);
	}
	
}
