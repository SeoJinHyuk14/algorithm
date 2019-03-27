package programmers.stack_queue;

import java.util.LinkedList;

public class Three {

    public static void main(String[] args) {
        System.out.println(solution(2, 10, new int[] {7, 4, 5, 6}));
        System.out.println(solution(100, 100, new int[] {10}));
        System.out.println(solution(100, 100, new int[] {10,10,10,10,10,10,10,10,10,10}));
        System.out.println(solution(3, 10, new int[] {7,3,7,3,7,3}));
    }

    private static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0, sumWeight = 0 ;
        LinkedList<Integer> queue = new LinkedList<>();

        for(int i=0; i<truck_weights.length; i++) {
            answer++;
            int peek = queue.isEmpty() || queue.size() < bridge_length ? 0 : queue.peek();
            if(sumWeight - peek + truck_weights[i] > weight) {
                queue.add(0);
                if(queue.size() > bridge_length) {
                    sumWeight -= queue.remove();
                }
                i--;
            } else {
                queue.add(truck_weights[i]);
                sumWeight += truck_weights[i];
                if(queue.size() > bridge_length) {
                    sumWeight -= queue.remove();
                }
            }
        }

        return answer + bridge_length;
    }
}
