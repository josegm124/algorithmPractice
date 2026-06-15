

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return -1;
        }
        int r = 0, l = 0, max = 0;
        Set<Character> window = new HashSet<>();
        while (r < s.length()) {
            char c = s.charAt(r);
            if (!window.contains(c)) {
                window.add(c);
                r++;
                max = Math.max(max, window.size());
            } else {
                window.remove(s.charAt(l));
                l++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
         System.out.println(lengthOfLongestSubstring("aabcdde")); 
    }
}