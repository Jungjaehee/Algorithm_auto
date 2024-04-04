import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	/**
	 * 스도쿠 검증
	 * 
	 * sum을 이용해 45인지 확인
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		A: for (int test_case = 1; test_case <=TC; test_case++) {
			int[][] sudok = new int[9][9];
			sb.append("#").append(test_case).append(" ");
			for (int i = 0; i < 9; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < 9; j++) {
					sudok[i][j] = Integer.parseInt(str[j]);
				}
			}
			for (int i = 0; i < 9; i++) {
				int sum1 = 0;
				int sum2 = 0;
				int sum3 = 0;
				for (int j = 0; j < 9; j++) {
					sum1 += sudok[i][j];
					sum2 += sudok[j][i];
				}
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						sum3 += sudok[(j+(i/3)*3)][(k+(i%3)*3)];
					}
				}
				if(sum1 != 45 || sum2 != 45 || sum3 != 45) {
					sb.append(0).append("\n");
					continue A;					
				}
			}
			sb.append(1).append("\n");
			
		}
		System.out.println(sb);
	}

}