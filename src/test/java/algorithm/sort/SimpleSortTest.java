package algorithm.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleSortTest {

    private int[] array = {7, 4, 3, 4, 6, 6, 1, 95, 4, 43, 2, 1};
    private SimpleSort simpleSort;

    @BeforeEach
    void before() {
        simpleSort = new SimpleSort();
    }

    private void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
    }

    @Test
    void hello() {
        System.out.println("hello world");
    }

    @Test
    void testSelectionSort() {
        simpleSort.selectionSort(array);
        printArray(array);
    }

    @Test
    void testInsertionSort() {
        simpleSort.insertionSort(array);
        printArray(array);
    }

    @Test
    void testBubbleSort() {
        simpleSort.bubbleSort(array);
        printArray(array);
    }
}