import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * L, R은 방향만 변경, 이동 X
 * 거북이가 이동한 영역을 모두 포함할 수 있는 가장 작은 직사각형의 넓이
 * 이동 길이는 500이하
 * 0: 위쪽, 1: 아래쪽, 2: 오른쪽, 3: 왼쪽
 * 0, 0에서 출발, 북쪽 방향
 * 
 * L 위(4, 0) -> 왼(3) -> 아래(2) -> 오(1) -> 위(0)
 * R 위(0) -> 오(1) -> 아래(2) -> 왼(3) -> 위(4, 0)
 * 
 * 0~500 x_min, x_max, y_min, y_max 업데이트하면서 시뮬레이션 진행
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {0, 0}};
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < T; test_case++) {
			int r_min = 0;
			int r_max = 0;
			int c_min = 0;
			int c_max = 0;
			
			int cur_r = 0;
			int cur_c = 0;
			int cur_dir = 0;
			
			String command = br.readLine();
			
			for (int i = 0; i < command.length(); i++) {
				char c = command.charAt(i);
				
				int new_dir = 0;
				int step_r = 0;
				int step_c = 0;
				
				switch(c) {
					
					case 'F' :
						step_r = deltas[cur_dir][0];
						step_c = deltas[cur_dir][1];
						new_dir = 0;
						break;
					case 'B' :
						step_r = -deltas[cur_dir][0];
						step_c = -deltas[cur_dir][1];
						new_dir = 0;
						break;
					case 'L' :
						step_r = 0;
						step_c = 0;
						new_dir = -1;
						break;
					case 'R' :
						step_r = 0;
						step_c = 0;
						new_dir = 1;
						break;
				}
				cur_r += step_r;
				cur_c += step_c;
				
				cur_dir = (cur_dir+new_dir + 4) % 4;
				r_min = Math.min(r_min, cur_r);
				r_max = Math.max(r_max, cur_r);
				c_min = Math.min(c_min, cur_c);
				c_max = Math.max(c_max, cur_c);
			}
			
			int square = (r_max-r_min) * (c_max - c_min);

			sb.append(square).append("\n");
			
		}
		System.out.println(sb);

	}

}