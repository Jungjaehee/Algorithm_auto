import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 14,616KB | 360ms
 *
 */
public class Main {
	static boolean row[][] = new boolean[9][10];
	static boolean col[][] = new boolean[9][10];
	static boolean square[][] = new boolean[9][10];

	static final int N = 81;
	static int[][] sudok = new int[9][9];
	
	static int[][] bin = new int[81][2];
	static int size = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<9;i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				sudok[i][j] = Integer.valueOf(str.charAt(j)) - '0';
				if(sudok[i][j] == 0) bin[size++] = new int[] {i, j};
			}
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				row[i][sudok[i][j]] = true;
				col[i][sudok[j][i]] = true;
				int square_idx = ((i*9+j)/27)*3 + (j*9+i)/27;
				square[square_idx][sudok[i][j]] = true;
			}
		}

		play(0);
		
	}
	
	private static void play(int depth) {
		if(depth == size) {
			print();
			System.exit(0);
		}
		
		int r = bin[depth][0];
		int c = bin[depth][1];
		
		int square_idx = ((r*9+c)/27)*3 + (c*9+r)/27;
		for(int i=1;i<=9;i++) {
			if(row[r][i] || col[c][i] || square[square_idx][i]) continue;
				
			sudok[r][c] = i;
			row[r][i] = col[c][i] = square[square_idx][i] = true;
				
			play(depth+1);
			row[r][i] = col[c][i] = square[square_idx][i] = false;
								
		}
	}

	private static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sudok[i][j]);
			}
			System.out.println();
		}
	}
}