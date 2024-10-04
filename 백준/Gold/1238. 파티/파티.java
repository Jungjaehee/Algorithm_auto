import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int to, time;
        
        Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }
    
    static int N, M, X;
    static List<List<Edge>> graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, time));
        }
        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            if (i != X) {
                int timeToX = dijkstra(i, X);
                int timeBackHome = dijkstra(X, i);
                maxTime = Math.max(maxTime, timeToX + timeBackHome);
            }
        }
        
        System.out.println(maxTime);
    }
    
    static int dijkstra(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.time));
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        
        distances[start] = 0;
        pq.add(new Edge(start, 0));
        
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentNode = current.to;
            
            for (Edge edge : graph.get(currentNode)) {
                if (distances[currentNode] + edge.time < distances[edge.to]) {
                    distances[edge.to] = distances[currentNode] + edge.time;
                    pq.add(new Edge(edge.to, distances[edge.to]));
                }
            }
        }
        
        return distances[end];
    }
}