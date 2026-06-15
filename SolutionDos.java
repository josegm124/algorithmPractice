import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolutionDos {
    public static String[] solution(int speed, String initialState) {
        int n = initialState.length();
        List<Integer> positions = new ArrayList<>();
        List<Character> directions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = initialState.charAt(i);

            if (c == 'L' || c == 'R') {
                positions.add(i);
                directions.add(c);
            }
        }
        List<String> result = new ArrayList<>();
        int time = 0;
        while (true) {
            char[] frame = new char[n];
            Arrays.fill(frame, '.');
            boolean hasParticleInside = false;
            for (int i = 0; i < positions.size(); i++) {
                int start = positions.get(i);
                char direction = directions.get(i);
                int currentPosition;
                if (direction == 'R') {
                    currentPosition = start + time * speed;
                } else {
                    currentPosition = start - time * speed;
                }
                if (currentPosition >= 0 && currentPosition < n) {
                    frame[currentPosition] = 'X';
                    hasParticleInside = true;
                }
            }
            result.add(new String(frame));
            if (!hasParticleInside) {
                break;
            }
            time++;
        }
        return result.toArray(new String[0]);
    }
    public static void main(String[] args) {

        String[] result = solution(2, "..R...");

        for (String frame : result) {
            System.out.println(frame);
        }
    }
}