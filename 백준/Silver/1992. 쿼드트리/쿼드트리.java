import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/**
	 * 1. 분할 정복
	 * 2. 작이지는 경우 "(" 추가 
	 * 3. 작아지는 게 끝나면 ")" 추가
	 * 4. 반복문으로 탐색
	 * 
	 */
	
	static char[][] arr;
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			String st = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = st.charAt(j);				
			}
		}
		divide(0, 0, N);
		System.out.println(sb);
	}
	private static void divide(int r, int c, int n) {
		if(n == 1 || sameValCheck(r, c, n)) {
			sb.append(arr[r][c]);
			return;
		}
		
		sb.append("(");
		
		int newN = n/2;
		divide(r, c, newN);
		divide(r, c+newN, newN);
		divide(r+newN, c, newN);
		divide(r+newN, c+newN, newN);
		sb.append(")");
	}
	
	private static boolean sameValCheck(int r, int c, int n) {
		char ch = arr[r][c];
		for (int i = r; i < r+n; i++) {
			for (int j = c; j < c+n; j++) {
				if(ch != arr[i][j]) return false;
			}
		}
		return true;
	}

}