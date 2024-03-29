import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시간:  | 메모리: 
 *
 */
public class Main {
	static List<Integer> []relation;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		relation = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			relation[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			relation[a].add(b);
			relation[b].add(a);
		}
		
		for (int i = 0; i < N; i++) {
			findFriends(i, 1, 1 << i);
		}
		System.out.println(0);
	}
	
	private static void findFriends(int i, int cnt, int flag) {
		if(cnt > 4) {
			System.out.println(1);
			System.exit(0);
		}
		
		for (int next : relation[i]) {			
			if((flag & (1 << next)) != 0) continue;
			findFriends(next, cnt+1, flag | (1 << next));
		}
	}
		

}