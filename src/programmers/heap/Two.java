package programmers.heap;

import java.util.PriorityQueue;

public class Two {

    public static void main(String[] args) {
        System.out.println(solution(4, new int[] {4, 10, 15}, new int[] {20, 5, 10}, 30));
    }

    private static int solution(int stock, int[] dates, int[] supplies, int k) {
        final int LEN = dates.length;
        int answer = 0, days = 0, dateIndex = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((i1, i2) -> i1 <= i2 ? 1 : -1);

        while (true) {
            k -= stock;
            days += stock;

            if(k > 0) {
                for(int i=dateIndex; i<LEN; i++) {
                    if(dates[i] <= days) {
                        priorityQueue.offer(supplies[i]);
                        if(i == LEN - 1) {
                            dateIndex = i + 1;
                        }
                    } else {
                        dateIndex = i;
                        break;
                    }
                }
                if(priorityQueue.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                stock = priorityQueue.poll();
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }
}
