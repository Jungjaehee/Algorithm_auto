import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * DFS 이용 
 * 시간: 312ms | 메모리: 13,664KB
 */
public class Main {
	static int N;
	static String[][] graph;
	static boolean[] isVisited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new String[N][];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			graph[i] = br.readLine().split(" ");
		}
		
		for (int i = 0; i < N; i++) {
			isVisited = new boolean[N];
			searchPath(i);
			
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static String[] searchPath(int i) {
		if(isVisited[i])
			return graph[i];
		
		isVisited[i] = true;
		for (int j = 0; j < N; j++) {
			if(graph[i][j].equals("0")) continue;
			merge(graph[i], searchPath(j));
		}
		return graph[i];
	}
	
	private static void merge(String[] str1, String[] str2) {
		for (int i = 0; i < N; i++) {
			if(str2[i].equals("1")) str1[i] = "1";
		}
		
	}
}