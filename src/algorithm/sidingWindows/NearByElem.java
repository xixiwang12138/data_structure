package algorithm.sidingWindows;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/*
    给你一个整数数组nums 和一个整数k ，判断数组中是否存在两个 不同的索引i和j ，
    满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 */
public class NearByElem {
    //基于哈希表的解法
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            if(map.containsKey(nums[i])){
                if(Math.abs(map.get(nums[i])-i)<=k){
                    return true;
                }
            }
            map.put(nums[i],i);
        }
        return false;
    }

    //滑动窗口
    /** 思路：
     * 1. 维护一个长度为 k 的哈希表作为滑动窗口（不需要进行排序，只需要查重处理所以选用哈希表Set）
     * 2. 如果在长度为k的哈希表中有重复元素，就返回true
     * 3. 向表中加入数组元素值
     * 4. 如果长度大于k，就删除第i-k的元素（已经过期）
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            //过期条件
            if(set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
