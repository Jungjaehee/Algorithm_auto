import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 84ms | 메모리: 11,808KB
 *
 */
public class Main {
	static int[] peoples;   // 각 구역의 인구수 저장
	static int[][] adjList; // 연결 정보 저장
	static boolean[] ischeck;  // 체크했었던 조합인 경우를 저장
	static int N;        // 전체 구역 수
	static int NP = 0;  // 전체 인구 수
	static int minDiff = Integer.MAX_VALUE;  // 인구 수 차이의 최소값 저장
	static int full;       // 비트 마스킹에서 모두 다 선택된 경우를 고려하기 위해 사용 
	static int isSelect;   // DFS에서 방문체크를 위해 사용
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		full = (int) (Math.pow(2, N+1) - 2);  // 오른쪽 1bit를 제외하고 1~N+1 bit자리를 1로 꽉 채웠을 때, 값 계산
		peoples = new int[N+1];
		ischeck = new boolean[full+1];
		adjList = new int[N+1][];             // 정점마다 연결된 정점이 다름
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
			NP += peoples[i];
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			adjList[i] = new int[cnt];
			for (int j = 0; j < cnt; j++) {
				int n = Integer.parseInt(st.nextToken());
				adjList[i][j] = n;
			}
		}
		getRegion(1, 0, 0);
		System.out.println(minDiff == Integer.MAX_VALUE? -1:minDiff);
	}
	
	/*
	 * 1. 하나의 선거구에 포함될 구역들 구하기 -> 부분집합 이용
	 * 2. 하나의 선거구에 구역들이 하나도 포함되지 않은 경우와 모두 다 포함된 경우는 제외
	 * 3. DFS를 통해 선택한 구역들과 선택하지 않은 구역들이 각각 연결되어있는지 DFS를 통해 검사
	 * 
	 */
	static void getRegion(int depth, int select, int nPeoples) {
		if(depth == N+1) {
			if(select == full || select == 0) return;
			if(ischeck[select] || ischeck[full-select]) return;  // 선택된 구역들뿐만 아니라, 선택되지 않은 구역들을 체크하기 때문에 중복 줄이기위함
			
			if(!isConnect(select, true) || !isConnect(select, false) ) return;  // true: 선택된 구역들이 연결되어있는가? | false: 선택되지 않은 구역들이 연결되어 있는가?
			
			ischeck[select] = ischeck[full-select] =true;
			minDiff = Math.min(minDiff, Math.abs(NP-nPeoples-nPeoples));  // 인구 수 차이 최소값 갱신
			return;
		}
		
		getRegion(depth+1, select | 1 << depth, nPeoples+peoples[depth]);  // 구역 선택 후 재귀 호출
		getRegion(depth+1, select, nPeoples);   // 구역을 선택하지 않고 재귀 호출
	}
	
	private static boolean isConnect(int select, boolean flag) {  // 구역들이 연결되어있는가 검사 (DFS이용)
		isSelect = select;
		for (int i = 1; i <= N; i++) {
			if(flag ^ (isSelect &(1 << i)) == 0) {  // flag가 true일 때, 1이상 값
				isSelect ^= (1 << i); 
				DFS(i, flag);
				break;
			}
		}
		return isSelect == 0 || isSelect == full;
	}
	
	private static void DFS(int start, boolean flag) {
		for (int c : adjList[start]) {
			if(flag ^ (isSelect & (1 << c)) == 0) {
				isSelect ^= (1<<c);
				DFS(c, flag);
			}
		}
	}
}