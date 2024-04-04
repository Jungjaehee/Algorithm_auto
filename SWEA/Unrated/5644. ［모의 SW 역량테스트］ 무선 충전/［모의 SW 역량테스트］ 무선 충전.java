import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static class Battery{
		int y;
		int x;
		int C;
		int P;
		public Battery(int y, int x, int c, int p) {
			super();
			this.y = y;
			this.x = x;
			C = c;
			P = p;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		int[][] deltas = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			
			Battery[] BC = new Battery[A+1];
			BC[0] = new Battery(1, 1, 20, 0);
			int[][] users = new int[2][M+1];
			users[0][0] = users[1][0] = 0;
			
			int[] user1 = {1, 1};
			int[] user2 = {10, 10};
			
			List<Integer> list1 = new ArrayList<>();
			List<Integer> list2 = new ArrayList<>();			
			
			int sum = 0;
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= M; j++) {
					users[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());
				BC[i] = new Battery(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			for (int i = 0; i < M+1; i++) {
				user1[0] += deltas[users[0][i]][0];
				user1[1] += deltas[users[0][i]][1];
				user2[0] += deltas[users[1][i]][0];
				user2[1] += deltas[users[1][i]][1];
				for (int j = 0; j < A+1; j++) {
					int x = BC[j].x;
					int y = BC[j].y;
					int C = BC[j].C;
					if(Math.abs(user1[0]-x) + Math.abs(user1[1] - y) <= C) list1.add(j);
					if(Math.abs(user2[0]-x) + Math.abs(user2[1] - y) <= C) list2.add(j);
				}
				int max = Integer.MIN_VALUE;
				for(int u1 : list1) {
					for(int u2 : list2) {
						int p1 = BC[u1].P;
						int p2 = BC[u2].P;
						if(u1 == u2) p1 = p2 /=2;
						max = Math.max(max, p1+p2);
					}
				}
				sum += max;
				list1.clear();
				list2.clear();
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}

}