
import java.util.Scanner;

class Solution {
	static char[] arr;
	static int cnt;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1;test_case <=T;test_case++) {
			arr = sc.next().toCharArray();
			int[] init = new int[arr.length];
			cnt = 0;
			System.out.print("#" + test_case + " ");
			getChangeCount(0, init);		
			
		}
	}
	private static void getChangeCount(int idx, int[] init) {
		if(idx == arr.length) {
			System.out.println(cnt);
			return;
		}
		if(init[idx] != (arr[idx] - '0')) {
			for (int i = idx; i < init.length; i++) {
				init[i] = arr[idx]-'0';
			}
			cnt++;
		}
		getChangeCount(idx+1, init);
	}

}
