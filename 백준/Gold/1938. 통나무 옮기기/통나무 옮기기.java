import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static class Node{
		int[][] position = new int[3][2];
		int mode;  // 가로 : 1, 세로 : 3
		
		@Override
		public String toString() {
			return "Node [position=" + Arrays.deepToString(position) + ", mode=" + mode + "]";
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1} };
		
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][];
		Node train = new Node();
		Node dest = new Node();
		
		int train_idx = 0;
		int dest_idx = 0;
		for (int i = 0; i < N; i++) {
			map[i]  = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'B') {
					train.position[train_idx][0] = i;
					train.position[train_idx++][1] = j;
					map[i][j] = '0';
				}
				else if(map[i][j] == 'E') {
					dest.position[dest_idx][0] = i;
					dest.position[dest_idx++][1] = j;
					map[i][j] = '0';
				}
			}
		}
		train.mode = (train.position[0][0] == train.position[1][0])? 1 : 3;
		dest.mode = (dest.position[0][0] == dest.position[1][0])? 1 : 3;
	
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][][] isVisited = new boolean[4][N][N];
		
		queue.offer(train);
		isVisited[train.mode][train.position[1][0]][train.position[1][1]] = true;
		
		int cnt = 0;
		Node cur;
		while(!queue.isEmpty()) {
			int size = queue.size();
			Q: while(size-- > 0) {
				cur = queue.poll();
				// 4방
				A: for (int d = 0; d < 4; d++) {
					Node next = new Node();
					boolean flag = true;
					for (int i = 0; i < 3; i++) {
						int nr = cur.position[i][0] + deltas[d][0];
						int nc = cur.position[i][1] + deltas[d][1];
						
						if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1 || map[nr][nc] == '1') continue A;
						next.position[i][0] = nr;
						next.position[i][1] = nc;
						
						if(nr != dest.position[i][0] || nc != dest.position[i][1])
							flag = false;
					}

					int center_r = next.position[1][0];
					int center_c = next.position[1][1];
					if(isVisited[cur.mode][center_r][center_c])
						continue;
					
					if(flag && cur.mode == dest.mode) {
						System.out.println(cnt+1);
						return;
					}
					
					next.mode = cur.mode;
					queue.offer(next);
					isVisited[next.mode][center_r][center_c] = true;	
				}
				
				// 회전
				for (int i = 0; i < 3; i++) {
					for (int d = 0; d < 2; d++) {
						int nr = cur.position[i][0] + deltas[cur.mode - d][0];
						int nc = cur.position[i][1] + deltas[cur.mode - d][1];
						if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1 || map[nr][nc] == '1') continue Q;
					}
				}
				int mode = (cur.mode + 2) % 4;
				if(isVisited[mode][cur.position[1][0]][cur.position[1][1]])
					continue;
				
				
				Node turn = new Node();
				turn.position[1] = cur.position[1];
				turn.mode = mode;
				turn.position[0][0] = cur.position[1][0] + deltas[cur.mode-1][0];
				turn.position[0][1] = cur.position[1][1] + deltas[cur.mode-1][1];
				turn.position[2][0] = cur.position[1][0] + deltas[cur.mode][0];
				turn.position[2][1] = cur.position[1][1] + deltas[cur.mode][1];
				
				boolean flag = true;
				for (int i = 0; i < 3; i++) {
					if(turn.position[i][0] != dest.position[i][0] || turn.position[i][1] != dest.position[i][1])
						flag = false;
				}
				
				if(flag && mode == dest.mode) {
					System.out.println(cnt+1);
					return;
				}
				
				queue.offer(turn);
				
				isVisited[mode][cur.position[1][0]][cur.position[1][1]] = true;
				
			}
			cnt++;
		}
		
		System.out.println(0);
	}

}