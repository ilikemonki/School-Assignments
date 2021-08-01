//Meng Cha
//CECS 277
//Quick Sort
//4/12/2018

import java.util.Scanner;
public class QuickSortTester {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the word to be quick sorted: ");
        String word = input.nextLine();     //Get input

        char arr[] = word.toCharArray();    //Convert string into char array

        QuickSort qs = new QuickSort();     //Create Quicksort object
        qs.sort(arr, 0, word.length() - 1); //Call quicksort
        for (char c : arr)      //Output
            System.out.print(c);
    }
}
