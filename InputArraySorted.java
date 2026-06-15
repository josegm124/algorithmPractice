

import java.util.Arrays;

public class InputArraySorted {

    public static int[] mySum(int[] array, int target) {
        int[] resp = new int[2];
        int l = 0, arrayLenth = array.length, r = arrayLenth - 1;
        while (l < r) {
            int sum = array[r] + array[l];
            if (sum == target) {
                resp[0] = l + 1;
                resp[1] = r + 1;
                return resp;
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[] {-1,-1};
    }

    public static void main(String[] rgs) {
        int[] inArray = { 1, 2, 3, 4, 8, 65, 90 };
        int target = 7;
        System.out.println(Arrays.toString(mySum(inArray, target)));

    }
    
}
