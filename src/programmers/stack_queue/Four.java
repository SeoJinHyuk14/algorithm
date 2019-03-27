package programmers.stack_queue;

import java.util.ArrayList;
import java.util.Arrays;

public class Four {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[] {93,30,55}, new int[] {1, 30, 5})));
    }

    private static int[] solution(int[] progresses, int[] speeds) {
        final int LEN = progresses.length;
        ArrayList<Integer> list = new ArrayList<>();
        int current = 0;

        do {
            boolean first = true;
            int cnt = 0;

            for(int i=current; i<LEN; i++) {
                if(progresses[i] < 100) {
                    progresses[i] += speeds[i];
                }
                if(first && progresses[i] >= 100) {
                    cnt++;
                } else if(first && progresses[i] < 100) {
                    first = false;
                }
            }

            if(cnt > 0) {
                list.add(cnt);
                current += cnt;
            }
        } while (current < LEN);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
