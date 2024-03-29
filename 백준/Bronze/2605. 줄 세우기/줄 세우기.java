import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int idx;
		int N = sc.nextInt();
		ArrayList<Integer> student = new ArrayList<Integer>(N);
		
		for(int i=0;i<N;i++) {
			idx = sc.nextInt();
			student.add(i-idx, i+1);
		}
		for(int i=0;i<N;i++) {
			System.out.print(student.get(i) + " ");
		}
	}

}
