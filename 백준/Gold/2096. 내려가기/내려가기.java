import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 점화식? dp1[i] = arr[i][0] + max(dp1[i-1], dp2[i-1]) 
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stu
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr1 = Integer.parseInt(st.nextToken());
		int arr2 = Integer.parseInt(st.nextToken());
		int arr3 = Integer.parseInt(st.nextToken());
		
		int maxDp1 = arr1;
		int maxDp2 = arr2;
		int maxDp3 = arr3;

		int maxDp4 = arr1;
		int maxDp5 = arr2;
		int maxDp6 = arr3;
		
		int minDp1 = arr1;
		int minDp2 = arr2;
		int minDp3 = arr3;

		int minDp4 = arr1;
		int minDp5 = arr2;
		int minDp6 = arr3;

		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr1 = Integer.parseInt(st.nextToken());
			arr2 = Integer.parseInt(st.nextToken());
			arr3 = Integer.parseInt(st.nextToken());
			
			
			maxDp4 = arr1 + Math.max(maxDp1, maxDp2);
			maxDp5 = arr2 + Math.max(Math.max(maxDp1, maxDp2) ,maxDp3);
			maxDp6 = arr3 + Math.max(maxDp2, maxDp3);
			
			maxDp1 = maxDp4;
			maxDp2 = maxDp5;
			maxDp3 = maxDp6;
			
			minDp4 = arr1 + Math.min(minDp1, minDp2);
			minDp5 = arr2 + Math.min(Math.min(minDp1, minDp2) ,minDp3);
			minDp6 = arr3 + Math.min(minDp2, minDp3);
			
			minDp1 = minDp4;
			minDp2 = minDp5;
			minDp3 = minDp6;
		}
		
		int sum_max = Math.max(Math.max(maxDp1, maxDp2), maxDp3);
		int sum_min = Math.min(Math.min(minDp1, minDp2), minDp3);
		
		System.out.println(sum_max + " " + sum_min);
	}

}