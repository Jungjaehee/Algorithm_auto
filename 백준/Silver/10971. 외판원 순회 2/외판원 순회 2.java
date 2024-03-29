import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] city;
	static long minVal = Long.MAX_VALUE;
	static int start;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		city = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) { // 시작 위치 지정
			start = i;
			getPath(i, 0, 0, 1 << i);			
		}
		System.out.println(minVal);
	}
	
	private static void getPath(int vertex, int depth, long money, int isVisited) {
		if(money >= minVal) return;
		if(depth == N-1) {
			if(city[vertex][start] == 0) return;
			minVal = Math.min(minVal, money + city[vertex][start]);
			return;
		}
		
		for (int j = 0; j < N; j++) {
			if(city[vertex][j] == 0 || (isVisited & (1 << j)) != 0) continue;
			getPath(j, depth+1, money+city[vertex][j], (isVisited | (1 << j)));
		}
	}
	
	
}