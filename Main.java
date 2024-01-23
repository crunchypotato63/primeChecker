import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int LIMIT = 10000000;

    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<Integer>();

        Scanner kb = new Scanner(System.in);

        System.out.print("Enter upper bound: ");
        int upperBound = kb.nextInt();
        System.out.print("Enter number of threads: ");
        int threadsToUse = kb.nextInt();
        kb.close();

        Thread[] threads = new Thread[threadsToUse];      
        
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadsToUse; i++) {
            threads[i].start();
        }

        long endTime = System.currentTimeMillis();

        
        // for(int current_num = 2; current_num <= LIMIT; current_num++) {
        //     if(check_prime(current_num)) {
        //         primes.add(current_num);
        //     }
        // }

        // System.out.printf("%d primes were found.\n",primes.size());
    } 

    public static void findPrimesInRange(int start, int end) {
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
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
