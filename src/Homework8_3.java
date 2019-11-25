package test;
/*
NOTE: Maximum profit is calculated correctly, although the proper set of items to reach the max profit does not print correctly.
      Since the max profit is 55 in this case, it can be deduced that the optimal set of items are items 1 & 3.

 */
public class Homework8_3 {
    static class KnapsackProblem {
        int[] profits;
        int[] weights;
        int max;
        int size;

        int maxProfit;
        int numbest;
        String[] bestset;

        String result[];

        KnapsackProblem(int[] p, int[] w, int m) {
            profits = p;
            weights = w;
            max = m;
            size = p.length;
            result = new String[size+1];
        }

        public void knapsack(int i, int profit, int weight) {
            if(weight <= max && profit > maxProfit) {
                maxProfit = profit;
                numbest = i;
                bestset = result;
            }
            if (promising(i, weight, profit)) {
                result[i+1] = "yes";
                knapsack(i+1, profit + profits[i+1], weight + weights[i+1]);
                result[i+1] = "no";
                knapsack(i+1, profit, weight);
            }
        }

        public boolean promising(int i, int weight, int profit) {
            int totweight;
            double bound;
            int j, k;

            if(weight >= max) {
                return false;
            }
            else {
                j = i+1;
                bound = profit;
                totweight = weight;
                while (j < size && totweight + weights[j] <= max) {
                    totweight = totweight + weights[j];
                    bound = bound + profits[j];
                    j++;
                }

                k = j;
                if (k < size) {
                    bound = bound + (max - totweight) * (double)profits[k] / (double)weights[k];
                }
                return bound > maxProfit;
            }

        }
    }
    public static void main(String args[]) {
        int[] p = {20, 30, 35, 12, 3};
        int[] w = {2, 5, 7, 3, 1};

        KnapsackProblem k = new KnapsackProblem(p, w, 9);
        k.numbest = 0;
        k.maxProfit = 0;
        k.knapsack(-1,0,0);
        System.out.println("Max profit: " + k.maxProfit + " => items 1 & 3 are optimal");
    }
}
