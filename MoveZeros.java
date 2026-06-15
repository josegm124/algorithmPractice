
import java.util.Arrays;

public class MoveZeros {

    public static int[] moveZeros(int[] myArray) {
        int l = 0, r = 1, arrayLenth = myArray.length;
        if (arrayLenth < 2)
            return new int[] { -1 };
        while (r < arrayLenth) {
            if (myArray[l] != 0) {
                l++;
                r++;

            } else if (myArray[r] == 0) {
                System.out.println("lalo");
                r++;
            } else {
                int temp = myArray[r];
                myArray[r] = myArray[l];
                myArray[l] = temp;
            }

        }
        return myArray;
    }

    public static void main(String[] args) {
        int[] myArray = { 1, 2, 0, 3, 0, 4, 5, 6 };
        System.out.println(Arrays.toString(moveZeros(myArray)));

    }
}
