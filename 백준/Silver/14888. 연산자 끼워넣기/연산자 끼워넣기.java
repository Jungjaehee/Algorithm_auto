import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static char[] operator = {'+', '-', '*', '/'};
	static int[] nums;
	static char[] operArr;
	static boolean[] isSelected;
	static int min=Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		operArr = new char[N-1];
		isSelected = new boolean[N-1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int k = 0;
		for (int i = 0; i < 4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				operArr[k++] = operator[i];
			}
		}
		
		permu(0, nums[0]);
		System.out.println(max + "\n" + min);
	}
	private static void permu(int depth, int res) {
		if(depth == N-1) {
			min = min > res? res:min;
			max = max < res? res:max;
			return;
		}
		for (int i = 0; i < N-1; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			switch(operArr[i]) {
			case '+':				
				permu(depth+1, res+nums[depth+1]);
				break;
			case '-':
				permu(depth+1, res-nums[depth+1]);
				break;
			case '*':
				permu(depth+1, res*nums[depth+1]);
				break;
			case '/':
				permu(depth+1, res/nums[depth+1]);
				break;
			}
			isSelected[i] = false;
		}
		
	}

}