package programmers.stack_queue;

import java.util.Arrays;

public class Five {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[] {6,9,5,7,4})));
        System.out.println(Arrays.toString(solution(new int[] {3,9,9,3,5,7,2})));
        System.out.println(Arrays.toString(solution(new int[] {1,5,3,6,7,6,5})));
    }

    private static int[] solution(int[] heights) {
        final int LEN = heights.length;
        int[] answer = new int[LEN];

        for(int i=0; i<LEN; i++) {
            int searchIndex = i - 1;
            while (true) {
                if(searchIndex < 0) {
                    answer[i] = 0;
                    break;
                } else if(heights[searchIndex] > heights[i]) {
                    answer[i] = searchIndex + 1;
                    break;
                } else {
                    searchIndex --;
                }
            }
        }

        return answer;
    }
}
