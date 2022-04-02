package data_structure.tree;

import algorithm.SortAlgorithm.HeapSort;

import java.util.Arrays;

public class TestForHeap {
    public static void main(String[] args) {
//        Heap integerHeap = new Heap(99);
//        integerHeap.insert(4);
//        integerHeap.insert(7);
//        integerHeap.insert(6);
//        integerHeap.insert(3);
//        integerHeap.insert(2);
//        integerHeap.insert(5);
//        integerHeap.insert(4);
//        integerHeap.insert(0);
//
//
//        integerHeap.sink(1);
//        integerHeap.display();
        int[] nums = {1,2,32,4,3,54,90};
        HeapSort.heapSorting(nums);
//        HeapSort.sink(nums,2, nums.length-1);
//        System.out.println(Arrays.toString(nums));
//        HeapSort.sink(nums,1, nums.length-1);
//        System.out.println(Arrays.toString(nums));
//        HeapSort.sink(nums,0, nums.length-1);
        System.out.println(Arrays.toString(nums));


    }
}
