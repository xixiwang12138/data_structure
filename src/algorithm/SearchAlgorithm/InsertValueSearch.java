package algorithm.SearchAlgorithm;

public class InsertValueSearch {

    public static int insertValueSearch(int[] nums, int target){
        //在插值查找中，此判断必须有！否则可能造成Mid越界
        if(target>nums[nums.length-1] || target<nums[0]){
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {  //搜索区间的概念,搜索区间为[left,right];
            int mid = left + (right-left)*(target-nums[left])/(nums[right]-nums[left]);
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
}
