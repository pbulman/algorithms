package test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * NOTE: Price/weight arrays are passed in already sorted by price/weight ratio.
 *       BFS algorithm runs infinitely, although an effort was made at implementation.
 */
public class Homework9_2 {

    /** Best-first search approach **/
    static class BFSKnapsack {
        int W;
        int weights[];
        int prices[];
        int n;

        public BFSKnapsack(int max, int[] wt, int[] pc, int sz) {
            W = max;
            weights = wt;
            prices = pc;
            n = sz;
        }
        /** Node class **/
        static class Node {
            int level;
            int profit;
            int weight;
            float bound;

            public Node(int l, int p, int w, float b) {
                level = l;
                profit = p;
                weight = w;
                bound = b;
            }
        }

        /** Comparator for Priority Queue, bases priority on bound **/
        static class BoundComparator implements Comparator<Node> {
            public int compare(Node n1, Node n2) {
                if(n1.bound > n2.bound) { return 1; }
                else if(n1.bound < n2.bound) { return -1; }
                else { return 0; }
            }
        }

        public int knapsack() {
            PriorityQueue<Node> q = new PriorityQueue<Node>(new BoundComparator());
            Node u = new Node(0,0,0,0);
            Node v = new Node(0,0,0,0);

            int maxProfit = 0;
            v.bound = bound(v);
            q.add(v);
            while(q.size() > 0) {
                v = q.poll();
                q.remove(v);
                if(v.bound > maxProfit) {
                    u.level = v.level + 1;
                    u.weight = v.weight + weights[u.level - 1];
                    u.profit = v.profit + prices[u.level - 1];
                    if (u.weight <= W && u.profit > maxProfit) {
                        maxProfit = u.profit;
                    }
                    u.bound = bound(u);
                    if (u.bound > maxProfit) {
                        q.add(u);
                    }
                    u.weight = v.weight;
                    u.profit = v.profit;
                    u.bound = bound(u);
                    if (u.bound > maxProfit) {
                        q.add(u);
                    }
                }
            }

            return maxProfit;
        }


        public float bound(Node u) {
            int totweight, j, k;
            float result;
            if(u.weight >= W) {
                return 0;
            }
            else {
                result = u.profit;
                j = u.level+1;
                totweight = u.weight;
                while (j <= n && totweight + weights[j-1] <= W) {
                    totweight = totweight + weights[j-1];
                    result = result + prices[j-1];
                    j++;
                }
                k = j;
                if (k <= n) {
                    result = result + (W - totweight) * prices[k-1]/weights[k-1];
                }
                return result;
            }
        }

    }


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


        BFSKnapsack d = new BFSKnapsack(16, w, p ,4);
        startTime = System.nanoTime();
        System.out.println(d.knapsack());
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Elapsed Time: " + elapsedTime);

    }
}
