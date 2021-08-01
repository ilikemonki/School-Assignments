//Meng Cha
//CECS 328
//Project 2 Heap Sort
//4/2/2018

public class HeapSorterTester {

    public static void main(String[] args) {
        HeapSorter t = new HeapSorter();    //Create object

        //Test int array in Heap Sort
        Integer[] test1 = {9, 7, 8, 3, 4, 6, 5, 1, 2, 3};
        t.heapsort(test1);

        for (int T1 : test1)
            System.out.print(T1 + " ");
        System.out.println();

        //Test char array in Heap Sort
        Character[] test2 = {'J', 'I', 'G', 'H', 'D', 'C', 'E', 'B', 'F', 'A'};
        t.heapsort(test2);

        for (Character T2 : test2)
            System.out.print(T2 + " ");
        System.out.println();

        //Test string array in Heap Sort
        String[] test3 = {"banana", "dolphin", "gravity", "apple", "book", "phone"};
        t.heapsort(test3);

        for (String T3 : test3)
            System.out.print(T3 + " ");
        System.out.println();

        //Test double array in Heap Sort
        Double[] test4 = {4.5, 5.33, 5.9, 3.09, 8.88, 4.333, 4.332};
        t.heapsort(test4);

        for (Double T4 : test4)
            System.out.print(T4 + " ");
        System.out.println();
    }
}
