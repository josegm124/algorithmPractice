import java.util.HashMap;

public class Lc560 {

    public static int myFunction(int[] nums, int target) {
        int resp = 0;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 1. var sum para cada iteracion
        // 2. HashMap en clave vamos a tener la sum. En el valor cuantas veces a aparecido
        // 3. condicion matematica sum-target
        // 4. inicialisare el mapa 
        int sum = 0;
        HashMap<Integer, Integer> myMap = new HashMap<>();
        myMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int dif = sum - target;
            resp += myMap.getOrDefault(dif, 0);
            myMap.put(sum, myMap.getOrDefault(sum, 0) + 1);
        }
        return resp;
    }

    public static void main(String[] args) {

        int[] nums = { 1, 2, 3 };
        int target = 3;
        System.out.println(myFunction(nums, target));
        System.out.println("Lalo");
        System.out.println(myFunction(new int[] {1, -1, 0}, 0));
    }
    
}
