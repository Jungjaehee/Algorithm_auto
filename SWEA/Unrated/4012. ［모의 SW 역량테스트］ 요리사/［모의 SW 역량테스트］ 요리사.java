import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int[][] foodCombi;
    static int N;
    static int minDiff;
    static int[] pickedA;
    static int[] pickedB;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
         
        for (int test_case = 1; test_case <=TC; test_case++) {
            sb.append("#").append(test_case).append(" ");
            N = Integer.parseInt(br.readLine());
            foodCombi = new int[N][N];
            minDiff = Integer.MAX_VALUE;
            pickedA = new int[N/2];
            pickedB = new int[N/2];
             
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    foodCombi[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            combination(0, 0);
             
            sb.append(minDiff).append("\n");
        }
        System.out.println(sb);
    }
    /**
     * N개 중 N/2개를 뽑은 재귀 함수, 뽑은 후 sum계산 및 최소값 갱신
     * @param start: 요소를 뽑을 시작 위치
     * @param depth: 조합 뽑은 횟수
     */
    private static void combination(int start, int depth) {
        if(depth == N/2) {
            int sum = 0, idxA = 0, idxB = 0;
            for (int i = 0; i < N; i++) {
                if(idxA < N/2 && i==pickedA[idxA]) { 
                    idxA++;
                    continue;
                }
                pickedB[idxB++] = i;
            }
            for (int i = 0; i < N/2; i++) {
                for (int j = i; j < N/2; j++) {
                    sum += foodCombi[pickedA[i]][pickedA[j]] + foodCombi[pickedA[j]][pickedA[i]]
                          - foodCombi[pickedB[i]][pickedB[j]] - foodCombi[pickedB[j]][pickedB[i]];
                }
            }
             
            minDiff = Math.min(minDiff, Math.abs(sum));
            return;
        }
         
        for (int i = start; i < N; i++) {
            pickedA[depth] = i;
            combination(i+1, depth+1);
        }
    }
 
}