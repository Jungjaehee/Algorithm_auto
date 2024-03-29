import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N+1][3];
		for (int i = 1; i < N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] red = new int[N+1];
		int[] green = new int[N+1];
		int[] blue = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			red[i] = Math.min(green[i-1], blue[i-1]) + cost[i][0];
			green[i] = Math.min(red[i-1], blue[i-1]) + cost[i][1];
			blue[i] = Math.min(red[i-1], green[i-1]) + cost[i][2];
		}
		System.out.println(Math.min(Math.min(red[N], green[N]), blue[N]));
	}

}