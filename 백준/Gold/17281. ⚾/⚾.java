import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] order = new int[9];
	static int N;
	static int[][] players;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		players = new int[N][10];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/* 순서 정하기 , 1은 항상 4야*/
		int[] perm = {2, 3, 4, 5, 6, 7, 8, 9};
		order[3] = 1;
		int maxJum = Integer.MIN_VALUE;
		do {
			System.arraycopy(perm, 0, order, 0, 3);
			System.arraycopy(perm, 3, order, 4, 5);
			
			maxJum = Math.max(maxJum, play());
		} while(NextPermutation(perm));
		
		System.out.println(maxJum);
	}
	static boolean NextPermutation(int[] perm) {
		int N = perm.length;
		int i = N-1;
		while(i > 0 && perm[i-1] >= perm[i]) i--;
		if(i==0) return false;
		
		// i가 꼭대기
		int j = N-1;
		while(perm[j] <= perm[i-1]) j--;
		swap(perm, i-1, j);
		
		int k = N-1;
		while(i < k) swap(perm, i++, k--);
		
		return true;
	}
	
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static int play() {
		int jumsu = 0;
		int play = 0;
		for (int i = 0; i < N; i++) {
			int out = 3;
			boolean []present = new boolean[4];
			while(out > 0) {
				int hit = players[i][order[play++ % 9]];
				if(hit == 0) { 
					out--;
					continue;
				}
				present[0] = true;
				for (int j = 3; j >= 0; j--) {
					if(!present[j]) continue;
					present[j] = false;
					int idx = j + hit;
					if(idx > 3) {  // 홈 골인
						jumsu++;
						continue;
					}
					present[idx] = true;
				}
			}
		}
		
		return jumsu;
	}
	
}