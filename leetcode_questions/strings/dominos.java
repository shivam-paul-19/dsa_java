// Leetcode Q: 838. Push Dominoes

public class dominos {
    public static String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] forces = new int[n];
        int force = 0;

        // measuring right forces
        for(int i=0; i<n; i++) {
            char c = dominoes.charAt(i);
            if(c == 'R') {
                force = n;
            } else if(c == 'L') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] += force;
        }

        // measuring left forces 
        force = 0;
        for(int i=n-1; i>=0; i--) {
            char c = dominoes.charAt(i);
            if(c == 'L') {
                force = n;
            } else if(c == 'R') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] -= force;
        }

        // making the answer string
        StringBuilder sb = new StringBuilder();
        for(int f: forces) {
            if(f == 0) sb.append('.');
            else if(f > 0) sb.append('R');
            else sb.append('L');
        }

        return sb.toString();
    }
}
