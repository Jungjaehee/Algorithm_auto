import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static final int mag = 4;
	static final int nal = 8;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int K = Integer.parseInt(br.readLine());
			int[][] info = new int[mag][nal];
			int[] idx = new int[mag];
			
			int[] move = new int[mag];
			
			for (int i = 0; i < mag; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < nal; j++) {
					info[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken()) - 1;
				int dir = -Integer.parseInt(st.nextToken());
				
				move[n] = dir;
				int r = n;
				int c = (idx[n]-2 + nal) % nal;
				for (int j = n-1; j >= 0; j--) {
					int y = ((idx[j]+2 + nal) % nal);
					if(info[r][c] == info[j][y]) break;
					move[j] = dir = -dir;
					r = j;
					c = (idx[j]-2 + nal) % nal;
				}
				
				dir = move[n];
				r = n;
				c = (idx[n]+2 + nal) % nal;
				for (int j = n+1; j < mag; j++) {
					int y = ((idx[j]-2 + nal) % nal);
					if(info[r][c] == info[j][y]) break;
					move[j] = dir = -dir;
					r = j;
					c = (idx[j]+2 + nal) % nal;
				}
				
				for (int j = 0; j < mag; j++) {
					idx[j] = (idx[j] + move[j] + nal)%nal;
					move[j] = 0;
				}
			}
			
			int sum = 0;
			for (int j = 0; j < mag; j++) {
				sum += info[j][idx[j]] * (int) Math.pow(2, j);
			}
			
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}

}