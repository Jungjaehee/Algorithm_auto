import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	static StringBuilder sb = new StringBuilder();
	static StringBuilder res = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[] primeNum = {'2', '3', '5', '7'};
		res.setLength(N);
		
		for (int i = 0; i < primeNum.length; i++) {
			res.setCharAt(0, primeNum[i]);
			permutation(1);
		}
		System.out.println(sb);
	}
	
	private static void permutation(int depth) {
		
		int num = Integer.parseInt(res.substring(0, depth));

		if(!isPrime(num))
			return;
			
		if(depth == N) {
			sb.append(num).append("\n");
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			res.setCharAt(depth, nums[i]);
			permutation(depth+1);
		}
	}
	
	private static boolean isPrime(int num) {
		int s = (int)Math.sqrt(num);
		for (int i = s; i > 1; i--) {
			if(num % i == 0)
				return false;
		}
		return true;
	}

}