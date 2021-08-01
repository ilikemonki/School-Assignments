// Meng Cha
// CECS 328
//Project 1: Lexicographic Order Using Merge Sort
// 3/2/18

package Project1;

public class MergeSorter {

    public MergeSorter() {

    }

    void mergeSort(String[] arrList, int length)
    {
        if (arrList.length > 1) {
            // Initialize var that splits the array in half
            int n1 = length / 2;
            int n2 = length - n1;

            // Create two temp arrays
            String[] leftArray = new String[n1];
            String[] rightArray = new String[n2];

            // Copy data into the temp arrays
            for (int i = 0; i < n1; i++) {
                leftArray[i] = arrList[i];
            }
            for (int j = 0; j < n2; j++) {
                rightArray[j] = arrList[j + n1];
            }

            /* Call itself using temp array for parameter until
            array is less than 1 */
            mergeSort(leftArray, leftArray.length);
            mergeSort(rightArray, rightArray.length);
            merge(arrList, leftArray, rightArray);
        }

    }


    //merge the temp arrays back into array
    void merge(String[] arrList, String[] leftArray, String[] rightArray)
    {
        //declare and initialize place holders
        int L = 0, R = 0;

        for (int k = 0; k < arrList.length; k++)
        {
            if (R >= rightArray.length || (L < leftArray.length &&
                    leftArray[L].compareTo(rightArray[R]) < 0))
            {
                arrList[k] = leftArray[L];
                L++;
            }
            else
            {
                arrList[k] = rightArray[R];
                R++;
            }
        }

    }

    //prints array
    void printArray(String[] arrList)
    {
        for (String print : arrList)
        {
            System.out.println(print);
        }
        System.out.println();
    }

}
