package algorithmic_toolbox;

import java.util.Scanner;

public class MaxPairwiseProduct {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        long max1 = 0;  
        long max2 = 0;
        
        for (int i = 0; i < n; i++) {
            long num = scanner.nextLong();
            
            if (num > max1) {
                max2 = max1; 
                max1 = num;   
            } else if (num > max2) {
                max2 = num;
            }
        }
        System.out.println(max1 * max2);
        scanner.close();
    }
}