// Meng Cha
// CECS 328
// Memoized Cut Rod
// 5/4/2018

public class MemoizedCutRod {

    //print lengths
    public void printLengths() {
        System.out.print(length1 + " + " + length2);
    }

    int length1 = 0, length2 = 0;

    //create array to store solutions and set them < 0
    public int Memoized_Cut_Rod(int p[], int n) {
        int r[] = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            r[i] = Integer.MIN_VALUE;
        }
        return Memoized_Cut_Rod_Aux(p, n, r);   //call method and return value
    }

    //return max value for length n
    public int Memoized_Cut_Rod_Aux(int p[], int n, int r[]) {
        int q;
        if (r[n] >= 0) {
            return r[n];
        }
        if (n == 0) {
            q = 0;
        }
        else {
            q = Integer.MIN_VALUE;
            for (int i = 1; i < n; i++) {
                //set lengths if max value
                if(q <= (p[i] + Memoized_Cut_Rod_Aux(p, n - i - 1, r))) {
                    length1 = (i + 1);
                    length2 = (n-i-1);
                }
                //get the max value
                q = Math.max(q, (p[i] + Memoized_Cut_Rod_Aux(p, n - i - 1, r)));
            }
        }
        r[n] = q;   //store solution in array r
        return q;
    }

}
