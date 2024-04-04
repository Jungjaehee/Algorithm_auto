import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[][] foodCombi;
	static int N;
	static int minDiff;
	static int[] picked;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <=TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			N = Integer.parseInt(br.readLine());
			foodCombi = new int[N][N];
			minDiff = Integer.MAX_VALUE;
			picked = new int[N];
			for (int i = N-1; i >= N/2; i--) picked[i] = 1;
			
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					foodCombi[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			do {
				int sum = 0;
				for (int i = 0; i < N; i++) {
					for (int j = i; j < N; j++) {
						if(picked[i] != picked[j]) continue;
						if(picked[j] == 1) {
							sum += foodCombi[i][j] + foodCombi[j][i];
						}
						else{
							sum -= (foodCombi[i][j] + foodCombi[j][i]);
						}						
					}
				}
				minDiff = Math.min(minDiff, Math.abs(sum));
			}while(nextPermutation(picked));
			
			
			sb.append(minDiff).append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean nextPermutation(int[] picked) {
		int i = N-1;
		while(i > 0 && picked[i-1] >= picked[i]) i--;
		
		if(i == 0) return false;
		
		int j = N-1;
		while(picked[i-1] >= picked[j]) j--;
		swap(i-1, j, picked);
		
		int k = N-1;
		while(i<k) swap(i++, k--, picked);
		
		return true;
	}

	private static void swap(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


}