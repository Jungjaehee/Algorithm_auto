import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String line = br.readLine();
			if (line.equals("end")) {
				System.out.println(sb);
				return;
			}

			map = new char[3][3];
			int xCnt = 0;
			int oCnt = 0;
			for (int i = 0; i < 9; i++) {
				char curr = line.charAt(i);
				map[i / 3][i % 3] = curr;
				if (curr == 'X')
					xCnt++;
				if (curr == 'O')
					oCnt++;
			}
			
			int diff = xCnt - oCnt;
			boolean isValid = false;
			if(isConnected('X')^isConnected('O')) {
				if(isConnected('X')) {
					if(diff == 1) isValid = true;
				}
				else if(isConnected('O')) {
					if(diff == 0) isValid = true;
				}
			}
			if(!isConnected('X') && !isConnected('O')){
				if(diff == 1 && (xCnt + oCnt) == 9) isValid = true;
			}
						
			if(!isValid) sb.append("invalid").append("\n");
			else sb.append("valid").append("\n");
		}
	}

	static boolean isConnected(char target) {
		// 가로
		A: for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] != target) {
					continue A;
				}
			}
			return true;
		}

		// 세로
		A: for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[j][i] != target) {
					continue A;
				}
			}
			return true;
		}
		// 대각선
		boolean flag1 = true;
		boolean flag2 = true;
		for(int i = 0; i < 3; i++) {
			if(map[i][i] != target) {
				flag1 =  false;
			}
	
			for(int j = 0; j < 3; j++) {
				if(i+j == 2 && map[i][j] != target) {
					flag2 = false;
				}
			}
		}
		if(!flag1 && !flag2) return false;
		return true;
	}
}