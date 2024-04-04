import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	static String[][] sadari;
	static int N = 100;
	static int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("res/input_1210.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case=1;test_case<=10;test_case++) {
			reader.readLine();
			sadari = new String[N][];
			int x_idx = -1;
			for (int i = 0; i < N; i++) {
				sadari[i] = reader.readLine().split(" ");
			}
			
			for (int i = 0; i < N; i++) {
				if(sadari[N-1][i].equals("2")) {
					x_idx = i;
					break;
				}
			}
			System.out.print("#" + test_case + " ");
			getStart(N-1, x_idx);
		}
	}
	
	static void getStart(int r, int c) {
		if(r==0) {
			System.out.println(c);
			return;
		}
		for(int d = 0; d<deltas.length;d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			if(nr < 0 || nr > N-1|| nc < 0 || nc > N-1) continue;
			if(sadari[nr][nc].equals("1")) {
				sadari[nr][nc] = "0";
				getStart(nr, nc);
				break;
			}
		}
	}

}
