package algorithm.SearchAlgorithm;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {1, 4, 5, 7, 8, 12, 56, 56, 56, 56, 87};
        System.out.println(binarySearch4(nums, 56));
    }

    /**
     * 二分查找的递归实现，注意必须有界
     */
    public static int binarySearch(int[] nums, int target, int left, int right) {
        if (left > right) {   //基线条件
            return -1;
        }
        int mid = left + ((right - left) >> 1);  //防止溢出  >>1 位运算
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, target, left, mid);  //向左递归
        } else {
            return binarySearch(nums, target, mid + 1, right); //向右递归
        }
    }

    /**
     * 二分查找的非递归实现
     */
    public static int binarySearch2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {  //搜索区间的概念,搜索区间为[left,right];
            int mid = left + ((right - left) / 2);   //逐步减少搜索区间
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }


    //返回全部索引，返回数组
    public static ArrayList<Integer> binarySearch3(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        ArrayList<Integer> result = new ArrayList<>();
        while (left <= right) {  //搜索区间的概念,搜索区间为[left,right];
            int mid = left + ((right - left) / 2);   //逐步减少搜索区间
            if (nums[mid] == target) {
                result.add(mid);
                int resultIndexLeft = mid - 1;
                int resultIndexRight = mid + 1;
                while (nums[resultIndexLeft] == target) {
                    result.add(resultIndexLeft);
                    --resultIndexLeft;
                }
                while (nums[resultIndexRight] == target) {
                    result.add(resultIndexRight);
                    ++resultIndexRight;
                }
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return result;
    }


    //返回第一个出现的位置
    public static int binarySearch4(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {  //搜索区间的概念,搜索区间为[left,right];
            int mid = left + ((right - left) / 2);   //逐步减少搜索区间
            if (nums[mid] == target) {
                while (nums[mid] == target) {
                    mid++;
                }
                return mid -1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
