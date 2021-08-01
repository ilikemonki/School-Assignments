// Meng Cha
// CECS 328
//Project 1: Lexicographic Order Using Merge Sort
// 3/2/18

package Project1;

import java.util.*;
public class MergeSorterTester {

    public static void main(String[] args)
    {
        String[] arr = {"if", "music", "be", "the", "food", "of", "love", "play", "on", "william", "shakespeare"};

        MergeSorter ob = new MergeSorter();

        System.out.println("Before merge sort:");
        ob.printArray(arr);
        ob.mergeSort(arr, arr.length);

        System.out.println("After merge sort:");
        ob.printArray(arr);
    }
}
