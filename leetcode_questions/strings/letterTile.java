public class letterTile {
    public static int fact(int n) {
        if(n==1 || n==0) {
            return 1;
        }
        return n*fact(n-1);
    }

    // public static int numTilePossibilities(String tiles) {
        
    // }
    public static void main(String[] args) {
        System.out.println((fact(3)/fact(3-1))/fact(2));
    }
}
