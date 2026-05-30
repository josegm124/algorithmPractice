package algorithmic_toolbox;

import java.math.BigInteger;

public class MultiplicacionBigInteger {
    public static void main(String[] args) {
        BigInteger num1 = new BigInteger("12345678901234567890");
        BigInteger num2 = new BigInteger("98765432109876543210");
        BigInteger resultado = num1.multiply(num2);
        System.out.println("El producto es: " + resultado);
    }
}