import java.util.Arrays;
import java.util.Scanner;
/**
 *
 */
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = 9;
		int[] heights = new int[N];
		int sum = 0;
		for(int i=0;i<N;i++) {
			heights[i] = sc.nextInt();
			sum += heights[i];
		}
		sum -= 100;
		Arrays.sort(heights);
		int left = 0, right = N-1;
		while(left < right) {
			int twoSum = heights[left] + heights[right];
			if(twoSum == sum) break;
			else if(twoSum < sum) left++;
			else right--;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if(i == left || i == right) continue;
			sb.append(heights[i]).append("\n");
		}
		System.out.print(sb.toString());
	}

}