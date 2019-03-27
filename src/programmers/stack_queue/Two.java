package programmers.stack_queue;

import java.util.*;
import java.util.stream.Collectors;

public class Two {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    private static int solution(int[] priorities, int location) {
        int answer = 0;
        LinkedList<Integer> queue = Arrays.stream(priorities).boxed().collect(Collectors.toCollection(LinkedList::new));

        while(true) {
            int maxIndex = queue.indexOf(Collections.max(queue));

            for(int i=0; i<maxIndex; i++) {
                queue.offer(queue.remove());
            }

            location = location >= maxIndex ? location - maxIndex : location + queue.size() - maxIndex;
            answer ++;

            if(location == 0) {
                break;
            }

            queue.remove();
            location --;
        }
        return answer;
    }
}
