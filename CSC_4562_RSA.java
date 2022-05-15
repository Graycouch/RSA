package csc_4562_rsa;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSC_4562_RSA {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter A Number To Check If It Is Prime: ");
        int number = Integer.parseInt(input.nextLine());
        System.out.println();
        primeChecker(number);
        System.out.println();

        System.out.print("Enter The First Number In The Range To Find The 10th And 19th Prime Number: ");
        int number1 = Integer.parseInt(input.nextLine());
        System.out.print("Enter The Second Number In The Range To Find The 10th And 19th Prime Number: ");
        int number2 = Integer.parseInt(input.nextLine());
        System.out.println();
        RSA(number1, number2);
    }

    private static void primeChecker(int number) {
        boolean flag = false;
        for (int i = 2; i <= number / 2; ++i) {
            if (number % i == 0) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println(number + " is a prime number");
        } else {
            System.out.println(number + " is not a prime number");
        }
    }

    private static void RSA(int number1, int number2) {
        Map<Character, Integer> hashMap = new HashMap<>();
        createMap(hashMap);

        int e = 1;
        BigInteger d;
        int j = 0;
        int p = 0;
        int q = 0;
        int k = 1;
        int gcdTemp = 0;
        double remainder = 1;
        BigInteger n;   
        BigInteger ciphertextNumber;
        int number = number1;
        boolean primeTenth = false;
        boolean primeNineteenth = false;

        while (number <= number2 && j < 19) {
            boolean flag = false;
            for (int i = 2; i <= number / 2; ++i) {
                if (number % i == 0) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                j++;
            }

            if (j == 10 && primeTenth != true) {
                System.out.printf("The %dth prime number between %d and %d is %d", j, number1, number2, number);
                System.out.println();
                primeTenth = !primeTenth;
                p = number;
            }

            if (j == 19 && primeNineteenth != true) {
                System.out.printf("The %dth prime number between %d and %d is %d", j, number1, number2, number);
                System.out.println();
                primeNineteenth = !primeNineteenth;
                q = number;
            }

            number++;
        }

        n = BigInteger.valueOf(p * q);
        
        while(gcdTemp != 1){
            e++;
            gcdTemp = gcd(e, (p - 1) * (q - 1));
        }
        
        System.out.println();
        System.out.println("e = " + e);
        System.out.println("n = " + n);
        
        BigInteger eBigInt = BigInteger.valueOf(e);
        BigInteger pBigInt = BigInteger.valueOf(p - 1);
        BigInteger qBigInt = BigInteger.valueOf(q - 1);

        d = eBigInt.modInverse((pBigInt.multiply(qBigInt)));

        System.out.println("d = " + d);
        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter The Message That You Want To Encrypt: ");
        String plaintext = input.nextLine();
        String plaintextNumberString = new String();
        System.out.println();

        for (int i = 0; i < plaintext.length(); i++) {
            plaintextNumberString = plaintextNumberString + hashMap.get(plaintext.charAt(i)).toString();
        }
        
        BigInteger plaintextNumber = new BigInteger(plaintextNumberString);

        System.out.println("The integer representation of the plaintext is: " + plaintextNumber);

        ciphertextNumber = plaintextNumber.pow(e).mod(n);
        System.out.println("The integer representation of the ciphertext after encryption is: " + ciphertextNumber);

        int dInt = d.intValue();
        plaintextNumber = ciphertextNumber.pow(dInt).mod(n);
        System.out.println("The integer representation of the plaintext after decryption is: " + plaintextNumber);
    }

    private static void createMap(Map tokens) {
        tokens.put('a', 0);
        tokens.put('b', 1);
        tokens.put('c', 2);
        tokens.put('d', 3);
        tokens.put('e', 4);
        tokens.put('f', 5);
        tokens.put('g', 6);
        tokens.put('h', 7);
        tokens.put('i', 8);
        tokens.put('j', 9);
        tokens.put('k', 10);
        tokens.put('l', 11);
        tokens.put('m', 12);
        tokens.put('n', 13);
        tokens.put('o', 14);
        tokens.put('p', 15);
        tokens.put('q', 16);
        tokens.put('r', 17);
        tokens.put('s', 18);
        tokens.put('t', 19);
        tokens.put('u', 20);
        tokens.put('v', 21);
        tokens.put('w', 22);
        tokens.put('x', 23);
        tokens.put('y', 24);
        tokens.put('z', 25);
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
