package demoGreedy;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class BestTimeToBuyAndSellStock121 {

    // 暴力法，超时
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            int buyPrice = prices[i];
            for (int j = i; j < prices.length; j++) {
                int profit = prices[j] - buyPrice;
                ans = Math.max(ans, profit);
            }
        }
        return ans;
    }

    public int maxProfit2(int[] prices) {
        // 遍历价格数组一遍，记录历史最低点。
        // 每天都考虑：今日价格 - 最低价格 能获利多少？
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

}
