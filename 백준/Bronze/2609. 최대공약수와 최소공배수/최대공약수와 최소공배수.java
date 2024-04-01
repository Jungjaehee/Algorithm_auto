import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		System.out.println(GCD(a, b));
		System.out.println(LCD(a, b));
	}
	static int GCD(int a, int b) {
		if(a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		if(a%b == 0) return b;
		return GCD(b, a%b);
	}
	
	static int LCD(int a, int b) {
		return (a*b) / GCD(a, b);
	}
}