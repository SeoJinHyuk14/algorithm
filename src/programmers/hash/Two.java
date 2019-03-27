package programmers.hash;

import java.util.ArrayList;

public class Two {

    public static void main(String[] args) {
        System.out.println(solution(new String[] {"119", "97674223", "1195524421"}));
    }

    private static boolean solution(String[] phone_book) {
        ArrayList<String> list = new ArrayList<>();

        for(String phone : phone_book) {
            for(String key : list) {
                if(key.startsWith(phone) || phone.startsWith(key)) {
                    return false;
                }
            }
            list.add(phone);
        }

        return true;
    }
}
