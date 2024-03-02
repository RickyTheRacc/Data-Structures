package misc;

import java.util.Arrays;

public class COpe {
    public static void main(String[] args) {
        // Find first 6 numbers in the sequence where a(n) = 3*a(n-1)*a(n-1)
        int[] a = {1, 0, 0, 0, 0, 0, 0};
        for (int i = 1; i < 7; i++) {
            a[i] = 3 * a[i-1] * a[i-1];
        }

        System.out.println(Arrays.toString(a));

        int[] b = {1, 1, 2, 0, 0, 0, 0, 0, 0};
        for (int i = 3; i < 9; i++) {
            b[i] = b[i-1] - b[i-2] + b[i-3];
        }

        System.out.println(Arrays.toString(b));
    }

    public static int mystery(int x, int n) {
        int sum = 0;

        if (x <= 1) return 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum += x / 2;
            }
        }

        return sum;
    }
}
