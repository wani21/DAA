package DivideNConquer;

import java.math.BigInteger;

public class KaratsubaMultiplication {

    /**
     * Karatsuba multiplication for large integers represented as BigInteger.
     * Time complexity: O(n^1.585) where n is the number of digits.
     */
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        // Base case: use standard multiplication for small numbers
        int n = Math.max(x.bitLength(), y.bitLength());
        if (n <= 64) {
            return x.multiply(y);
        }

        // Split size: half the number of bits
        int half = n / 2;

        // Split x into x1 (high) and x0 (low)
        BigInteger x0 = x.and(BigInteger.ONE.shiftLeft(half).subtract(BigInteger.ONE)); // x mod 2^half
        BigInteger x1 = x.shiftRight(half);                                              // x / 2^half

        // Split y into y1 (high) and y0 (low)
        BigInteger y0 = y.and(BigInteger.ONE.shiftLeft(half).subtract(BigInteger.ONE)); // y mod 2^half
        BigInteger y1 = y.shiftRight(half);                                              // y / 2^half

        // 3 recursive multiplications (instead of 4 in naive approach)
        BigInteger z0 = karatsuba(x0, y0);              // low * low
        BigInteger z2 = karatsuba(x1, y1);              // high * high
        BigInteger z1 = karatsuba(x0.add(x1), y0.add(y1)); // (low+high) * (low+high)

        // Karatsuba's trick: middle term = z1 - z2 - z0
        BigInteger middle = z1.subtract(z2).subtract(z0);

        // Combine: result = z2 * 2^(2*half) + middle * 2^half + z0
        return z2.shiftLeft(2 * half)
                .add(middle.shiftLeft(half))
                .add(z0);
    }

    /**
     * Karatsuba for plain long integers (simpler version for smaller numbers)
     */
    public static long karatsubaLong(long x, long y) {
        // Base case
        if (x < 10 || y < 10) {
            return x * y;
        }

        // Determine the size of the numbers
        int n = Math.max(Long.toString(Math.abs(x)).length(),
                Long.toString(Math.abs(y)).length());
        long m = (long) Math.pow(10, n / 2);

        // Split x and y
        long x0 = x % m;   // low part of x
        long x1 = x / m;   // high part of x
        long y0 = y % m;   // low part of y
        long y1 = y / m;   // high part of y

        // 3 recursive multiplications
        long z0 = karatsubaLong(x0, y0);
        long z2 = karatsubaLong(x1, y1);
        long z1 = karatsubaLong(x0 + x1, y0 + y1);

        // Combine
        return z2 * m * m + (z1 - z2 - z0) * m + z0;
    }

    public static void main(String[] args) {
        // --- Test with BigInteger (handles truly large numbers) ---
        BigInteger a = new BigInteger("12345678901234567890");
        BigInteger b = new BigInteger("98765432109876543210");

        BigInteger karatsubaResult = karatsuba(a, b);
        BigInteger javaResult = a.multiply(b);  // Built-in for verification

        System.out.println("=== BigInteger Karatsuba ===");
        System.out.println("A         : " + a);
        System.out.println("B         : " + b);
        System.out.println("Karatsuba : " + karatsubaResult);
        System.out.println("Java      : " + javaResult);
        System.out.println("Match     : " + karatsubaResult.equals(javaResult));

        // --- Test with long ---
        long x = 1234567L;
        long y = 9876543L;
        System.out.println("\n=== Long Karatsuba ===");
        System.out.println("X         : " + x);
        System.out.println("Y         : " + y);
        System.out.println("Karatsuba : " + karatsubaLong(x, y));
        System.out.println("Standard  : " + (x * y));

        // --- Performance comparison ---
        System.out.println("\n=== Performance Test ===");
        BigInteger big1 = new BigInteger("2").pow(1024);
        BigInteger big2 = new BigInteger("2").pow(1024).subtract(BigInteger.ONE);

        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) karatsuba(big1, big2);
        long karatsubaTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) big1.multiply(big2);
        long javaTime = System.nanoTime() - start;

        System.out.printf("Karatsuba time : %,d ns%n", karatsubaTime);
        System.out.printf("Java built-in  : %,d ns%n", javaTime);
    }
}
