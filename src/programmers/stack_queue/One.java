package programmers.stack_queue;

import java.util.Stack;

public class One {

    public static void main(String[] args) {
        System.out.println(solution("()(((()())(())()))(())"));
    }

    private static int solution(String arrangement) {
        int answer = 0;
        Stack<Boolean> stack = new Stack<>();

        for(int i=0; i<arrangement.length(); i++) {
            switch (arrangement.charAt(i)) {
                case '(':
                    stack.push(true);
                    if(arrangement.charAt(i+1) != ')') {
                        answer ++;
                    }
                    break;
                case ')':
                    stack.pop();
                    if(arrangement.charAt(i-1) == '(') {
                        answer += stack.size();
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        return answer;
    }
}
