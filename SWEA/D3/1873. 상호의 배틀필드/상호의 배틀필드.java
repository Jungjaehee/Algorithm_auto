import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	/**
	 * [ 문제 해석 ]
	 * 사용자 전차 1개
	 * 사용자 입력에 따라 전차 동작 (문자에 따른 동작)
	 * '.' 일 때만 전진
	 */
	
	static int[] dr = {-1, 1, 0, 0, 0};  // U, D, L, R, S
	static int[] dc = {0, 0, -1, 1, 0};	
	static String car = "^v<>";
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			char[] dArr = new char['Z'+1];
			dArr['U'] = 0; dArr['D'] = 1; dArr['L'] = 2; dArr['R'] = 3; dArr['S'] = 4;
			
			int[] carIndex = new int[3];
			
			char[][] map = new char[R][C];
			String str;
			for (int i = 0; i < R; i++) {
				str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
					if(car.contains(""+map[i][j])) {
						carIndex[0] = i;
						carIndex[1] = j;
						carIndex[2] = car.indexOf(map[i][j]);
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			str = br.readLine();
			for (int i = 0; i < N; i++) {
				int idx = dArr[str.charAt(i)];
				int nr = carIndex[0] + dr[idx];
				int nc = carIndex[1] + dc[idx];
				if(idx !=4) carIndex[2] = idx;
				
				map[carIndex[0]][carIndex[1]] = car.toCharArray()[carIndex[2]];
				
				if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1) continue;
								
				switch(idx) {
					case 4:  //s
						while(true) {
							if(map[nr][nc] == '*') {
								map[nr][nc] = '.';
								break;
							}
							else if(map[nr][nc] == '#') break;
							nr += dr[carIndex[2]];
							nc += dc[carIndex[2]];
							if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1) break;
						}
						break;
					default:  // U D L R
						if(map[nr][nc] != '.') continue;
						map[carIndex[0]][carIndex[1]] = '.';
						map[nr][nc] = car.toCharArray()[carIndex[2]];
						carIndex[0] = nr;
						carIndex[1] = nc;
						
				}
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			
			
		}
		System.out.println(sb);
		
		
	}


}
