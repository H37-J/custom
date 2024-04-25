package com.hjk.custom.utils.algorithms.programmers;

import java.util.*;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class Three {
    Map<Integer, List<Integer>> edge = new HashMap<>();
    Map<Integer, List<Integer>> answers = new HashMap<>();
    boolean[] visited;
    int count = 1;

    public static void main(String... args) {
        var fun = new Three();
        fun.solution2(8, new int[][]{{1, 2}, {2, 3}, {2, 4}, {1, 5}, {4, 7}}, new int[]{1,2, 3, 7, 4, 5,8}, 1);
    }

    //상담원 인원
    //k	n	reqs	result
    //3	5	[[10, 60, 1], [15, 100, 3], [20, 30, 1], [30, 50, 3], [50, 40, 1], [60, 30, 2], [65, 30, 1], [70, 100, 2]]	25
    //2	3	[[5, 55, 2], [10, 90, 2], [20, 40, 2], [50, 45, 2], [100, 50, 2]]	90
    public int solution1(int k, int n, int[][] reqs) {
        int answer = 0;
        return answer;
    }

    //n	roads	sources	destination	result
    //3	[[1, 2], [2, 3],[1,4]]	[2, 3]	1	[1, 2]
    //5	[[1, 2], [1, 4], [2, 4], [2, 5], [4, 5]]	[1, 3, 5]	5	[2, -1, 0]
    public int[] solution2(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        Arrays.fill(answer, - 1);
        visited = new boolean[n + 1];
        for (var road : roads) {
            var list1 = edge.getOrDefault(road[0], new ArrayList<Integer>());
            var list2 = edge.getOrDefault(road[1], new ArrayList<Integer>());
            list1.add(road[1]);
            list2.add(road[0]);
            edge.put(road[0], list1);
            edge.put(road[1], list2);
        }
        print(edge);
        visited[destination] = true;
        recur(destination);

        answers.forEach((index, list) -> {
            for(int i = 0; i < sources.length; i++) {
                if(sources[i] == destination) answer[i] = 0;
                if(list.contains(sources[i])) {
                    answer[i] = index;
                }
            }
        });

        return answer;
    }

    public void recur(int start) {
        var queue = new LinkedList<Integer>();
        var list = new ArrayList<Integer>();
        for (var check : edge.get(start)) {
            if (visited[check]) continue;
            queue.add(check);
            list.add(check);
            visited[check] = true;
        }
        if (queue.isEmpty()) return;
        answers.put(count, list);
        count++;
        while (!queue.isEmpty()) {
            recur(queue.poll());
        }
        count--;
    }


}

