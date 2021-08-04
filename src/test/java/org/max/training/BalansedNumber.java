package org.max.training;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Scanner;

public class BalansedNumber {
    public static void main(String[] args) {


//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Input number>99");
//        int b = scanner.nextInt();
        //balansedNumber(b);
        BalancedNumber("12412");
    }

    public static void balansedNumber(int b) {
        int sum1 = 0;
        int sum = 0;
        String c = b + "";
        char[] v = c.toCharArray();
        int[] m = new int[v.length];
        for (int i = 0; i < v.length; i++) {
            m[i] = v[i];

        }

        if ((v.length % 2) == 0) {
            int[] g = new int[(v.length / 2) - 1];
            int[] k = new int[(v.length / 2) - 1];
            for (int i = 0, j = 0; i < v.length; i++) {
                if (i < (v.length / 2) - 1)
                    g[i] = m[i];
                else if (i > (v.length / 2)) {
                    k[j] = m[i];
                    j++;
                }
            }
            for (int value : g) {
                sum = sum + value;
            }
            for (int j : k) {
                sum1 = sum1 + j;
            }

            if (sum == sum1) {
                System.out.println("Number is balansed");
            } else {
                System.out.println("Number is not balansed");
            };
        } else {
            int[] t = new int[(v.length - 1) / 2];
            int[] y = new int[(v.length - 1) / 2];
            for (int i = 0, j = 0; i < v.length; i++) {
                if (i < (v.length - 1) / 2)
                    t[i] = m[i];
                else if (i > (v.length - 1) / 2) {
                    y[j] = m[i];
                    j++;
                }
            }
            for (int j : t) {
                sum = sum + j;
            }
            for (int j : y) {
                sum1 = sum1 + j;
            }

            if (sum == sum1) {
                System.out.println("Number is balansed");
            } else {
                System.out.println("Number is not balansed");
            }
            ;
        }
        ;
    }
    private static void BalancedNumber(String s)
    {
    int Leftsum = 0;
    int Rightsum = 0;

    // Calculating the Leftsum
    // and rightSum simultaneously
    for(int i = 0; i < s.length() / 2; i++)
    {

        // Typecasting each character
        // to integer and adding the
        // digit to respective sums
        Leftsum += (int)(s.charAt(i) - '0');
        Rightsum += (int)(s.charAt(
                s.length() - 1 - i) - '0');
    }

    if (Leftsum == Rightsum)
            System.out.println("Balanced");
    else
            System.out.println("Not Balanced");
}
}
