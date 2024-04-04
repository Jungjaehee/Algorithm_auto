import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int[][] arr = new int[9][9];
            int[] width = new int[9];
            int[] height = new int[9];
            int[] box = new int[9];

            int res = 1;

            int[] correct = {1, 2, 3, 4, 5, 6, 7, 8, 9};

            for(int i=0;i<9;i++) {
                for(int j=0;j<9;j++)
                    arr[i][j] = sc.nextInt();
            }


            for(int i=0;i<9;i++) {
                for(int j=0;j<9;j++) {
                    width[j] = arr[i][j];
                    height[j] = arr[j][i];
                    int x = ((int)(i/3))*3+(int)j/3;
                    int y = (i%3)*3+j%3;
                    box[j] = arr[x][y];
                }

                Arrays.sort(width);
                Arrays.sort(height);
                Arrays.sort(box);

                if(!Arrays.equals(box, correct) || !Arrays.equals(width, correct) || !Arrays.equals(height, correct)) {
                    res = 0;
                    break;
                }
            }

            System.out.println("#" + test_case + " " + res);
        }
	}
}