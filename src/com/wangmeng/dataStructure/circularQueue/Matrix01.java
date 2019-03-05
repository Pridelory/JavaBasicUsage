package com.wangmeng.dataStructure.circularQueue;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class Matrix01 {
    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    int distance = BFS(matrix, rows, cols, i, j);
                    result[i][j] = distance;
                }
            }
        }
        return result;
    }
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    //BFS method
    public int BFS(int[][] matrix, int rows, int cols, int x, int y) {
        int step = 0;
        //definate a queue
        LinkedList<Integer> queue = new LinkedList<>();
        //add root node to the queue
        queue.offer(x * cols + y);
        //iterate
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.peek();
                int row = temp / cols;
                int col = temp % cols;
                for (int[] d : dirs) {
                    int newRow = row + d[0];
                    int newCol = col + d[1];
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                        if (matrix[newRow][newCol] == 0) {
                            return step;
                        } else {
                            queue.offer(newRow * cols + newCol);
                        }
                    }
                }
            }
            queue.pop();
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] array = new int[][] {{1,0,1,1,0,0,1,0,0,1},{0,1,1,0,1,0,1,0,1,1},{0,0,1,0,1,0,0,1,0,0},{1,0,1,0,1,1,1,1,1,1},{0,1,0,1,1,0,0,0,0,1},{0,0,1,0,1,1,1,0,1,0},{0,1,0,1,0,1,0,0,1,1},{1,0,0,0,1,1,1,1,0,1},{1,1,1,1,1,1,1,0,1,0},{1,1,1,1,0,1,0,0,1,1}};
        int[][] ints = new Matrix01().updateMatrix(array);
        int count = 0;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                System.out.print(ints[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void test() {
        int[][] array = new int[][] {{1,0,1,1,0,0,1,0,0,1},{0,1,1,0,1,0,1,0,1,1},{0,0,1,0,1,0,0,1,0,0},{1,0,1,0,1,1,1,1,1,1},{0,1,0,1,1,0,0,0,0,1},{0,0,1,0,1,1,1,0,1,0},{0,1,0,1,0,1,0,0,1,1},{1,0,0,0,1,1,1,1,0,1},{1,1,1,1,1,1,1,0,1,0},{1,1,1,1,0,1,0,0,1,1}};
        int bfs = new Matrix01().BFS(array, 10, 10, 9, 0);
        System.out.println(bfs);
    }
}
