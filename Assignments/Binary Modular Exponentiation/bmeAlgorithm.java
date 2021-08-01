// Meng Cha
// CECS 229
// Binary Modular Exponentiation Algorithm
// 9/18/2018

import java.util.Scanner;
public class bmeAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long b, n, m;   //base, exponent, modulus

        System.out.println("b^n mod m");
        System.out.print("Enter for b: ");
        b = sc.nextLong();  //get b input

        System.out.print("Enter for n: ");
        n = sc.nextLong();  //get n input

        System.out.print("Enter for m: ");
        m = sc.nextLong();  //get m input

        //Calls bme function. Outputs the whole equation and answer.
        System.out.println(b + "^" + n + " mod " + m + " = " + bme(b, n, m));
    }

    //algorithm of Binary Modular Exponentiation.
    static long bme(long b, long n, long m) {
        long result = 1;    //set result to one.

        if(m == 1) return 0;    //return 0 if mod 1.
        b = b % m;  //set base to the remainder of b mod m.
        System.out.println("Steps:");

        while (n > 0) {     //loop until exponent is zero.

            if ((n & 1) == 1) { //check if exponent is odd, update result.
                result = (result * b) % m; //multiply result and base and mod it with m
            }
            b = (b * b) % m; //square the base
            n = n>>1;  //divide exponent in half, shifts one bit to the left.

            //Prints out the variables throughout each loop.
            System.out.printf("result: %-10d", result);
            System.out.printf("n: %-10d", n);
            System.out.printf("b: %-10d \n", b);
        }
        return result;
    }
}
