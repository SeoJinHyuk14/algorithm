package programmers.hash;

import java.util.ArrayList;
import java.util.HashMap;

public class Three {
    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
    }

    private static int solution(String[][] clothes) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String[] clothe : clothes) {
            if(map.containsKey(clothe[1])) {
                map.get(clothe[1]).add(clothe[0]);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(clothe[0]);
                map.put(clothe[1], list);
            }
        }

        int total = 1;
        for(String key : map.keySet()) {
            total *= map.get(key).size() + 1;   // 옷 안 입는 경우도 있어서 + 1
        }

        return total - 1;   // 아무것도 안 입는 경우 - 1
    }
}
