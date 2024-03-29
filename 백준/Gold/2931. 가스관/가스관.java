import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int dr = 0, dc = 0;
	static int[][][][] directions;
	static char[][] gas;
	
	public static void main(String[] args) throws IOException {
		int[][] deltas = {
				{-1, 0}, {0 ,1}, {1, 0}, {0, -1}
		};
		char[] sign = {'|', '-', '+', '1', '2', '3', '4'};
		directions = new int[][][][]{
				{ { {1, 0}, {-1, 0} }, { {1, 0}, {-1, 0} } },
				{{{0, 1}, {0, -1}}, {{0, 1}, {0, -1}}},
				{{{1,0}, {-1, 0}, {0, 1}, {0, -1}}, {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}},
				{{{0, -1}, {-1, 0}}, {{1, 0}, {0, 1}}},
				{{{0, -1}, {1, 0}}, {{-1, 0}, {0, 1}}},
				{{{0, 1}, {1, 0}}, {{-1, 0}, {0, -1}}},
				{{{0,1}, {-1, 0}}, {{1, 0}, {0, -1}}}				
		};

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		gas = new char[R][];
		int startR = 0, startC = 0;
		
		/* 입력 */
		for (int i = 0; i < R; i++) {
			gas[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(gas[i][j] == 'M') {
					startR = i;
					startC = j;
				}
			}
		}
		
		int curR = 0, curC = 0;
		/* 시작점에서 움직일 다음 방향 정하기 */
		for (int d = 0; d < 4; d++) {
			curR = startR + deltas[d][0];
			curC = startC + deltas[d][1];
			if(curR < 0 || curR > R-1 || curC < 0 || curC > C-1 || gas[curR][curC] == '.') continue;
			
			dr = deltas[d][0];
			dc = deltas[d][1];
			if(next(curR, curC)) break;
		}
		
		/* 정해진 방향대로 이동하기 -> .을 만날 때까지 */
		while(true) {
			curR += dr;
			curC += dc;
			if(gas[curR][curC] == '.') break;
			next(curR, curC);
		}
		
		int[] dirs = new int[4];
		int size = 0;
		for (int d = 0; d < 4; d++) {
			int nr = curR + deltas[d][0];
			int nc = curC + deltas[d][1];
			if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1 || gas[nr][nc] == '.') continue;
			
			dr = deltas[d][0];
			dc = deltas[d][1];
			if(next(nr, nc)) dirs[size++] = d;
		}
		char val = '4';
		if(size == 4) val = '+';
		else if(dirs[0] % 2 == dirs[1] % 2) val = sign[dirs[0]%2];
		else if(dirs[0] == 1) val = '1';
		else if(dirs[1] == 1) val = '2';
		else if(dirs[0] == 0) val = '3';
				
		sb.append(curR+1).append(" ").append(curC+1).append(" ").append(val);
		System.out.println(sb);
		
	}
	
	/* 해당 dr, dc로 r, c구간에 있는 가스관에 들어갈 수 있는지 판단 후 방향 업데이트 */
	static boolean next(int r, int c) {
		int idx = mapping(gas[r][c]);
		if(idx == -1) return false;
		int[][] in = directions[idx][0];
		int[][] out = directions[idx][1];
		int len = in.length;
		for (int i = 0; i < len; i++) {
			if(dr != in[i][0] || dc != in[i][1]) continue;
			dr = out[i][0];
			dc = out[i][1];
			return true;
		}
		return false;
	}
	
	static int mapping(char c) {
		switch(c) {
			case '|':
				return 0;
			case '-':
				return 1;
			case '+':
				return 2;
			case '1':
				return 3;
			case '2':
				return 4;
			case '3':
				return 5;
			case '4':
				return 6;
			default:
				return -1;
		}
	}

}