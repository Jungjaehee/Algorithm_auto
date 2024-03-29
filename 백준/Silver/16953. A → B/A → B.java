import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<Integer> set;
	static int minCnt = Integer.MAX_VALUE;
	static long B;
	static boolean flag = true;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		set = new HashSet<>();
		
		calculation(A, 0);
		System.out.println(minCnt == Integer.MAX_VALUE? -1:minCnt+1);
	}

	private static void calculation(long num, int cnt) {
		if(num > B) {
			return;
		}
		else if(num == B) {
			minCnt = Math.min(minCnt, cnt);
			return;
		}
		
		calculation(num*10+1, cnt+1);
		calculation(num*2, cnt+1);
	}

}