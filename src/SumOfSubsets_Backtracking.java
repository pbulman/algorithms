public class SumOfSubsets_Backtracking {

    static class SumOfSubsetsProblem {
        int weights[];
        String result[];
        int size;
        int goal;

        SumOfSubsetsProblem(int[] w, int g) {
            goal = g;
            weights = w;
            size = weights.length;
            result = new String[size+1];
        }

        public void sumOfSubsets(int i, int weight, int total) {
            if(promising(i, weight, total)) {
                if(weight == goal) {
                    for(int j=0; j < size; j++) {
                        System.out.print(result[j] + " ");
                    }
                    System.out.print("\n");
                }
                else {
                    result[i+1] = "yes";
                    sumOfSubsets(i+1, weight + weights[i+1], total - weights[i+1]);
                    result[i+1] = "no";
                    sumOfSubsets(i+1, weight, total - weights[i+1]);

                }
            }
        }

        public boolean promising(int index, int weight, int total) {
            return (weight+total >= goal) && (weight == goal || weight + weights[index+1] <= goal);
        }
    }
    public static void main(String args[]) {
        int[] problem = {2, 10, 13, 17, 22, 42};
        SumOfSubsetsProblem s = new SumOfSubsetsProblem(problem, 52);
        s.sumOfSubsets(-1,0, 106);
    }

}
