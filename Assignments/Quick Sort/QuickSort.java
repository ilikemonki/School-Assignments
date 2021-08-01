//Meng Cha
//CECS 277
//Quick Sort
//4/12/2018

public class QuickSort {

    char arr[];     //Character array
    //sort array
    public void sort(char arr[], int low, int high) {
        if(low < high) {
            int pIndex = quicksort(arr, low, high); //Get the partition index

            //Seperate array and sort them
            sort(arr, low, pIndex - 1);
            sort(arr, pIndex + 1, high);
        }
    }

    //Compare and sorts char array
    private int quicksort(char arr[], int low, int high) {
        int pivot = arr[high];  //Set pivot
        int i = (low - 1);  //set index

        for (int j = low; j <= high - 1; j++)
        {
            if (arr[j] <= pivot)    //Check if array index is less/equal to the pivot
            {
                i++;    //Increment index
                swap(arr,i, j);     //Swap the char indexes
            }
        }
        swap(arr,i+1, high);    //swap
        return (i + 1); //return index
    }

    //Swap character array
    private void swap(char arr[], int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
