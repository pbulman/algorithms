/**
 * NOTE: Price/weight arrays are passed in already sorted by price/weight ratio.
 */
public class Knapsack_DP_Greedy {

    /** Dynamic Approach **/
    static class DPKnapsack {
        int W;
        int weights[];
        int prices[];
        int n;

        public DPKnapsack(int max, int[] wt, int[] pc, int sz) {
            W = max;
            weights = wt;
            prices = pc;
            n = sz;
        }

        public int knapsack() {
            int results[][] = new int[n+1][W+1];
            for(int i=0; i <= n; i++) {
                for(int j=0; j <= W; j++) {
                    if(i == 0 || j == 0) {
                        results[i][j] = 0;
                    }
                    else if (weights[i - 1] <= j) {
                        results[i][j] = Math.max(prices[i-1] + results[i-1][j - weights[i-1]], results[i-1][j]);
                    }
                    else {
                        results[i][j] = results[i-1][j];
                    }
                }
            }
            return results[n][W];
        }

    }


    /** Greedy approach **/
    static class GreedyKnapsack {
        int W;
        int weights[];
        int prices[];
        int n;

        public GreedyKnapsack(int max, int[] wt, int[] pc, int sz) {
            W = max;
            weights = wt;
            prices = pc;
            n = sz;
        }

        public int knapsack() {
            int maxProfit = 0;
            int currWeight = 0;
            for(int i=0; i < n; i++) {
                currWeight += weights[i];
                if(currWeight < W) {
                    maxProfit += prices[i];
                }
                else {
                    break;
                }
            }
            return maxProfit;
        }
    }


    public static void main(String args[]) {
        long startTime, stopTime, elapsedTime;
        int[] p = {40,30,50,10};
        int[] w = {2,5,10,5};
        int[] p2 = {50, 140, 60};
        int[] w2 = {5, 20, 10};

        GreedyKnapsack b = new GreedyKnapsack(30, w2, p2, 3);
        startTime = System.nanoTime();
        System.out.println(b.knapsack());
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Time elapsed: " + elapsedTime);

        DPKnapsack c = new DPKnapsack(16, w, p, 4);
        startTime = System.nanoTime();
        System.out.println(c.knapsack());
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Elapsed Time: " + elapsedTime);
    }
}
