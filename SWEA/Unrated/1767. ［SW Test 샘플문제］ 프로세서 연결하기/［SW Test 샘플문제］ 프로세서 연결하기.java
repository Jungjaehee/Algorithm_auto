import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] map;
	static int[][] cores;
	static int size;
	static int maxCore, minLen;
	static int[][] deltas = {{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			size = 0;
			cores = new int[12][2];
			maxCore = 0;
			minLen = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1 && i > 0 && i < N-1 && j > 0 && j < N-1) {
						cores[size++] = new int[] {i, j};
					}
				}
			}
			
			getMaxCore(0, 0, 0);
			sb.append("#").append(test_case).append(" ").append(minLen).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void getMaxCore(int depth, int cnt, int len) {
		if(depth == size) {
			if(maxCore == cnt)
				minLen = Math.min(minLen, len);
			else if(maxCore < cnt) {
				minLen = len;
				maxCore = Math.max(maxCore, cnt);
			}
			return;
		}

		getMaxCore(depth+1, cnt, len);
		for (int i = 1; i < 5; i++) {
			int add = isConnect(depth, i);
			if(add > 0) {
				setConnect(depth, i, add, 2);
				getMaxCore(depth+1, cnt+1, len+add);
				setConnect(depth, i, add, 0);
			}
		}
	}
	private static int isConnect(int depth, int d) {
		if(d == 0) return 0;
		int len = 0;
		int r = cores[depth][0];
		int c = cores[depth][1];
		
		while(true) {
			r += deltas[d][0];
			c += deltas[d][1];
			
			if(r < 0 || r > N-1 || c < 0 || c > N-1) break;
			
			if(map[r][c] != 0) return 0;
			len++;
		}
		return len;
	}

	private static void setConnect(int depth, int d, int length, int mapping) {
		int r = cores[depth][0];
		int c = cores[depth][1];
		
		for (int i = 0; i < length; i++) {
			r += deltas[d][0];
			c += deltas[d][1];
			map[r][c] = mapping;
		}		
	}

}