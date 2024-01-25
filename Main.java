import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Main {
    private static final int LIMIT = 10000000;

    public static class myThread extends Thread{
        int start;
        int end;
        List<Integer> primes;

        public myThread(int start, int end, List<Integer> primes){
            this.start = start;
            this.end = end;
            this.primes = primes;
        }

        @Override
        public void run(){
            findPrimesInRange(start, end, primes);
        }
    }

    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<Integer>();
        int threadsToUse = 1;
        int upperBound = LIMIT;
        Scanner kb = new Scanner(System.in);

        System.out.print("Enter upper bound: ");
        upperBound = kb.nextInt();
        System.out.print("Enter number of threads: ");
        threadsToUse = kb.nextInt();
        kb.close();

        myThread[] threads = new myThread[threadsToUse];    
        
        int range = upperBound/threadsToUse;
        
        
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadsToUse; i++) {

            int start = (i+1)*range-range+1; 
            int end = (i == threadsToUse - 1) ? upperBound : start+range-1;


            threads[i] = new myThread(start, end, primes);
            threads[i].start();
        }

        for (int i = 0; i < threadsToUse; i++) {
            try {
                threads[i].join(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.printf("%d primes were found.\n",primes.size());
        System.out.printf("Runtime: %d", endTime-startTime);
    } 

    public static void findPrimesInRange(int start, int end, List<Integer> primes) {
        for (int currentNum = start; currentNum <= end; currentNum++) {
            if (check_prime(currentNum)) {
                synchronized (primes) {
                    primes.add(currentNum);
                }
            }
        }
    }

    /*
    This function checks if an integer n is prime.

    Parameters:
    n : int - integer to check

    Returns true if n is prime, and false otherwise.
    */
    public static boolean check_prime(int n) {
        if (n == 1){
            return false;
        }

        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}
