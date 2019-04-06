package programmers.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Three {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{0, 3}, {1, 9}, {2, 6}}));
    }

    private static int solution(int[][] jobs) {
        int answer = 0, currentTime =0, index = 0;
        final int LEN = jobs.length;


        jobs = Arrays.stream(jobs).sorted((i1, i2) -> i1[0] <= i2[0] ? -1 : 1).toArray(int[][]::new);
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((i1, i2) -> i1[1] <= i2[1] ? -1 : 1);

        do {
            for(int i=index; i<LEN; i++) {
                if(currentTime >= jobs[i][0]) {
                    priorityQueue.offer(jobs[i]);
                    if(i == LEN - 1) {
                        index = i + 1;
                    }
                } else {
                    index = i;
                    break;
                }
            }

            if(priorityQueue.isEmpty()) {
                int[] tempJob = jobs[index++];
                priorityQueue.offer(tempJob);
                currentTime += tempJob[0] - currentTime ;
            }

            int[] job = priorityQueue.poll();
            currentTime += job[1];
            answer += currentTime - job[0];
        } while(index < LEN || !priorityQueue.isEmpty());

        return answer / LEN;
    }
}
