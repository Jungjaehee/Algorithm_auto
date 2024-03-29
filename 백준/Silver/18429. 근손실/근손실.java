import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Main {
	static int cnt = 0;
	static List<Integer> obtain;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] str=null;
		int N=0, k=0;
		try {
			str = reader.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			k = Integer.parseInt(str[1]);
			obtain = new ArrayList<Integer>(N);
			str = reader.readLine().split(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<N;i++) {
			obtain.add(Integer.valueOf(str[i]));
		}
		
		for(int i=0;i<N;i++) {
			if(obtain.get(i) >= k) {
				int[] visited = new int[N];
				visited[i] = 1;
				planning(k, 1, obtain.get(i), visited);
				visited[i] = 0;
			}
		}
		System.out.println(cnt);
	}
	static void planning(int k, int n, int sum,int[] visited) {
		if(n == obtain.size()) {
			cnt++;
			return;
		}
		for(int i=0;i<obtain.size();i++) {
			if(obtain.get(i) >= (n+1)*k-sum && visited[i] == 0) {
				visited[i] = 1;
				planning(k, n+1, sum+obtain.get(i), visited);
				visited[i] = 0;
			}
		}
	}

}
