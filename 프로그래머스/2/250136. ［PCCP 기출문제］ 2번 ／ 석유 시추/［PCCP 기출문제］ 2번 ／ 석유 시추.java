import java.util.*;
class Solution {
    static int oil = 0;
    static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int n=0, m=0;
    static int[][] cpLand;
    static int[][] isVisited;
    static int visitNum = 0;
    public int solution(int[][] land) {
        int answer = 0;
        cpLand = land;
        n = land.length;
        m = land[0].length;
        visitNum = 1;
        Map<Integer, Integer> map = new HashMap<>();
        isVisited = new int[n][m];
        for(int i=0;i<m;i++){
            int total = 0;
            boolean[] isMap = new boolean[n*m];
            for(int j=0;j<n;j++){
                if(land[j][i] == 0) continue;
                if(isVisited[j][i] > 0){
                    if(isMap[isVisited[j][i]]) continue;
                    isMap[isVisited[j][i]] = true;
                    total += map.get(isVisited[j][i]);
                    continue;
                }
                isVisited[j][i] = visitNum;
                oil = 1;
                dfs(j, i);
                total += oil;
                isMap[visitNum] = true;
                map.put(visitNum++, oil);
            }
            answer = Math.max(answer, total);
        }
        return answer;
    }
    static void dfs(int i, int j){
        for(int d = 0;d<4;d++){
            int nr = i + deltas[d][0];
            int nc = j + deltas[d][1];
            if(nr < 0 || nr > n-1 || nc < 0 || nc > m-1 || isVisited[nr][nc] > 0 || cpLand[nr][nc] == 0) continue;
            isVisited[nr][nc] = visitNum;
            oil++;
            dfs(nr, nc);
        }
    }
}