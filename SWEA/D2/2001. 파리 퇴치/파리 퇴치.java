import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(reader.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(reader.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			int[][] sum = new int[N][N];
			
			int max = Integer.MIN_VALUE;
			
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(reader.readLine());
				for (int j = 0; j < N; j++) {
					int value = Integer.parseInt(st.nextToken());
					map[i][j] = value;
					
					if(j<M) sum[i][M-1] += value;
					else sum[i][j] += sum[i][j-1]-map[i][j-M]+value;
				}
			}
			for (int j = M-1; j <N ; j++) {
				int sumAll = 0;
				for (int i = 0; i < N; i++) {
					if(i<M) sumAll += sum[i][j];
					else
						sumAll += sum[i][j]-sum[i-M][j];
					max = max < sumAll? sumAll:max;
				}
			}
			sb.append(max);
			System.out.println(sb.toString());
		}
		
	}

}