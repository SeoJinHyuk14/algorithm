package programmers.stack_queue;

import java.util.Arrays;
import java.util.Stack;

public class Six {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[] {1, 2, 3, 2, 3})));
    }

    private static int[] solution(int[] prices) {
        final int LEN = prices.length;
        int[] answer = new int[LEN];
        Stack<int[]> stack = new Stack<>();

        for(int i=0; i<LEN; i++) {
            if(stack.empty()) {
                stack.push(new int[] {prices[i], i});
            } else if(stack.peek()[0] > prices[i]) {    // 하한가
                int[] element = stack.pop();
                answer[element[1]] = i - element[1];
                i--;
            } else {            // 상한가
                stack.push(new int[] {prices[i], i});
            }
        }

        for(int[] element : stack) {    //스택에 남아 있는것
            answer[element[1]] = LEN - element[1] - 1;
        }

        return answer;
    }
}
