package uselessTest;

/**
 * Created by coco1 on 2016/9/13.
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        return search(nums ,0 ,target);
    }
    public int search(int[] nums, int id, int target) {
        if(target < 0 || id >= nums.length) {
            return 0;
        } else if(target == 0) {
            return 1;
        } else {
            return (search(nums, id, target - nums[id]) +
                    search(nums, id+1, target));
        }
    }
    public static void main(String args[]) {
        CombinationSumIV c = new CombinationSumIV();
        int [] nums ;
        nums= new int[]{1, 2, 3, 4};
        int target = 4;
        System.out.print(c.combinationSum4(nums,target));
    }
}
