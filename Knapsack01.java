/**
 * 0/1 背包问题（动态规划）
 * 问题：有 n 个物品，每个物品有重量 w[i] 和价值 v[i]，
 *       给定背包容量 C，求能装入背包的最大总价值。
 * 每个物品只能选一次（0/1）
 */
public class Knapsack01 {

    /**
     * 动态规划求解 0/1 背包最大价值
     *
     * @param C 背包容量
     * @param w 物品重量数组
     * @param v 物品价值数组
     * @return 最大总价值
     */
    public static int knapsack(int C, int[] w, int[] v) {
        int n = w.length;

        // dp[i][c] 表示前 i 个物品（0~i-1），容量为 c 时的最大价值
        int[][] dp = new int[n + 1][C + 1];

        // 遍历每个物品
        for (int i = 1; i <= n; i++) {
            // 遍历每种容量
            for (int c = 0; c <= C; c++) {
                // 默认不选当前物品
                dp[i][c] = dp[i - 1][c];

                // 如果当前容量能放下第 i-1 个物品（因为 i 从 1 开始）
                if (c >= w[i - 1]) {
                    // 选当前物品：价值 = 前 i-1 个物品在容量 c-w[i-1] 时的价值 + 当前物品价值
                    int valueWithCurrent = dp[i - 1][c - w[i - 1]] + v[i - 1];
                    // 取最大值
                    dp[i][c] = Math.max(dp[i][c], valueWithCurrent);
                }
            }
        }

        // 返回前 n 个物品、容量 C 时的最大价值
        return dp[n][C];
    }

    /**
     * 空间优化版本（一维数组）
     * 注意：容量需要从大到小遍历，避免重复选取
     */
    public static int knapsackOptimized(int C, int[] w, int[] v) {
        int n = w.length;
        int[] dp = new int[C + 1];

        for (int i = 0; i < n; i++) {
            // 逆序遍历容量，确保每个物品只选一次
            for (int c = C; c >= w[i]; c--) {
                dp[c] = Math.max(dp[c], dp[c - w[i]] + v[i]);
            }
        }
        return dp[C];
    }

    // 测试
    public static void main(String[] args) {
        int C = 10;
        int[] w = {2, 3, 4, 5};
        int[] v = {3, 4, 5, 6};

        int maxValue = knapsack(C, w, v);
        System.out.println("最大价值（二维DP）: " + maxValue);

        int maxValueOpt = knapsackOptimized(C, w, v);
        System.out.println("最大价值（一维优化）: " + maxValueOpt);
    }
}