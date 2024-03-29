import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char[] characters;
	static char[] res;
	static int L;
	static int C;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		characters = new char[C];
		res = new char[L];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < C; i++) {
			characters[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(characters);
		combination(0, 0, 0, 0);
		
		System.out.println(sb);
		
	}
	private static void combination(int start, int depth, int N, int M) {
		if(depth == L) {
			if(N >= 1 && M >= 2)
				sb.append(String.valueOf(res)).append("\n");
			return;
		}
		
		for (int i = start; i < C; i++) {
			char ch = characters[i];
			res[depth] = ch;
			if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') combination(i+1, depth+1, N+1, M);
			else combination(i+1, depth+1, N, M+1);
		}
	}

}