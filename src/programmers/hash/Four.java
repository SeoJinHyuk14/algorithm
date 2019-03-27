package programmers.hash;

import java.util.*;
import java.util.stream.Collectors;

public class Four {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[] {"classic", "pop", "classic", "classic", "pop"}, new int[] {500, 600, 15000, 800, 2500})));
    }

    private static int[] solution(String[] genres, int[] plays) {
        int size = genres.length;
        final int COUNT = 2;

        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for(int i=0; i<size; i++) {
            if(map.containsKey(genres[i])) {
                map.get(genres[i]).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(genres[i], list);
            }
        }

        LinkedHashMap<String, ArrayList<Integer>> map2 = map.entrySet().stream().sorted((o1, o2) -> {
            Integer o1_sum = o1.getValue().stream().mapToInt(v1 -> plays[v1]).sum();
            Integer o2_sum = o2.getValue().stream().mapToInt(v2 -> plays[v2]).sum();
            return o2_sum.compareTo(o1_sum);
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));

        ArrayList<Integer> result = new ArrayList<>();
        for(String key : map2.keySet()) {
            ArrayList<Integer> data = map2.get(key);
            if(data.size() >= 2) {
                for(int i=0; i<COUNT; i++) {
                    int maxIndex = 0;
                    for(int j=1; j<data.size(); j++) {
                        maxIndex = plays[data.get(maxIndex)] < plays[data.get(j)] ? j : maxIndex;
                    }
                    result.add(data.get(maxIndex));
                    data.remove(maxIndex);
                }
            } else {    // 장르에 속한 곡이 한개
                result.add(data.get(0));
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
