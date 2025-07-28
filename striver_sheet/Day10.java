// Day 10: recursion and backtracking

import java.util.*;

public class Day10 {
    // Leetcode Q: 46. Permutations
    
    // swap fn
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // helper recurive fn
    private void permuteUtil(int idx, int[] nums, List<List<Integer>> ans) {
        // base case
        if(idx == nums.length) {
            // if idx has reached the length then add the list in the ans list and return
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<nums.length; i++) {
                list.add(nums[i]);
            }
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int i=idx; i<nums.length; i++) {
            // swap nums[idx] with each element to make different permutations
            swap(nums, idx, i);
            // recursive call
            permuteUtil(idx+1, nums, ans);
            // undo the swapping while backtracking
            swap(nums, idx, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        // as this is permutation problem to we will use recursion
        List<List<Integer>> ans = new ArrayList<>();
        permuteUtil(0, nums, ans);
        return ans;
    }    

    // Leetcode Q: 51. N-Queens

    // checking of Queen is safe to put here or not
    private boolean isSafe(List<String> board, int row, int col, int n) {
        // check if the column is safe
        int i=row;
        while(i>=0) {
            if(board.get(i).charAt(col) == 'Q') return false;
            i--;
        }

        // if right upper diagnal is safe
        i = row;
        int j = col;
        while(i>=0 && j<n) {
            if(board.get(i).charAt(j) == 'Q') return false;
            i--;
            j++;
        }

        // if left upper daignal is safe
        i = row;
        j = col;
        while(i>=0 && j>=0) {
            if(board.get(i).charAt(j) == 'Q') return false;
            i--;
            j--;
        }

        return true;
    }

    // recursive function to place Queens
    private void placeQueens(int row, int n, List<String> board, List<List<String>> ans) {
        // base case
        if(row == n) {
            // all queens are placed
            ans.add(new ArrayList<>(board));
            return;
        }

        // try each position in one row
        // first get the row as a StringBuilder

        StringBuilder sb = new StringBuilder(board.get(row));
        for(int i=0; i<n; i++) {
            if(isSafe(board, row, i, n)) {
                sb.setCharAt(i, 'Q');   // place queen
                board.set(row, sb.toString());
                placeQueens(row+1, n, board, ans);
                sb.setCharAt(i, '.');   // remove queen while backtracking
                board.set(row, sb.toString());
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        // we will make a blank n*n board
        List<String> board = new ArrayList<>();
        List<List<String>> boards = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            String r = "";
            for(int j=0; j<n; j++) {
                r += '.';
            }
            board.add(new String(r));
        }
        
        placeQueens(0, n, board, boards);
        return boards;
    }

    // Leetcode Q: 37. Sudoku Solver

    // to check if it is valid
    private boolean isValid(char[][] board, int row, int col, char num) {
        // check if it is unique in row and col, or in the small 3by3 cube
        for(int i=0; i<board.length; i++) {
            if(board[row][i] == num) return false;
            if(board[i][col] == num) return false;
            if(board[3*(row/3)+i/3][3*(col/3)+i%3] == num) return false;
        }

        return true;
    }

    // recursive fn to solve sudoku
    private boolean solve(char[][] board) {
        // traverse through martix
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {

                // as yout find any empty space, try to fill it every possible number
                if(board[i][j] == '.') {
                    for(char c='1'; c<='9'; c++) {
                        if(isValid(board, i, j, c)) {
                            board[i][j] = c;

                            // check if rest can be done
                            if(solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        } 
                    }

                    // tried all
                    return false;
                }
            }
        }

        return true;
    }
    public void solveSudoku(char[][] board) {
        // we will make a recursive function that will place numbers row by row
        solve(board);
    }

    // Not in Leetcode: M coloring problem
    private void makeGraph(List<Integer>[] graph, int n, int[][] edges) {
        Arrays.fill(graph, new ArrayList<>());
        for(int[] e: edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
    }
    private boolean validCol(int node, int c, List<Integer>[] graph, int[] color) {
        // check whether the neighbours have same color
        for(int n: graph[node]) {
            if(color[n] == c) return false;
        }

        return true;
    }

    private boolean color(int node, List<Integer>[] graph, int[] color, int m, int n) {
        if(node == n) return true;  // all nodes are colored

        for(int i=1; i<=m; i++) {
            if(validCol(node, i, graph, color)) {
                color[node] = i;
                // check the rest graph
                if(color(node+1, graph, color, m, n)) {
                    return true;
                }
                color[node] = 0;
            }
        }

        return false;
    }

    public boolean graphColoring(int[][] edges, int m, int n) {
        // first make an undirected graph from the edges
        List<Integer>[] graph = new List[n];
        makeGraph(graph, n, edges);
        return color(0, graph, new int[m], m, n);
    }

    // Not in leetcode: rat in maze
    private void findPathUtil(int[][] grid, boolean[][] visited, int row, int col, List<String> paths, String path) {
        if(row == grid.length-1 && col == grid[0].length-1) {
            paths.add(new String(path));
            return;
        }

        // try every move in recursion
        // down
        if(row+1 < grid.length && grid[row+1][col] == 1 && !visited[row+1][col]) {
            visited[row+1][col] = true;
            findPathUtil(grid, visited, row+1, col, paths, path + 'D');
            visited[row+1][col] = false;
        }

        // left
        if(col-1 >= 0 && grid[row][col-1] == 1 && !visited[row][col-1]) {
            visited[row][col-1] = true;
            findPathUtil(grid, visited, row, col-1, paths, path + 'L');
            visited[row][col-1] = false;
        }

        // rigth
        if(col+1 < grid[0].length && grid[row][col+1] == 1 && !visited[row][col+1]) {
            visited[row][col+1] = true;
            findPathUtil(grid, visited, row, col+1, paths, path + 'R');
            visited[row][col+1] = false;
        }

        // up
        if(row-1 >= 0 && grid[row-1][col] == 1 && !visited[row-1][col]) {
            visited[row-1][col] = true;
            findPathUtil(grid, visited, row-1, col, paths, path + 'U');
            visited[row-1][col] = false;
        }
    }

    public List<String> findPath(int[][] grid) {
        // make a visited matrix first
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        List<String> ans = new ArrayList<>();
        if(grid[0][0] == 1) findPathUtil(grid, visited, 0, 0, ans, new String(""));
        return ans;
    }

    // Leetcode Q: 139 word break
    private boolean Break(int idx, String s, Set<String> wordDict, int[] dp) {
        if(idx >= s.length()) {
            return true;    // all the partitions are done
        }

        if(dp[idx] != -1) return dp[idx] == 1;

        String s2 = new String("");
        for(int i=idx; i<s.length(); i++) {
            s2 += s.charAt(i);
            // only if a portion is found, then we will go for further partitions
            if(wordDict.contains(s2)) {
                if(Break(idx+s2.length(), s, wordDict, dp)) {
                    dp[idx] = 1;
                    return true;
                }
            }
        }

        dp[idx] = 0;
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>();
        for(String st: wordDict) {
            wordSet.add(st);
        }

        // dp array for memoization
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return Break(0, s, wordSet, dp);
    }
}