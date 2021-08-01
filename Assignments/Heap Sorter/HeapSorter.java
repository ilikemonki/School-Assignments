//Meng Cha
//CECS 328
//Project 2 Heap Sort
//4/2/2018

public class HeapSorter<T extends Comparable<T>> {

    private int heapsize;   //size of array
    //Get parent node
    private int parent(int i) {
        return i - 1 / 2;
    }
    //Get left child
    private int leftChild(int i) {
        return 2 * i ;
    }
    //Get right child
    private int rightChild(int i) {
        return 2 * i + 1;
    }

    //Build heap
    private void buildMaxHeap(T[ ] array) {
        int n = array.length;
        heapsize = array.length - 1;    //initialize size
        for (int i = parent(n); i >= 0; i--)
            maxHeapify(array, i);
    }
    //Maintain max heap property
    private void maxHeapify(T[ ] array, int i) {
        int largest = i;  //Initialize largest to root
        int l = leftChild(i);   //Initialize l
        int r = rightChild(i);  //Initialize r

        // Checks if left is larger than root
        if (l <= heapsize && array[l].compareTo(array[largest]) > 0)
            largest = l;

        // Checks if right is larger than the largest
        if (r <= heapsize && array[r].compareTo(array[largest]) > 0)
            largest = r;

        // Checks to see if largest is not root
        if (largest != i)
        {
            //swap
            T temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;

            // Recursion
            maxHeapify(array, largest);
        }
    }
    //Heap sort
    public void heapsort(T[ ] array) {
        int n = array.length;
        buildMaxHeap(array);    //builds a heap;
        for (int i=n-1; i>=0; i--)
        {
            // Move the root to end of array
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapsize--; //lower the array size;
            maxHeapify(array, 0);   //to reduce heap, pass in index 0
        }
    }
}
