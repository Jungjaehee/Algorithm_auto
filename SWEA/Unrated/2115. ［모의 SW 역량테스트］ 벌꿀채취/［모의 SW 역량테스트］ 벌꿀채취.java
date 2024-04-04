import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] honey;
	static int[][] picked;
	static int N, M, C;
	static int maxValue;
	static int maxSum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			honey = new int[N*N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honey[i*N+j] = Integer.parseInt(st.nextToken());
				}
			}

			maxValue = Integer.MIN_VALUE;
			picked = new int[2][M];
			comb(0, 0, -1);
			sb.append("#").append(test_case).append(" ").append(maxValue).append("\n");
			
		}
		System.out.println(sb);
	}
	private static void comb(int depth, int start, int visit) {
		if(depth == 2) {
			maxValue = Math.max(maxValue, calculateValue());
			return;
		}
		
		for(int i=start;i<N*N;i++) {
			int r = i / N;
			int end = i + M -1;
			int endR = end / N;
			int visitEnd = visit + M -1;
			
			if((visit != -1) && ((i>= visit && i <= visitEnd) || (end>= visit && end <= visitEnd))) continue;
			if(r != endR) continue;
			
			int idx = 0;
			for(int m=i; m<=end;m++) 
				picked[depth][idx++] = honey[m];
			
			comb(depth+1, i+1, i);
		}
	}
	
	private static int calculateValue() {
		int value = 0;
		for (int i = 0; i < 2; i++) {
			maxSum = 0;
			subset(i, 0, 0, 0);
			value += maxSum;
		}
		return value;
	}
	
	private static void subset(int p, int depth, int sum, int valSum) {
		if(depth == M) {
			maxSum = Math.max(maxSum, valSum);
			return;
		}
		int temp = sum + picked[p][depth];
		if(temp <= C)
			subset(p, depth+1, temp, (int)(valSum + Math.pow(picked[p][depth], 2)));
		subset(p, depth+1, sum, valSum);
	}

}