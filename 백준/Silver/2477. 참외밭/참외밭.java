import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		final int line = 6;
		int[] dir = new int[line];
		int[] lens = new int[line];
		int res = N;
		
		for(int i=0;i<line;i++) {
			dir[i] = sc.nextInt();
			lens[i] = sc.nextInt();
		}
		

		for(int i=0;i<line*2;i++) {
			int j1 = i%line, j2 = (i+1)%line, j3 = (i+2)%line, j4 = (i+3)%line;
			if(dir[j1] == dir[j3] && dir[j2] == dir[j4]) {
				res *= (lens[j1] + lens[j3])*(lens[j2] + lens[j4]) - (lens[j2]*lens[j3]);
				break;
			}
		}
		System.out.println(res);
	}
}
