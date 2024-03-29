import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int D;
	static int[] res = new int[3];
	static int maxKill = 0;
	static int[][] enemy;
	static int[][] enemyOrigin;
	static int enemySize = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		enemy = new int[N][M];
		enemyOrigin = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				enemyOrigin[i][j] = enemy[i][j] = Integer.parseInt(st.nextToken());
				if(enemy[i][j] == 1) enemySize++;
			}
		}
	
		combination(0, 0);
		System.out.println(maxKill);
	}
	
	static void combination(int start, int depth) {
		if(depth == 3) {
			maxKill = Math.max(maxKill, play(enemySize));
			for (int i = 0; i < N; i++) {
				System.arraycopy(enemyOrigin[i], 0, enemy[i], 0, M);
			}
			return;
		}
		
		for (int i = start; i < M; i++) {
			res[depth] = i;
			combination(i+1, depth+1);
		}
	}

	private static int play(int size) {
		int kill = 0;
		int []x = new int[3];
		int []y = new int[3];
		while(size > 0) {
			for (int i = 0; i < 3; i++) {
				int minDis = Integer.MAX_VALUE;
				x[i]=Integer.MAX_VALUE; y[i]=Integer.MAX_VALUE;
				
				for (int r = 0; r< N; r++) {
					for(int c = 0; c< M ;c++) {
						if(enemy[r][c] == 0) continue;
						
						int dis = Math.abs(N - r) + Math.abs(res[i] - c);
						if(dis > D) continue;
						if(minDis == dis && y[i] < c) continue;
						if(minDis >= dis) {
							minDis = dis;
							x[i] = r;
							y[i] = c;
						}
						
					}
				}
			}
			
			for (int i = 0; i < 3; i++) {
				if(x[i] == Integer.MAX_VALUE || enemy[x[i]][y[i]] == 0) continue;
				kill++;
				size--;
				enemy[x[i]][y[i]] = 0;
			}
			
			for (int r=N-1;r >= 0; r--) {
				for(int c = 0; c< M ;c++) {
					if(enemy[r][c] == 0) continue;
					enemy[r][c] = 0;
					if(r != N-1) enemy[r+1][c] = 1;
					else size--;
				}
			}
		
		}
		return kill;
		
	}
}