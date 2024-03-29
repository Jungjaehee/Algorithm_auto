import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<int[]> stack = new ArrayDeque<>();
		
		int[] work = {0, 0};
		int jumsu = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if(st.countTokens() == 1) {
				work[1]--;
			}
			else{
				if(work[1] > 0) stack.push(work.clone());
				
				String s = st.nextToken();
				work[0] = Integer.parseInt(st.nextToken());
				work[1] = Integer.parseInt(st.nextToken())-1;
			}
			
			if(work[1] == 0) {
				jumsu += work[0];
				if(!stack.isEmpty())
					work = stack.pop();
			}
			
		}
		System.out.println(jumsu);
	}

}