import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <=T; test_case++) {
            StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append("\n");
			
			int N = sc.nextInt();
			
			int [][] deltas = {
					{0, 1}, {1, 0}, {0, -1}, {-1, 0}
			};
			
			int [][] numbers = new int[N][N];
			int d = 0;
			int cnt = 1;
			int r=0, c=0;
			
			for (int i = 0; i < N*N; i++) {
				if(r >= 0 && r < N && c >= 0 && c < N && numbers[r][c] == 0) {
					numbers[r][c] = cnt++;
				}
				else {
					r -= deltas[d%4][0];
					c -= deltas[d%4][1];
					d++; i--;
				}
				r += deltas[d%4][0];
				c += deltas[d%4][1];
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(numbers[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}
		
	}

}
