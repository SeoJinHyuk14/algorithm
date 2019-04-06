package programmers.heap;

import java.util.PriorityQueue;

public class One {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 2, 3, 9, 10, 12}, 7));
    }

    private static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int sco : scoville) {
            priorityQueue.add(sco);
        }

        while (priorityQueue.size() > 0 && priorityQueue.peek() < K) {
            if(priorityQueue.size() < 2) {
                return -1;
            }
            priorityQueue.add(priorityQueue.poll() + priorityQueue.poll() * 2);

            answer++;
        }
        return answer;
    }
}
