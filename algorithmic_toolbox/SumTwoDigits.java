package algorithmic_toolbox;

import java.math.BigInteger;
import java.util.Scanner;

public class SumTwoDigits {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger a = scanner.nextBigInteger();
        BigInteger b = scanner.nextBigInteger();
        BigInteger resultado = a.add(b);
        System.out.println(resultado);
        scanner.close();
    }
    
}