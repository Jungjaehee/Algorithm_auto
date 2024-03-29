import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] str = reader.readLine().split(" ");
		int S = Integer.parseInt(str[0]);
		int P = Integer.parseInt(str[1]);
		int cnt = 0;
		
		String DNA = " " + reader.readLine();
		String[] chars = {" ", "A", "C", "G", "T"};
		
		int[] charCond = new int[5];
		int[] charCnt = new int[5];
		
		
		Map<String, Integer> charNum = new HashMap<>(5);
		for (int i=0;i<5;i++) {
			charNum.put(chars[i], i);
		}
		
		str = reader.readLine().split(" ");
		for (int i = 1; i < 5; i++) {
			charCond[i] = Integer.parseInt(str[i-1]);
		}
		
		for (int i = 1; i < P; i++) {
			String k = String.valueOf(DNA.charAt(i));
			charCnt[charNum.get(k)]++;
		}
		
		boolean isPerfect = true;
		for (int i = P; i <= S; i++) {
			String k = String.valueOf(DNA.charAt(i-P));
			charCnt[charNum.get(k)]--;
			k = String.valueOf(DNA.charAt(i));
			charCnt[charNum.get(k)]++;
			
			isPerfect = true;
			for (int j=1;j<5;j++) {
				if(charCond[j] > charCnt[j]){
					isPerfect = false;
				break;
				}
			}
			if(isPerfect) cnt++;
		}
		System.out.println(cnt);
		
	}

}