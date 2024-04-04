import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		int[][] temp = new int[R][C];
		
		int[][][] deltas = {{{-1, 0}, {0, 1}, {1, 0}, {0, -1}},
							{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}};
		
		int[] freshAir = new int[2];
		int idx = 0;
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			if(map[i][0] == -1) freshAir[idx++] = i;
		}
		
		for (int i = 0; i < T; i++) {
			
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					if(map[j][k] == 0) continue;
					if(map[j][k] < 5) {
						temp[j][k] += map[j][k];
						continue;
					}
					int cnt = 0;
					int val = map[j][k] / 5;
					for (int d = 0; d < 4; d++) {
						int nr = j + deltas[0][d][0];
						int nc = k + deltas[0][d][1];
						
						if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1 || map[nr][nc] == -1) continue;
						
						temp[nr][nc] += val;
						cnt++;
					}
					temp[j][k] += map[j][k] - val*cnt;
				}
			}
			
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					map[j][k] = temp[j][k];
					temp[j][k] = 0;
				}
			}
			
			int d = 0;
			int r = freshAir[0]-1;
			int c = 0;
			map[r][c] = 0;
			map[freshAir[0]][0] = 0;			
			while(d < 4) {
				int nr = r + deltas[0][d][0];
				int nc = c + deltas[0][d][1];
				
				if(nr < 0 || nr > freshAir[0] || nc < 0 || nc > C-1) {
					d++;
					continue;
				}
				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
			}
			map[freshAir[0]][0] = -1;
			
			
			d = 0;
			r = freshAir[1]+1;
			c = 0;
			map[r][c] = 0;
			map[freshAir[1]][0] = 0;
			while(d < 4) {
				int nr = r + deltas[1][d][0];
				int nc = c + deltas[1][d][1];
				
				if(nr < freshAir[1] || nr > R-1 || nc < 0 || nc > C-1) {
					d++;
					continue;
				}
				
				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
			}
			map[freshAir[1]][0] = -1;
		}
		
		int sum = 0;
		for (int j = 0; j < R; j++) {
			for (int k = 0; k < C; k++) {
				if(map[j][k] < 1) continue;
				sum += map[j][k];
			}
		}
		
		System.out.println(sum);
		
	}

}