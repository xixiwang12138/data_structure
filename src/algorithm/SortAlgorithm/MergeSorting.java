package algorithm.SortAlgorithm;

import java.util.Date;

public class MergeSorting {
    public static void main(String[] args) {
//        int[] nums = {3, 1, 5, 2, 3, 5, 6,435,3,768,7,8,7,32, 78, 234};
//        mergeSorting(nums, 0, nums.length - 1);
//        System.out.println(Arrays.toString(nums));

        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        Date t1 = new Date();
        System.out.println("排序前：" + t1.toString());
        mergeSorting(arr);
        Date t2 = new Date();
        System.out.println("排序后：" + t2.toString());
    }

    public static void mergeSorting(int[] nums) {
        if (nums.length < 2 || nums == null) {
            return;
        }

        process(nums, 0, nums.length - 1);
    }

    public static void process(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        process(nums, start, mid);
        process(nums, mid + 1, end);
        merge(nums, start, mid, end);
        //[start,mid] 和 [mid+1,end] 两个数组均有序，但是合起来不一定
        //merge函数就可以使两个合起来有序
        //由于递归的特性，当分隔到只有两个元素的时候，左右两边均只有一个数，所以分别有界，一定可以合起来

    }

    /**
     * merge函数最主要的作用是归并，即将两个有序的小数组合并成一个大的有序数组
     *
     * @param arr
     * @param low
     * @param middle
     * @param high
     */
    public static void merge(int[] arr, int low, int middle, int high) {
        //用于储存归并后的临时数组
        int[] temp = new int[high - low + 1];
        //记录第一个数组的下标
        int i = low;
        //记录第二个数组的下标
        int j = middle + 1;

        int index = 0;

        while (i <= middle && j <= high) {
            // temp[index++] = arr[i]>=arr[j] ? arr[j++]:arr[i++];
            if (j <= high && arr[i] > arr[j]) {
                temp[index] = arr[j];
                j++;
                index++;
            } else if (i <= middle && arr[i] <= arr[j]) {
                temp[index] = arr[i];
                i++;
                index++;
            }
        }

        while (i <= middle) {
            temp[index] = arr[i];
            i++;
            index++;
        }
        while (j <= high) {
            temp[index] = arr[j];
            j++;
            index++;
        }
        for (int k = 0; k < high - low + 1; k++) {       //k<high-low+1不能使用=>k<arr.length
            //在后续过程中会使用递归操作，只对数组的[low,high]部分进行排序
            arr[low + k] = temp[k];
        }
    }


    //非递归过程的实现
    public static void mergeSorting2(int[] nums) {
        if (nums.length < 2 || nums == null) {
            return;
        }

        int mergeSize = 1;  //当前有序的左组长度，1个元素自然有序，所以初始值改为1
        while (mergeSize < nums.length) {   //左组都有序，超过全部数组，即已排好序
            int left = 0;   //左组的指针
            while (left < nums.length) {  //左组的指针已经超过全部数组了
                int middle = left + mergeSize - 1;   //middle指针指向左组的最后一个元素
                //[middle+1,right]与[left,middle]两个数组已经有序
                if (middle > nums.length) {  // middle指针已经超过数组的长度，这一部分的左组已经有序，不能构成完整左组
                    break;
                }

                int right = Math.min(middle + mergeSize, nums.length - 1);
                merge(nums, left, middle, right);
                left = right + 1;

            }
            if (mergeSize > nums.length / 2) {   //防止mergeSize的大小溢出Int的范围
                break;
            }
            mergeSize <<= 1;
        }

    }


    //归并排序的应用:小和问题，N*logN的解法
    public static int smallSum(int[] nums) {
        if (nums.length < 2 || nums == null) {
            return 0;
        }

        return process2(nums, 0, nums.length - 1);
    }

    public static int process2(int[] nums, int start, int end) {
        if (start == end) {
            return 0;
        }
        int mid = start + (end - start) >> 1;
        return process2(nums, start, mid) +
                process2(nums, mid + 1, end) +
                merge2(nums, start, mid, end);
    }

    public static int merge2(int[] arr, int low, int middle, int high) {
        //用于储存归并后的临时数组
        int[] temp = new int[high - low + 1];
        //记录第一个数组的下标
        int i = low;
        //记录第二个数组的下标
        int j = middle + 1;
        int index = 0;
        int res = 0;
        while (i <= middle && j <= high) {
            res += arr[i] < arr[j] ? (high - j + 1) * arr[i] : 0;   //只有当右边的指针大于左边的指针的时候，才改变res
            //相等的时候，必须先拷贝右边的数组，在代码上就是在 == 的时候拷贝 j
            temp[index++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= middle) {
            temp[index++] = arr[i++];
        }
        while (j <= high) {
            temp[index++] = arr[j++];
        }
        for (int k = 0; k < high - low + 1; k++) {       //k<high-low+1不能使用=>k<arr.length
            //在后续过程中会使用递归操作，只对数组的[low,high]部分进行排序
            arr[low + k] = temp[k];
        }
        return res;
    }

}
