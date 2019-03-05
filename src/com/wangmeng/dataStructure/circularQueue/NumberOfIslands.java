package com.wangmeng.dataStructure.circularQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * NumberOfIslands
 * 两种方法--DFS和BFS
 */
public class NumberOfIslands {
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    Queue<Integer> queue;
    public int numIslands(char[][] grid) {
        //sanity check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (grid[x][y] == '1') {
                    count++;
                    //dfs广度优先算法
//                    dfs(grid,rows,cols,x,y);
                    //bfs深度优先算法
                    bfs(grid,rows,cols,x,y);
                }
            }
        }
        return count;
    }

    //dfs算法
    public void dfs(char[][] grid, int rows, int cols, int x, int y) {
        //base case
        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        //recursion
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            dfs(grid,rows,cols,newX,newY);
        }
    }

    //bfs算法
    public void bfs(char[][] grid, int rows, int cols, int x, int y) {
        queue = new LinkedList<Integer>();
        int code= x * cols + y;
        queue.offer(code);
        //bfs
        while (!queue.isEmpty()) {
            Integer code1 = queue.poll();
            int i = code1 / cols;
            int j = code1 % cols;
            if (i > 0 && grid[i - 1][j] == '1') {
                //当前元素的左边
                queue.offer((i-1) * cols + j);
                grid[i - 1][j] = '0';
            }
            if (i < cols -1 && grid[i+1][j] == '1') {
                //当前元素的右边
                queue.offer((i + 1) * cols + j);
                grid[i + 1][j]='0';
            }
            if (j > 0 && grid[i][j - 1] == '1') {
                //当前元素的下边
                queue.offer(i * cols + j - 1);
                grid[i][j - 1] = '0';
            }
            if (j < rows - 1 && grid[i][j + 1] == '1') {
                //当前元素的上边
                queue.offer(i * cols + j + 1);
                grid[i][j + 1] = '0';
            }
        }
    }

    public static void main(String[] args) {
        char[][] twoDArray = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        int i = new NumberOfIslands().numIslands(twoDArray);
        System.out.println(i);
    }
}
