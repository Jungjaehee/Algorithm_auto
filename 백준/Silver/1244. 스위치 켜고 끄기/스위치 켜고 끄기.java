import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(reader.readLine());
		String[] switches = reader.readLine().split(" ");
//		String[] switcddd = new String[N+1];
//		for(int i=1; i<=N; i++) switcddd[i] = switches[i-1];
//		
		int M = Integer.parseInt(reader.readLine());
		
		String[] str;
		
		for(int i=0;i<M;i++) {
			str = reader.readLine().split(" ");
			String gender = str[0];
			int number  = Integer.parseInt(str[1]);
			
			switch(gender) {
			case "1":
				
				for(int j=1;(j*number-1)<N;j++) {
					switches[number*j-1] = switches[number*j-1].equals("0")? "1":"0";
				}
				break;
			case "2":
				switches[number-1] = switches[number-1].equals("0")? "1":"0";
				int cnt = 1;
				while(true) {
					int x = number-1+cnt;
					int y = number-1-cnt;
					if(x >= N || y<0 || !switches[x].equals(switches[y])) break;
					if(switches[x].equals(switches[y])){
						switches[y] = switches[x] = switches[x].equals("0")? "1":"0";
//						switches[y] = switches[x];	
					}
					cnt++;
				}
				break;
			}
			
		}

		for(int i=1;i<=N;i++) {
			System.out.printf("%s ", switches[i-1]);
			if(i%20 == 0)
				System.out.println();
		}
	}

}