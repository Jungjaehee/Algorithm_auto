import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	/**
	 * [ 문제 해석 ]
	 * 레이저 탑 N개
	 * 각각 다른 높이, <- 으로 레이저 쏨
	 * 탑의 높이가 같거나 큰 경우만 레이저 받을 수 있음
	 * N: 1~500,000 => 1.5s 제한
	 * 
	 * [ 문제 해결 프로세스 ]
	 * 
	 * 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> stackIdx = new Stack<Integer>();
		Stack<Integer> maxSave = new Stack<Integer>();
		Stack<Integer> maxSaveIdx = new Stack<Integer>();
		
		int[] res = new int[N];
		
		for (int i = 1; i <=N; i++) {
			int val = Integer.parseInt(st.nextToken());
			stack.push(val);
			stackIdx.push(i);
		}
		
		while(!stack.isEmpty()) {
			int val = stack.pop();
			int idx = stackIdx.pop();
			if(maxSave.isEmpty() || val < maxSave.peek()) {
				maxSave.push(val);
				maxSaveIdx.push(idx);
				continue;
			}
			while(!maxSave.isEmpty() && val >= maxSave.peek()) {
				maxSave.pop();
				res[maxSaveIdx.pop()-1] = idx;
			}
			maxSave.push(val);
			maxSaveIdx.push(idx);
		}
		System.out.println(Arrays.toString(res).replaceAll("[^0-9\\s]", ""));		
		
	}

}