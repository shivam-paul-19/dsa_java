// Leetocde Q: 2115. Find All Possible Recipes from Given Supplies

import java.util.*;

public class possibleRecipes {
    private boolean canMade(int i, HashSet<String> suppliesSet, List<List<String>> ingredients) {
        for(int s=0; s<ingredients.get(i).size(); s++) {
            if(!suppliesSet.contains(ingredients.get(i).get(s))) {
                return false;
            }
        }

        return true;
    }
    
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        HashSet<String> suppliesSet = new HashSet<>();
        List<String> answer = new ArrayList<>();

        for(String s: supplies) {
            suppliesSet.add(s);
        }

        int count = recipes.length;
        while(count>0) {
            for(int i=0; i<recipes.length; i++) {
                if(answer.contains(recipes[i])) continue;
                if(canMade(i, suppliesSet, ingredients)) {
                    answer.add(recipes[i]);
                    suppliesSet.add(recipes[i]);
                }
            }
            count--;
        }

        return answer;
    }
}
