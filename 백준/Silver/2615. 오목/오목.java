import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 바둑판: 19 X 19
	 * 
	 * 1. 배열 입력 받기
	 * 2. dx, dy 방향으로 6번 이동 -> 5개인지 확인
	 * 	- 5번 이동 전 그 전에 그 값이 있는지도 확인할 것
	 * 3. 시작 번호 출력
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int N = 19;
		String[][] baduk = new String[N][];
		int[][] deltas = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}};
		
		for (int i = 0; i < N; i++) {
			baduk[i] = br.readLine().split(" ");
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(baduk[i][j].equals("0")) continue;
				
				for (int d = 0; d < deltas.length; d++) {
					int prevR = i - deltas[d][0];
					int prevC = j - deltas[d][1];
					if(prevR >= 0 && prevR < N && prevC >=0 && prevC < N && baduk[i][j].equals(baduk[prevR][prevC]))
						continue;
					
					int nr = i;
					int nc = j;
					int cnt = 1;
					for (int k = 0; k < 5; k++) {
						nr += deltas[d][0];
						nc += deltas[d][1];
						if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1 || !baduk[i][j].equals(baduk[nr][nc]))
							break;						
						cnt++;
					}
					if(cnt == 5) {
						sb.append(baduk[i][j]).append("\n").append(i+1).append(" ").append(j+1);
						System.out.println(sb);
						return;
					}
					
				}
			}
		}
		System.out.println(0);
	}

}