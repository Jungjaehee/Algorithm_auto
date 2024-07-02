import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int TC = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case<= TC; test_case++) {
            sb.append("#").append(test_case).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int pow_N = (int) Math.pow(2, N)-1;
            if((M & pow_N) == pow_N)
                sb.append("ON");

            else
                sb.append("OFF");

            sb.append("\n");
        }

        System.out.println(sb);
    }
}