import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/**
	 * N 킬로그램 배달 -> 3킬로, 5킬로 봉지
	 * 
	 * 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		while(N > 0) {
			if(N % 5 == 0) {
				cnt += (N/5);
				N %= 5;
				break;
			}
			N -= 3;
			cnt++;
		}
		System.out.println(N==0? cnt: -1);
	}

}