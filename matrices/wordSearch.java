
public class wordSearch {
    public static boolean isExists(String word, int i, int j, int idx, char[][] board) {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j] == '*' || board[i][j] != word.charAt(idx)) {
            return false;
        }

        if(word.length()-1 == idx) {
            return true;
        }

        char c = board[i][j];
        board[i][j] = '*';
        boolean res = isExists(word, i-1, j, idx+1, board) || isExists(word, i+1, j, idx+1, board) || isExists(word, i, j-1, idx+1, board) || isExists(word, i, j+1, idx+1, board);
        board[i][j] = c;
        return res;
    }

    public static boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j] == word.charAt(0) && isExists(word, i, j, 0, board)) {
                    return true;
                }
            }
        }

        return false;
    }    

    public static void main(String[] args) {
        char[][] word = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        System.out.println(exist(word, "ABCCD"));
    }
}
