import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] foods;
	static int N;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(reader.readLine());
		
		foods = new int[N][2];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(reader.readLine());
			foods[i][0] = Integer.parseInt(st.nextToken());  // 신맛
			foods[i][1] = Integer.parseInt(st.nextToken());  // 쓴맛
		}
		
		getPowerSet(0, 1, 0);
		System.out.println(min);
	}

	private static void getPowerSet(int depth, int  mul, int sum) {
		if(depth == N) {
			if(mul != 1 && sum != 0) {
				int diff = Math.abs(sum - mul);
				min = min > diff? diff:min;
			}
			return;
		}
		getPowerSet(depth+1, mul*foods[depth][0], sum+foods[depth][1]);
		getPowerSet(depth+1, mul, sum);
	}

}