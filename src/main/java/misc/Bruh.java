package misc;

import java.util.HashMap;
import java.util.Map;

public class Bruh {
    public static void main(String[] args) {
        System.out.println(mystery(0));
        System.out.println(mystery(5));
        System.out.println(mystery(13));
        System.out.println(mystery(297));
    }

    public static Map<Integer, Integer> unify(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        Map<Integer, Integer> map3 = new HashMap<>(map1);

        map2.forEach((n1, n2) -> {
            int nya = map3.getOrDefault(n1, 0);
            map3.put(n1, n2 + nya);
        });

        return map3;
    }

    public static int mystery (int n) {
        if (n < 0) {
            return -mystery(-n);
        } else if (n == 0) {
            return 0;
        } else {
            return mystery(n / 10) * 10 + 9 - (n % 10);
        }
    }
}
