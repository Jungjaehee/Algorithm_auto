import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++)
        {

            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] ary_n = new int[N];
            int[] ary_m = new int[M];

            for(int i=0;i<N;i++)
                ary_n[i] = sc.nextInt();

            for(int i=0;i<M;i++)
                ary_m[i] = sc.nextInt();

            int sum = 0, max_val = -2147483648;
            if(N>M) {
                for(int i=0;i<=(N-M);i++) {
                    sum = 0;
                    for(int j=0;j<M;j++) {
                        sum += (ary_n[i+j]*ary_m[j]);
                    }
                    max_val = max_val < sum? sum:max_val;
                }
            }
            else {
                for(int i=0;i<=(M-N);i++) {
                    sum = 0;
                    for(int j=0;j<N;j++) {
                        sum += (ary_n[j]*ary_m[i+j]);
                    }
                    max_val = max_val < sum? sum:max_val;
                }
            }

            System.out.println("#"+test_case+" "+max_val);
        }
	}
}