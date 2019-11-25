import java.util.ArrayList;

public class Homework8 {

    static class QueensProblem {
        int[] cols;
        int n;
        int total = 0;
        int numberOfNodes = 0;

        ArrayList<ArrayList> results = new ArrayList<ArrayList>();

        QueensProblem(int size) {
            n = size;
            cols = new int[size+1];
        }

        public void queens(int i) {
            if(promising(i)) {
                numberOfNodes++;
                if(i == n) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for(int k=1; k <= n; k++) {
                        temp.add(cols[k]-1);
                        //System.out.print(cols[k]-1 + " ");

                    }
                    results.add(temp);
                    //System.out.print("\n");
                    total++;
                }
                else {
                    for(int j=1; j <= n; j++) {
                        cols[i+1] = j;
                        queens(i +1);
                    }
                }
            }
        }

        public boolean promising(int index) {
            int k = 1;
            boolean prom = true;

            while (k < index && prom) {
                if (cols[index] == cols[k] || Math.abs(cols[index] - cols[k]) == index-k) {
                    prom = false;
                }
                k++;
            }
            return prom;
        }
    }
    public static void main(String args[]) {
        QueensProblem queen;
        int resultStepper[] = {4, 8, 10, 12};
        ArrayList<Integer> first;
        for(int i : resultStepper) {
            queen = new QueensProblem(i);
            queen.queens(0);
            System.out.println("# of solutions for n=" + i + ": " + queen.total);
            System.out.println("# of nodes created: " + queen.numberOfNodes);
            System.out.println("1st solution:");
            first = queen.results.get(0);
            for(Integer j : first) {
                System.out.print(j + " ");
            }
            System.out.print("\n\n");

        }
    }

}
