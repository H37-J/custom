package com.hjk.custom.utils.algorithms.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class Algos {
    static int finalX;
    static int finalY;
    static int count = 1;
    static int answer = -1;


    public static void main(String... args) {
        coin();
    }

    public static int coin() {
        int amount = 12;
        int[] coins = {2,4,5};
        int[] combinations = new int[amount + 1];
        combinations[0] = 1;

        for(int coin : coins) {
            for(int i = coin; i < amount + 1; i++) {
                combinations[i] += combinations[i - coin];
            }
            print(combinations);
        }
        return combinations[amount];
    }



    // [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]	11
    public static int solution1(int[][] maps) {
        finalX = maps.length - 1;
        finalY = maps[0].length - 1;
        int[][] maps2 = new int[maps.length][maps[0].length];
        for (int i = 0; i < maps.length; i++) {
            maps2[i] = maps[i].clone();
        }
        checkWay(maps, 0, 0);
        return answer;
    }

    public static boolean checkWay(int[][] maps, int x, int y) {
        if (x < 0 || y < 0 || x > finalX || y > finalY) return false;
        if (x == finalX && y == finalY) {
            answer = count;
            return true;
        }

        if (maps[x][y] == 1) {
            count++;
            maps[x][y] = -1;
            if(checkWay(maps, x, y)) maps[x][y] = 1;
            if (checkWay(maps, x + 1, y)) {
                return true;
            }
            if (checkWay(maps, x, y + 1)) {
                return true;
            }
            if (checkWay(maps, x - 1, y)) {
                return true;
            }
            if (checkWay(maps, x, y - 1)) {
                return true;
            }
            count--;
            return false;
        } else {
            return false;
        }
    }


}
