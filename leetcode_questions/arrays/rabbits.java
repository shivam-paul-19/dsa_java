// Leetcode Q: 781. Rabbits in Forest

import java.util.*;

public class rabbits {
    public static int numRabbits(int[] answers) {
        HashMap<Integer, Integer> rabbitMap = new HashMap<>();
        int count = 0;
        for(int i=0; i<answers.length; i++) {
            if(answers[i] == 0){
                count++;
            } else if(!rabbitMap.containsKey(answers[i])) {
                rabbitMap.put(answers[i], 1);
                count += (answers[i]+1);
            } else {
                rabbitMap.put(answers[i], rabbitMap.get(answers[i])+1);
                if(rabbitMap.get(answers[i]) > answers[i]+1) {
                    rabbitMap.put(answers[i], 1);
                    count += (answers[i]+1);   
                }
            }
        }

        return count;
    }
}