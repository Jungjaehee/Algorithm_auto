import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int R;
	static int C;
	static char[][] map;  // 빵 집 거리 저장 배열
	static boolean[][] isVisited;  // 거리 방문 체크 배열
	static int[][] deltas = {{-1, 1}, {0, 1}, {1,1}}; // 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		map = new char[R][C];
		isVisited = new boolean[R][C];
		
		
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		for (int i = 0; i < R; i++) {
			searchPath(i, 0);  // 각 행에 대해 경로 탐색
		}
		
		System.out.println(cnt);
		
	}
	
	static boolean searchPath(int nr, int nc){
		if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1 || map[nr][nc] == 'x' || isVisited[nr][nc]) return false;
		if(nc == C-1) {  // 끝까지 도달한 경우, cnt 증가
			cnt++;
			return true;
		}
		isVisited[nr][nc] = true;  // 방문 체크
		for (int[] d: deltas) {
			if(searchPath(nr+d[0], nc+d[1])) return true;
		}
		return false;
	}
	
}