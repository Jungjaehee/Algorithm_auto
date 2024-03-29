import java.util.Arrays;
import java.util.Scanner;

public class Main{
	static int N;
	static int M;
	
	static int[] picked;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		picked = new int[M];
		isSelected = new boolean[N];
		
		permu(0);
		
	}
	private static void permu(int idx) {
		if(idx == M) {
			System.out.println(Arrays.toString(picked).replaceAll("[^0-9\\s]", ""));
			return;
		}
		for (int i = 1; i <=N; i++) {
			if(isSelected[i-1]) continue;
			picked[idx] = i;
			isSelected[i-1] = true;
			permu(idx+1);
			isSelected[i-1] = false;
		}
	}
}
