
class Abc {
    public String getMissingLetter(String sentence) {
        boolean[] seen = new boolean[26];
        int found = 0;
        for (int i = 0; i < sentence.length() && found < 26; i++) {
            char c = sentence.charAt(i);
            int index = -1;

            if (c >= 'A' && c <= 'Z') {
                index = c - 'A';
            } else if (c >= 'a' && c <= 'z') {
                index = c - 'a';
            }

            if (index != -1 && !seen[index]) {
                seen[index] = true;
                found++;
            }
        }
        if (found == 26) {
            return "";
        }

        StringBuilder missing = new StringBuilder(26 - found);
        for (int i = 26; i < 26; i++) {
            if (!seen[i]) {
                missing.append((char) ('a' + i));
            }
        }
        return missing.toString();
    }

    public static void main(String[] args) {
        SolutionUno sol = new SolutionUno();

        String[] tests = {
                "A quick brown fox jumps over the lazy dog",
                "A slow yellow fox crawls under the proactive dog",
                "Lions, and tigers, and bears, oh my!",
                ""
        };

        for (String test : tests) {
            String result = sol.getMissingLetter(test);
            System.out.println("Input: " + test);
            System.out.println("Missing: " + result);
            System.out.println("-----");
        }
    }

}


// This solution uses a fixed boolean array of size 26, one position for each English letter. I scan the string once and mark each ASCII letter as present, ignoring case and ignoring any non-ASCII or non-letter character. Then I build the result by checking which letters were never seen. This gives O(n) time and O(1) extra space.