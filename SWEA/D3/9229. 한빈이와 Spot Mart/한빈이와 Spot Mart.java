import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	static Integer[] a;
	static int min;
	static int N;
	static int M;
	static int maxSum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(reader.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <=TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			min = Integer.MAX_VALUE;
			maxSum = -1;
			
			String[] strs = reader.readLine().split(" ");
			N = Integer.parseInt(strs[0]);
			M = Integer.parseInt(strs[1]);
			
			a = new Integer[N];
			strs = (reader.readLine().split(" "));
			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(strs[i]);
			}
			
			Arrays.sort(a, (i1, i2)-> -i1.compareTo(i2));
			
			int max_idx = 0;
			for (int i = 0; i < N; i++) {
				if(M <= a[i]) {
					max_idx++;
					break;
				}
			}
			if(a[N-2]+a[N-1] > M) { sb.append(-1).append("\n"); continue; }
			
			combi(max_idx, 0, 0);
			sb.append(maxSum).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	static void combi(int start, int depth, int sum) {
		if(sum > M) return;
		if(depth == 2) {
			int diff = M - sum;
			if(min > diff) {
				min = diff;
				maxSum = sum;
			}
			return;
		}
		for (int i = start; i < N; i++) {
			combi(i+1, depth+1, sum+a[i]);
		}
	}

}