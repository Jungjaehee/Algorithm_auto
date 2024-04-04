import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Person{
		int r;
		int c;
		int[] distance = new int[2];
		int pick;
		int move;

		public Person(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static int N;
	static int[][] map;
	static int[] select;
	static Person[] persons;
	static int[][] stair;
	static int size;
	static int minTime;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			persons = new Person[10];
			stair = new int[2][3]; 
			
			size = 0;
			int idx = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						persons[size++] = new Person(i, j);
					}
					else if(map[i][j] > 1) {
						stair[idx][0] = i;
						stair[idx][1] = j;
						stair[idx++][2] = map[i][j];
					}

				}
			}
			minTime = Integer.MAX_VALUE;
			
			for (int i = 0; i < size; i++) {
				persons[i].distance[0] = Math.abs(stair[0][0] - persons[i].r) + Math.abs(stair[0][1] - persons[i].c);
				persons[i].distance[1] = Math.abs(stair[1][0] - persons[i].r) + Math.abs(stair[1][1] - persons[i].c);
			}
			select = new int[size];
			selectStair(0);
			sb.append("#").append(test_case).append(" ").append(minTime).append("\n");
		}
		System.out.println(sb);
	}
	
	static void selectStair(int depth) {
		if(depth == size) {
			
			int time = move();
			minTime = Math.min(minTime, time);
			return;
		}
		
		persons[depth].pick = 0;
		select[depth] = persons[depth].distance[0]+1;
		selectStair(depth+1); // depth번째 사람의 1번 계단 선택
		
		persons[depth].pick = 1;
		select[depth] = persons[depth].distance[1]+1;
		selectStair(depth+1);    // depth번째 사람의 2번 계단 선택
	}

	private static int move() {
		int time = 0;
		int[] sc = new int[2];
		Queue<Person> queue = new ArrayDeque<>();
		Queue<Person>[] downStair = new Queue[2];
		downStair[0] = new ArrayDeque<>();
		downStair[1] = new ArrayDeque<>();
		
		Queue<Person>[] wait = new Queue[2];
		wait[0] = new ArrayDeque<>();
		wait[1] = new ArrayDeque<>();
		
		for (int i = 0; i < size; i++) {
			persons[i].move = select[i];
			queue.offer(persons[i]);
		}
		
		int peoples = size;
		while(peoples > 0) {
			int Qsize = queue.size();
			while(Qsize -- > 0) {
				Person p = queue.poll();
				
				if(--p.move == 0) {
					p.move = stair[p.pick][2];
					wait[p.pick].offer(p);
					continue;
				}
				
				queue.offer(p);
			}
			
			Qsize = downStair[0].size();
			while(Qsize -- > 0) {
				Person p = downStair[0].poll();
				
				if(--p.move == 0) {
					peoples--;
					continue;
				}
				downStair[0].offer(p);
			}
			
			Qsize = downStair[1].size();
			while(Qsize -- > 0) {
				Person p = downStair[1].poll();
				
				if(--p.move == 0) {
					peoples--;
					continue;
				}
				downStair[1].offer(p);
			}
			
			Qsize = wait[0].size();
			while(Qsize-- > 0) {
				if(downStair[0].size() > 2) break;
				
				downStair[0].offer(wait[0].poll());
			}
			
			Qsize = wait[1].size();
			while(Qsize-- > 0) {
				if(downStair[1].size() > 2) break;
				
				downStair[1].offer(wait[1].poll());
			}
			
			if(minTime < ++time) break;
		}
		
		return time;
	} 

}