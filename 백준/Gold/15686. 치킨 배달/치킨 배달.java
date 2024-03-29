import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 치킨 거리: 집과 가장 가까운 치킨집 사이의 거리
	 * 
	 * 0: 빈 칸, 1: 집, 2: 치킨 집
	 * 도시의 치킨 거리를 가장 작게하는 M개 치킨집
	 * 
	 * N 개중 M개 뽑는 조합 -> 거리 계산 -> 최소가 되도록 갱신
	 * 
	 * 집 배열, 치킨 배열
	 * 
	 * 시간: 148ms | 메모리: 13,092KB
	 * 
	 * @param args
	 */
	static int[][] house;
	static int[][] chicken;
	static int houseCnt;
	static int chickenCnt;
	static int[] picked;
	static int M;
	static int disSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		house = new int[N*N][];
		chicken = new int[N*N][];
		houseCnt = 0;
		chickenCnt = 0;
		disSum = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				String s = st.nextToken();
				if(s.equals("0")) continue;
				else if(s.equals("2")) chicken[chickenCnt++] = new int[] {i, j};
				else house[houseCnt++] = new int[] {i, j};
			}
		}
		picked = new int[chickenCnt];
		for (int i = chickenCnt-1; i >= chickenCnt-M; i--) picked[i] = 1;
		
		do {
			int sum = 0;
			for (int i = 0; i < houseCnt; i++) {
				int minDis = Integer.MAX_VALUE;
				for (int j = 0; j < chickenCnt; j++) {
					if(picked[j] == 1) {
						minDis = Math.min(minDis, Math.abs(house[i][0]-chicken[j][0])+Math.abs(house[i][1]-chicken[j][1]));
					}
				}
				sum += minDis;
				if(sum > disSum) break;
			}
			disSum = Math.min(disSum, sum);
		}while(nextPermutation(picked));
		
		System.out.println(disSum);
	}
	
	static boolean nextPermutation(int[] picked) {
		int i = chickenCnt-1;
		while(i>0 && picked[i] <= picked[i-1]) i--;
		
		if(i == 0) return false;
		
		int j = chickenCnt-1;
		while(picked[i-1] >= picked[j]) j--;
		swap(picked, i-1, j);
		
		int k = chickenCnt-1;
		while(i < k) swap(picked, i++, k--);
		
		return true;
		
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}