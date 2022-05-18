package com.rank.random;

import java.util.Random;

/**
 * @author Caolp
 */
public class RandomTest {
    private static final long mask = (1L << 48) - 1;

    public static void main(String[] args) {

        Random random = new Random();
        int i = random.nextInt(65) + 10;
        System.out.println(mask);
        System.out.println(i);
    }


}
