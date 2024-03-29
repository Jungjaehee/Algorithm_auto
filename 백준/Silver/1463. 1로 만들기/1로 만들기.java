import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int []memo = new int[N+1];
		
		memo[1] = 0;
		
		for (int i = 2; i < N+1; i++) {
			memo[i] = Integer.MAX_VALUE;
			if(i%3 == 0)
				memo[i] = Math.min(memo[i], memo[i/3]+1);
			if(i%2 == 0)
				memo[i] = Math.min(memo[i], memo[i/2]+1);
			
			memo[i] = Math.min(memo[i], memo[i-1]+1);				
		}
		
//		System.out.println(Arrays.toString(memo));
		System.out.println(memo[N]);
	}

}