import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static char[][] mat;
	static int N;
	static int sum;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(reader.readLine());
		for(int test_case=1;test_case<=T; test_case++) {
			sum = 0;
			N = Integer.parseInt(reader.readLine());
			mat = new char[N][];
			for (int i = 0; i < N; i++) {
				mat[i] = reader.readLine().toCharArray(); 
			}
			for (int j = 0; j < N; j++) {
				sum += (mat[N/2][j]-'0');
			}
			getValue(N/2+1, 1, false);
			getValue(N/2-1, 1, true);
			
			System.out.println("#" + test_case + " " + sum);

		}
		
	}
	private static void getValue(int i, int blank, boolean isUp) {
		if(i < 0 || i > N-1)
			return;

		for (int j = 0; j < N; j++) {
			if(j < blank || j > N-blank-1) continue;
			sum += (mat[i][j]-'0');
		}
		getValue(i+(isUp? -1:1), blank+1, isUp);
	}

}
