import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int circle;
	static char[] number;
	static List<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			number = br.readLine().toCharArray();
			
			circle = N/4;
			splitNum();
			for(int i = 0; i< circle-1;i++) {
				rotateNum();	
				splitNum();
			}
			list.sort(Collections.reverseOrder());
			sb.append("#").append(test_case).append(" ").append(list.get(K-1)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void rotateNum() {
		char temp = number[0];
		for (int j = 1; j < number.length; j++) {
			number[j-1] = number[j];
		}
		number[N-1] = temp;
	}
	private static void splitNum() {
		String n = String.valueOf(number);
		for (int i = 0; i < number.length; i+=circle) {
			int num = Integer.parseInt(n.substring(i, i+circle), 16);
			if(!list.contains(num)) list.add(num);
		}
	}

}