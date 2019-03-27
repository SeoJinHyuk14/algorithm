package programmers.hash;

import java.util.HashMap;

public class One {
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"leo", "kiki", "eden"}, new String[] {"kiki", "eden"}));
        System.out.println(solution(new String[] {"leo", "kiki", "eden", "kiki"}, new String[] {"leo", "kiki", "eden"}));
    }

    private static String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String people : participant) {
            map.put(people, map.getOrDefault(people, 0) + 1);
//            if(map.containsKey(people)) {
//                map.put(people, map.get(people) + 1);
//            } else {
//                map.put(people, 1);
//            }
//            map.put(people, map.containsKey(people) ? map.get(people) + 1 : 1);
        }

        for (String people : completion) {
            map.put(people, map.get(people) - 1);
        }

        for (String key : map.keySet()) {
            if(map.get(key) > 0) {
                return key;
            }
        }

        return "error";
    }
}
