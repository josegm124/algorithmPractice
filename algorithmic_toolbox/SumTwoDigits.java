package algorithmic_toolbox;

import java.util.Scanner;

public class SumTwoDigits {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int resultado = a + b;
        System.out.println(resultado);
        scanner.close();
    }
}