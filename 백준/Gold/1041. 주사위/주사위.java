import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 그리디 형식 + 
 */
public class Main {
	static class Pair implements Comparable<Pair>{
		long num1;
		long num2;
		
		public Pair(long num1, long num2) {
			this.num1 = num1;
			this.num2 = num2;
			sort();
		}
		private void sort() {
			if(num1 > num2) {
				long temp = num2;
				num2 = num1;
				num1 = temp;
			}
				
		}
		@Override
		public int compareTo(Pair o) {
			return Long.compare(this.num1, o.num1);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		
		
		long[] number = new long[6];
		// [A, F], [B, E], [C, D]
		// [0, 5]  [1, 4]  [2, 3]
		StringTokenizer st = new StringTokenizer(br.readLine());
		long maxVal = 0;
		long sumVal = 0;
		for (int i = 0; i < 6; i++) {
			number[i] = Long.parseLong(st.nextToken());
			maxVal = Math.max(maxVal, number[i]);
			sumVal += number[i];
		}
		
		if(N == 1) {
			System.out.println(sumVal - maxVal);
			return;
		}
		long[] count =  new long[3];
		count[0] = (N-2) * (N-2) + (N-1)*(N-2)*4;
		count[1] = (N-1) * 4 + (N-2) * 4;
		count[2] = 4;
		
		Pair[] pairs = new Pair[3];
		pairs[0] = new Pair(number[0], number[5]);
		pairs[1] = new Pair(number[1], number[4]);
		pairs[2] = new Pair(number[2], number[3]);
		
		Arrays.sort(pairs);
		long[] sum = new long[3];
		sum[0] = pairs[0].num1;
		sum[1] = sum[0] + pairs[1].num1;
		sum[2] = sum[1] + pairs[2].num1;
		long total = count[0]*sum[0] + count[1]*sum[1] + count[2]*sum[2];
		
		System.out.println(total);
	
	}

}