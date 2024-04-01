package misc;

import java.util.*;

@SuppressWarnings("unused")
public class Review2 {
    public static void removeEvenLength(Set<String> input) {
        input.removeIf(s -> s.toCharArray().length % 2 == 0);
    }

    public int removeMin(Stack<Integer> stack) {
        int smallest = Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();

        while (stack.isEmpty()) {
            int temp = stack.pop();
            if (temp < smallest) smallest = temp;
            queue.add(temp);
        }

        while (!queue.isEmpty()) {
            int temp = queue.remove();
            if (temp > smallest) queue.add(temp);
        }

        return smallest;
    }

    static class EvenIterator implements Iterator<Integer> {
        private int current;

        public EvenIterator() {
            this.current = 0;
        }

        public boolean hasNext() {
            return true;
        }

        public Integer next() {
            return current += 2;
        }
    }

//    public boolean equals(Object thing) {
//        if (!(thing instanceof Point point)) return false;
//        return this.x - this.y == point.getX() - point.getY();
//    }

    public static int countInArea(Map<String, String> map, String code) {
        Set<String> set = new HashSet<>();

        map.values().forEach(s -> {
            if (!s.startsWith(code)) return;
            set.add(s);
        });

        return set.size();
    }

    public static void main(String[] args) {
        Map<String, String> map = Map.of(
            "Guh", "3199361987",
            "Gorp", "3195910384",
            "Gree", "3195910384",
            ":3", "123456789"
        );

        System.out.println(countInArea(map, "319"));
    }
}
