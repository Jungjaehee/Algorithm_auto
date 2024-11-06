import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 중복 제거 후 중복 조합 뽑기
	 * @param args
	 * @throws IOException 
	 */
	static Object[] nums;
	static int M;
	static int[] selected;
	static StringBuilder sb;
	static int newNs;
	private static int j;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Set<Integer> arr = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		nums = arr.toArray();
		Arrays.sort(nums);
		selected = new int[M];
		comb(0, 0);
		System.out.println(sb);
	}
	
	static void comb(int depth, int start) {
		if(depth == M) {
			for (int j = 0; j < M; j++) {
				sb.append(selected[j]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start;i<nums.length;i++) {
			selected[depth] = (int) nums[i];
			comb(depth+1, i);
		}
	}

}