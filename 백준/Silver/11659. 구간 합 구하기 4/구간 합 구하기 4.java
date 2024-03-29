import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = reader.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		
		int[] sum = new int[N+1];
		
		inputs = reader.readLine().split(" ");
		for(int k=1;k<=N;k++) {
			sum[k] += sum[k-1] + Integer.parseInt(inputs[k-1]);
		}
		for (int k = 0; k < M; k++) {
			inputs = reader.readLine().split(" ");
			System.out.println(sum[Integer.parseInt(inputs[1])] 
					- sum[Integer.parseInt(inputs[0])-1]);
		}
		
		
		
	}

}