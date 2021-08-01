/* Meng Cha
   CECS 229
   Euclidean/Bezout Algorithm
   10/11/2018
 */

import java.util.Scanner;

//This class uses the Euclidean Algorithm to get the gcd of two numbers.
//Also uses the Bezout Algorithm to get the coefficients.
public class EuclideanBezout {

    public static void main(String[] args) {
        long p, q;  //variables for user input.
        Scanner scan = new Scanner(System.in);

        //Ask for user input.
        System.out.println("gcd(p,q)");
        System.out.print("Enter p: ");
        p = scan.nextLong();    //parse user's input
        System.out.print("Enter q: ");
        q = scan.nextLong();

        long result = Euclidean(p, q);  //call Euclidean function and pass in the inputs.
        System.out.println("gcd(" + p + "," + q + ")" + " = " +result); //output the result.

        long bezoutResult[] = Bezout(p, q); //calls Bezout function and puts the coefficients into an array.
        System.out.println("Bezout's coefficient: " + bezoutResult[1] +
                " and " + bezoutResult[0]);     //outputs the result.
        System.out.println(bezoutResult[0] + " is the inverse of " + p +
                " modulo " + q);    //outputs the inverse.
    }

    //Euclidean Algorithm
    static long Euclidean(long p, long q) {
        while (q != 0) {    //loops until q is zero (when the remainder is zero).
            long r = p % q; // get the remainder of p and q
            p = q;  //set p with the value of q
            q = r;  //set q with the remainder.
        }
        return p;
    }

    //Bezout Algorithm
    static long[] Bezout(long p, long q) {      //returns long array b/c we want the two coefficients
        long x = 0, y = 1;  // there exist integer such that gcd(a,b) = xa +yb
        long lastx = 1, lasty = 0;  //get the last x and y
        long temp;  //temperary variable to set lastx/y to temp

        while (q != 0)  //loop until remainder is zero.
        {
            long s = p / q; //varible use for multiplying it with x and y.
            long r = p % q; //remainder

            p = q;  //set p with q
            q = r;  //set q with remainder

            temp = x;   //set temp to current x
            x = lastx - s * x;  //get new x
            lastx = temp;   //set lastx to old x

            temp = y;   //set temp to current y
            y = lasty - s * y;  //get new y
            lasty = temp;   //set lasty to old y
        }
        return new long[] {lastx, lasty};   //returns the two coefficients.
    }
}
