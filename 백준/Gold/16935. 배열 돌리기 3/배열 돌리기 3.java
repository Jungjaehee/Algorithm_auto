import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 
	 * 
	 * 	 
	 */
	static String[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		arr = new String[N][];
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().split(" ");
		}
		
		String[] cals = br.readLine().split(" ");
		for (int i = 0; i < R; i++) {
			switch(cals[i]) {
				case "1":
					RotateArrUD();
					break;
				case "2":
					RotateArrLR();
					break;
				case "3":
					RotateArrR90();
					break;
				case "4":
					RotateArrL90();
					break;
				case "5":
					RotateArrPartR();
					break;
				case "6":
					RotateArrPartL();
					break;
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
	}
	
	static void print(String[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
	/* 1번 연산 */
	static void RotateArrUD() {
		String tmp;
		for (int i = 0; i < arr.length/2; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				tmp = arr[i][j];
				arr[i][j] = arr[arr.length-i-1][j];
				arr[arr.length-i-1][j] = tmp;
			}
		}
	}
	
	/* 2번 연산 */
	static void RotateArrLR() {
		String tmp;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length/2; j++) {
				tmp = arr[i][j];
				arr[i][j] = arr[i][arr[i].length-j-1];
				arr[i][arr[i].length-j-1] = tmp;
			}
		}
	}
	
	/* 3번 연산 */
	static void RotateArrR90() {
		String[][] res = new String[arr[0].length][arr.length];
		
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				res[i][j] = arr[arr.length - j -1][i];
			}
		}
		arr = res;
	}
	
	/* 4번 연산 */
	static void RotateArrL90() {
		String[][] res = new String[arr[0].length][arr.length];
		
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				res[i][j] = arr[j][arr[0].length-i-1];
			}
		}
		arr = res;
	}
	
	/* 5번 연산 */
	static void RotateArrPartR() {
		int n = arr.length/2;
		int m = arr[0].length/2;
		int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

		String[][] res = new String[arr.length][arr[0].length];
		String[][] tmp = new String[n][m];
		
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				tmp[j][k] = arr[j][k];
			}
		}
		int r = 0, c = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + deltas[i][0]*n;
			int nc = c + deltas[i][1]*m;
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					res[nr+j][nc+k] = tmp[j][k];					
					tmp[j][k] = arr[nr+j][nc+k];
				}
			}
			r = nr; c=nc;
		}
		arr = res;
	}
	
	/* 6번 연산 */
	static void RotateArrPartL() {
		int n = arr.length/2;
		int m = arr[0].length/2;
		int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

		String[][] res = new String[arr.length][arr[0].length];
		String[][] tmp = new String[n][m];
		
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				tmp[j][k] = arr[j][k];
			}
		}
		int r = 0, c = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + deltas[i][0]*n;
			int nc = c + deltas[i][1]*m;
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					res[nr+j][nc+k] = tmp[j][k];					
					tmp[j][k] = arr[nr+j][nc+k];
				}
			}
			r = nr; c=nc;
		}
		arr = res;
	}
}