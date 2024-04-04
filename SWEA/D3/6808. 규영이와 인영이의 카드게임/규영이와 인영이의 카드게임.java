import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N = 9;
	static int[] gu = new int[N];
	static int[] iny = new int[N];
	
	static int win = 0;
	static int lose = 0;
	
	static boolean[] isSelected = new boolean[N];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(reader.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			win = 0;
			lose = 0;
			sb.append("#").append(test_case).append(" ");
			int N = 9;
			int i=1, j=0;
			boolean[] guCheck = new boolean[19];
			StringTokenizer st = new StringTokenizer(reader.readLine());
			
			
			for (int k = 0; k < N; k++) {
				gu[k] = Integer.parseInt(st.nextToken());
				guCheck[gu[k]] = true;
			}
			
			for (int k = 1; k <= N*2;k++) {
				if(!guCheck[k])
					iny[j++] = k;
			}
			permu(0, 0, 0);
			sb.append(win).append(" ").append(lose).append("\n");
			
		}
		System.out.println(sb.toString());

	}

	private static void permu(int depth, int gu_jum, int iny_jum) {
		if(depth == N) {
			if(gu_jum > iny_jum) win++;
			else if(gu_jum < iny_jum) lose++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			
			if(gu[depth] > iny[i]) permu(depth+1, gu_jum+gu[depth]+iny[i], iny_jum);
			else if(gu[depth] < iny[i]) permu(depth+1, gu_jum, iny_jum+gu[depth]+iny[i]);
			else permu(depth+1, gu_jum, iny_jum);
			
			isSelected[i] = false;

		}
		
	}

}