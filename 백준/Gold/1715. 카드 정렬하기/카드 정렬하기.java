import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		
		for(int i=0;i<N;i++) {
			int val = Integer.parseInt(br.readLine());
			queue.offer(val);
		}
		
		int sum = 0;
		int cards = 0;
		while(queue.size() > 1) {
			cards = queue.poll() + queue.poll();
			sum += cards;
			queue.offer(cards);
			
		}

		System.out.println(sum);
	}

}