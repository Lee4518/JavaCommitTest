/**
 * 最长递增子序列（LIS）- 经典 DP
 * 时间复杂度 O(n²)，空间复杂度 O(n)
 */
public class LIS_DP {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // dp[i] 表示以 nums[i] 结尾的最长递增子序列长度
        int[] dp = new int[n];
        
        // 初始化：每个元素自身构成长度为 1 的子序列
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        int maxLen = 1;

        // 对于每个位置 i，看前面所有位置 j
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 如果 nums[j] < nums[i]，可以接在后面
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 更新全局最大值
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("最长递增子序列长度: " + lengthOfLIS(nums));
        // 输出: 4
    }
}