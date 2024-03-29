package misc;

import java.util.*;

@SuppressWarnings("unused")
public class Gorp {
    public static void main(String[] args) {
//        EvenIterator iterator = new EvenIterator();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(iterator.next());
//        }
//
//        Set<String> set = new HashSet<>();
//        set.add("bruh");
//        set.add("three");
//        set.add("idkmanlol");
//        set.removeIf(s -> s.toCharArray().length % 2 == 0);
//        System.out.println(set);

        Map<String, String> test = Map.of(
            "Guh", "3199361987",
            "Gorp", "3199361987",
            "Me", "3195910384"
        );

        System.out.println(countInAreaCode(test, "319"));
    }

    public static int countInAreaCode(Map<String, String> map, String code) {
        Set<String> set = new HashSet<>();

        map.values().forEach(s -> {
            if (!s.startsWith(code)) return;
            set.add(s);
        });

        return set.size();
    }


















    public static class IntegerStringPair {
        Integer first;
        String second;

        public IntegerStringPair(Integer first, String second) {
            this.first = first;
            this.second = second;
        }

        public Integer getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }
    }

    public static class ObjectPair {
        Object first;
        Object second;

        public ObjectPair(Object a, Object b) {
            first = a;
            second = b;
        }

        public Object getFirst() {
            return first;
        }

        public Object getSecond() {
            return second;
        }
    }

    public static class Pair<A, B> {
        A first;
        B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }
    }

//    // 1
//    IntegerStringPair x1 = new IntegerStringPair(4, "dog");
//    Integer left1 = x1.getFirst();
//    String right1 = x1.getSecond();
//
//    // 2
//    ObjectPair x2 = new ObjectPair(4, "dog");
//    Integer left2 = x2.getFirst();
//    String right2 = x2.getSecond();
//
//    // 3
//    ObjectPair x3 = new ObjectPair(4, "dog");
//    Integer left3 = (Integer) x3.getFirst();
//    String right3 = (String) x3.getSecond();
//
//    // 4
//    ObjectPair x4 = new ObjectPair(4, "dog");
//    String left4 = (String) x4.getFirst();
//    Integer right4 = (Integer) x4.getSecond();
//
//    // 5
//    Pair<Integer,String> x5 = new Pair<>(4, "dog");
//    Integer left5 = x5.getFirst();
//    String right5 = x5.getSecond();
}
