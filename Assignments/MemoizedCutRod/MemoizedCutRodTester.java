// Meng Cha
// CECS 328
// Memoized Cut Rod
// 5/4/2018

public class MemoizedCutRodTester {


    public static void main(String[] args)
    {
        int[] p = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30}; //array of prices
        int n =8;   //length of rod

        MemoizedCutRod mcr = new MemoizedCutRod();
        int max = mcr.Memoized_Cut_Rod(p, n); //max value
        System.out.println("Max value for length " + n + " is " + max);
        System.out.print("The lengths of each piece in the rod used to obtain that max value: ");
        mcr.printLengths();   //print lengths
    }
}
