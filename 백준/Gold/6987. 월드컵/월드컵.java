import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] results;  // index 0 : 우승, 1 : 무승부, 2: 패
	static int[][] counts;
	static int[][] predicts;
	static StringBuilder sb = new StringBuilder();
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		results = new int[6][3];
		counts = new int[6][3];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			predicts = new int[6][6];
			flag = false;
			for (int j = 0; j < 6; j++) {
				int sum = 0;
				for (int k = 0; k < 3; k++) {
					results[j][k] = Integer.parseInt(st.nextToken());
					counts[j][k] = 0;
					sum += results[j][k];
				}
				if(sum != 5) flag = true;
			}
			if(flag) {
				sb.append(0).append(" ");
				continue;
			}
			
			flag = false;
			perm(0, 0, 1);
			if(!flag) sb.append(0).append(" ");
			
		}
		System.out.println(sb);
	}
	private static void perm(int depth, int r, int c) {
		if(flag) return;
		if(depth == 15) {
			if(!flag) {
				sb.append(1).append(" ");
				flag = true;
			}
			return;
		}
		
		if(c == 6) {
			r++;
			c = r+1;
		}
		
		for (int i = 0; i < 3; i++) {
			if(flag) return;
			if(counts[r][i]+1 > results[r][i] || counts[c][2-i]+1 > results[c][2-i]) continue;
			predicts[r][c] = i;
			predicts[c][r] = 2-i;
			
			counts[r][i]++;
			counts[c][2-i]++;
			perm(depth+1, r, c+1);
			counts[r][i]--;
			counts[c][2-i]--;
		}
	}

}