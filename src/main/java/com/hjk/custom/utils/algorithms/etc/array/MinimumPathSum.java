package com.hjk.custom.utils.algorithms.etc.array;

import java.util.Arrays;

public class MinimumPathSum {

    public static void main(String... args) {
        minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
    }

    public static int minPathSum(int[][] grid) {
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] += grid[0][i - 1];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        System.out.println(Arrays.deepToString(grid));

        return grid[grid.length - 1][grid[0].length - 1];
    }
}
