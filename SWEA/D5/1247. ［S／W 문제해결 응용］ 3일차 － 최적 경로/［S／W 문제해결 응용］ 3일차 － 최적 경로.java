import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	/**
	 * 
	 * N명의 고객 방문
	 * 위치 : (x, y),  거리 계산 : |x1-x2|+|y1-y2|
	 * 회사 -> 고객 방문 -> 집 경로
	 * 
	 * 1. 모든 경우의 수 따져보기 -> NPN 순열
	 *  - 거리 계산
	 * 
	 */
	static int[] arrX;
	static int[] arrY;
	static int N;
	static boolean[] isVisited;
	static int minDis;
	static int toX;
	static int toY;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <=TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			N = Integer.parseInt(br.readLine());
			arrX = new int[N];
			arrY = new int[N];
			isVisited = new boolean[N];
			minDis = Integer.MAX_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int fromX = Integer.parseInt(st.nextToken());
			int fromY = Integer.parseInt(st.nextToken());
			toX = Integer.parseInt(st.nextToken());
			toY = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				arrX[i] = Integer.parseInt(st.nextToken());
				arrY[i] = Integer.parseInt(st.nextToken());
			}
			permutation(0, fromX, fromY, 0);
			sb.append(minDis).append("\n");
		}
		System.out.println(sb);
	}
	private static void permutation(int depth, int x, int y, int distance) {
		if(minDis < distance) return;
		if(depth == N) {
			distance += Math.abs(toX-x) + Math.abs(toY - y);
			minDis = Math.min(minDis, distance);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			permutation(depth+1, arrX[i], arrY[i], (distance+Math.abs(x-arrX[i])+Math.abs(y-arrY[i])));
			isVisited[i] = false;
		}
		
	}

}