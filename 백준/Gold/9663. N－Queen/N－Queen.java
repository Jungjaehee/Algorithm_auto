import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[] col;
	static boolean[][] diag;
	static int[][] deltas = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
	static int N;
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new boolean[N];
		diag = new boolean[2][N*2-1];
		cnt = 0;
			
		permutation(0);
		
		System.out.println(cnt);
	}
	private static void permutation(int depth) {
		if(depth == N) {
			cnt++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			int r = depth;
			int c = i;
			if(col[c] || diag[0][r+c] || diag[1][r-c+(N-1)] ) continue;
			col[c] = true;
			diag[0][r+c] = true;
			diag[1][r-c+(N-1)] = true;
			permutation(depth+1);
			col[c] = false;
			diag[0][r+c] = false;
			diag[1][r-c+(N-1)] = false;
		}
	}

}