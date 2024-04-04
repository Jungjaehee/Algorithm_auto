import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int[] boxes;
    static int max_idx;
    static int min_idx;
	public static void main(String args[]) throws Exception
	{
        //System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for(int test_case=1;test_case<=10;test_case++) {
            int cnt = Integer.parseInt(reader.readLine());

            boxes = new int[100];
            String[] boxStr = reader.readLine().split(" ");
            for (int i = 0; i < boxStr.length; i++) {
                boxes[i] = Integer.parseInt(boxStr[i]);
            }
			System.out.print("#" + test_case + " ");
            dump(cnt);
        }

    }
    private static void dump(int cnt) {
        int max = 0;
        max_idx = -1;
        int min = 101;
        min_idx = -1;
        for (int i = 0; i < boxes.length; i++) {
            if(max < boxes[i]) {
                max = boxes[i];
                max_idx = i;
            }
            if(min > boxes[i]) {
                min = boxes[i];
                min_idx = i;
            }
        }
        if(cnt == 0) {
            System.out.println(boxes[max_idx] - boxes[min_idx]);
            return;
        }
        --boxes[max_idx];
        ++boxes[min_idx];
        dump(cnt-1);
    }
}