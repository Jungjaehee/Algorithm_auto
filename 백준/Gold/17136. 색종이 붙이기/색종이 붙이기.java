import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int[10][10];
	static int[] counts = new int[6];
	static boolean[][] post = new boolean[10][10];
	static int oneCount = 0;
	static int[][] ones = new int[100][];
	static int minCnt = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) ones[oneCount++] = new int[] {i, j};
			}
		}
		
		perm(0, 0, 0);
		System.out.println(minCnt == Integer.MAX_VALUE? -1 : minCnt);
		
	}
	private static void perm(int depth, int cnt, int oneCnt) {
		if(cnt >= minCnt) return;
		if(depth == oneCount) {
			if(oneCnt != oneCount) return;
			minCnt = Math.min(minCnt, cnt);
			return;
		}
		
		int r = ones[depth][0];
		int c = ones[depth][1];
		
		if(post[r][c]) perm(depth + 1, cnt, oneCnt);
		else {
			for (int i = 5; i > 0; i--) {
				if(counts[i] == 5 || !isPossible(r, c, i)) continue;
	
				posting(r, c, i, true);
				counts[i]++;
				
				perm(depth + 1, cnt+1, oneCnt+(i*i));
				
				counts[i]--;
				posting(r, c, i, false);
			}
		}
	}
	
	private static void posting(int r, int c, int n, boolean flag) {
		for (int i = r; i < r+n; i++) {
			for (int j = c; j < c+n; j++) {
				post[i][j] = flag;
			}
		}
	}

	private static boolean isPossible(int r, int c, int n) {
		if(r+n > 10 || c + n > 10) return false;
		for (int i = r; i < r+n; i++) {
			for (int j = c; j < c+n; j++) {
				if(map[i][j] != 1 || post[i][j]) return false;
			}
		}
		return true;
	}

}