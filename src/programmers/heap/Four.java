package programmers.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Four {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"I 16", "D 1"})));
        System.out.println(Arrays.toString(solution(new String[]{"I 7", "I 5", "I -5", "D -1"})));
    }

    private static int[] solution(String[] operations) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        int insertCnt = 0;
        for(String operation : operations) {
            switch (operation.charAt(0)) {
                case 'I':
                    int parse = Integer.parseInt(operation.substring(2));
                    minQueue.offer(parse);
                    maxQueue.offer(parse);
                    insertCnt ++;
                    break;
                case 'D':
                    if(operation.charAt(2) == '-') {
                        minQueue.poll();
                    } else {
                        maxQueue.poll();
                    }

                    if(insertCnt == minQueue.size() + maxQueue.size()) {
                        minQueue.clear();
                        maxQueue.clear();
                        insertCnt = 0;
                    }
                    break;
            }
        }

        return insertCnt == 0 ? new int[] {0, 0} : new int[] {maxQueue.poll(), minQueue.poll()};
    }
}
