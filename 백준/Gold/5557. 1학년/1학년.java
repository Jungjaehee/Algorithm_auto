import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] number = new int[N];
		long[][] cal = new long[N-1][21];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		cal[0][number[0]] = 1;
		for (int i = 1; i < N-1; i++) {
			for (int j = 0; j < 21; j++) {
				if(cal[i-1][j] > 0) {
					if(j + number[i] <= 20)
						cal[i][j+number[i]] += cal[i-1][j];
					if(j-number[i] >= 0) 
						cal[i][j-number[i]] += cal[i-1][j];
				}
			}
		}
		
		System.out.println(cal[N-2][number[N-1]]);
	}

}