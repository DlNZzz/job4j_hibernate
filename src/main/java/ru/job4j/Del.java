package ru.job4j;

import java.nio.charset.StandardCharsets;

public class Del {
    public static int minPartitions(String n) {
        char[] chars = n.toCharArray();
        int count = 0;
        for (char c : chars) {
            int num = Integer.parseInt(c + "");
            if (count < num) {
                count = num;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        byte[] bytes = "123".getBytes();
        for (byte b : bytes) {
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        }
    }
}
