package com.hjk.custom.utils.algorithms.etc.graph;

import java.util.*;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class Graph {

    int[] vertex;

    int[][] edges;

    int[] dp;

    boolean[] visited;

    public static void main(String... args) {
        var graph = new Graph();
        graph.solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}, new int[]{1, 3,4}, 2);
//        graph.solution(3, new int[][]{{1,2}, {2,3}}, new int[]{2,3}, 1);
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        var index = 0;
        dp = new int[n];
        edges = new int[n][n];
        var queue = new LinkedList<Integer>(List.of(destination - 1));
        dp[destination - 1] = 1;
        for (var road : roads) {
            edges[road[0] - 1][road[1] - 1] = 1;
            edges[road[1] - 1][road[0] - 1] = 1;
        }
        dijkstra(queue, 1);
        for (var source : sources) {
            answer[index++] = source == destination ? 0 : dp[source - 1] == 0 ? -1 : dp[source - 1];
        }
        return answer;
    }

    public void dijkstra(Queue<Integer> queue, int count) {
        if (queue.isEmpty()) return;

        var temp = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            var edge = edges[queue.poll()];
            for (int i = 0; i < edge.length; i++) {
                if (dp[i] > 0 || edge[i] == 0) continue;
                dp[i] = count;
                temp.add(i);
            }
        }
        queue.addAll(temp);
        dijkstra(queue, count + 1);
    }

    public void init(int n, int[][] sources) {
        vertex = new int[n];
        edges = new int[n][n];
        visited = new boolean[n];
        dp = new int[n];

        for (int i = 1; i <= n; i++) {
            vertex[i - 1] = i;
        }

        for (var source : sources) {
            edges[source[0] - 1][source[1] - 1] = 1;
            edges[source[1] - 1][source[0] - 1] = 1;
        }
    }
}
