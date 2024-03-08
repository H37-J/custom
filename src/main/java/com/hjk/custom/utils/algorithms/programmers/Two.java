package com.hjk.custom.utils.algorithms.programmers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class Two {

    private Long answer = 0L;
    private int call = 0;
    private int total = 0;

    public static void main(String... args) {
        var two = new Two();
        long startTime = System.currentTimeMillis();

//        two.solution26(new int[]{1, 3, 2}, new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"});
//        two.solution27(new String[][]{{"02:40", "04:10"},{"01:44", "02:20"},{"00:00","00:30"}, {"00:40","01:40"}, {"01:40", "02:20"}, {"01:50", "02:20"}, {"02:30", "02:40"}});
//        two.solution29(new int[]{9,1,5,3,6,2});

        two.solution38(new int[][]{{1, 0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,0}, {0,0,0,0,1}});
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Execution time: " + duration + "ms");
    }

    //게임 맵 최단거리
    // maps	answer
    // [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]	11
    // [[1,1], [1,1]]'/
    // [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]]	-1
    public int solution38(int[][] maps) {
        AtomicInteger answer = new AtomicInteger(Integer.MAX_VALUE);
        int x = 0, y = 0;
        int n = maps[0].length, m = maps.length;
        List<List<Integer>> list = new ArrayList<>();
        Boolean[][] visited = new Boolean[m][n];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                visited[i][j] = false;
            }
        }
        visited[0][0] = true;

        recursive(maps, x, y, maps[0].length, maps.length, list, visited, answer, 0);
        return answer.get() == Integer.MAX_VALUE ? -1 : answer.get();
    }

    public void recursive(int[][] maps, int x, int y, int n, int m, List<List<Integer>> list, Boolean[][] visited, AtomicInteger answer, int sum) {
        if (x + 1 == n && y + 1 == m) {
            if(answer.get() > sum) {
                answer.getAndSet(sum + 1 );
            }
            return;
        }

        var right = x + 1;
        var left = x - 1;
        var down = y + 1;
        var up = y - 1;

        if (right < n && !visited[y][right] && maps[y][right] == 1) {
            visited[y][right] = true;
            sum++;
            recursive(maps, right, y, n, m, list, visited, answer, sum);
            sum--;
            visited[y][right] = false;
        }
        if (down < m && !visited[down][x] && maps[down][x] == 1) {
            visited[down][x] = true;
            sum++;
            recursive(maps, x, down, n, m, list, visited, answer, sum);
            sum--;
            visited[down][x] = false;
        }
        if (left >= 0 && !visited[y][left] && maps[y][left] == 1) {
            visited[y][left] = true;
            sum++;
            recursive(maps, left, y, n, m, list, visited, answer, sum);
            sum--;
            visited[y][left] = false;
        }
        if (up >= 0 && !visited[up][x] && maps[up][x] == 1) {
            visited[up][x] = true;
            sum++;
            recursive(maps, x, up, n, m, list, visited, answer, sum);
            sum--;
            visited[up][x] = false;
        }
    }


    //피로도
    // k	dungeons	result
    //80	[[80,20],[50,40],[30,10]]	3
    public int solution37(int k, int[][] dungeons) {
        List<Integer> list = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        Boolean[] visited = new Boolean[dungeons.length];
        Arrays.fill(visited, false);
        AtomicInteger answer = new AtomicInteger(0);
        combination(k, dungeons, list, answer, visited, index);
        return answer.get();
    }

    public void combination(int current, int[][] dungeons, List<Integer> list, AtomicInteger answer, Boolean[] visitied, List<Integer> index) {
        if (Arrays.stream(visitied).filter(val -> val).toArray().length == visitied.length) {
            if (answer.get() < list.size()) {
                answer.getAndSet(list.size());
            }
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (visitied[i]) continue;
            visitied[i] = true;
            boolean check = true;
            var min = dungeons[i][0];
            var val = dungeons[i][1];
            index.add(i);
            if (current < min) {
                check = false;
            } else {
                current -= val;
                list.add(val);
            }
            combination(current, dungeons, list, answer, visitied, index);
            if (check) {
                list.remove(list.size() - 1);
                current += val;
            }
            index.remove(index.size() - 1);

            visitied[i] = false;
        }
    }

    //혼자 놀기의 달인
    //cards	result
    //[8,6,3,7,2,5,1,4]	12
    public int solution36(int[] cards) {
        int answer = 0;
        List<List<Integer>> list = new ArrayList<>();
        AtomicInteger result = new AtomicInteger(0);
        recursive(cards, 0, 0, list, result);
        return answer;
    }

    public void recursive(int[] cards, int index, int sum, List<List<Integer>> list, AtomicInteger result) {
        if (sum == cards.length || sum == cards.length - 1) {
            int num = 1;
            print(list);
            print(sum);
            for (int i = 0; i < list.size(); i++) {
                var val = list.get(i);
                num *= val.size();
            }
            var max = result.get();
            if (max < num) {
                result.getAndSet(num);
            }
            list.clear();
            return;
        }

        for (int i = index; i < cards.length; i++) {
            var tempList = new ArrayList<Integer>();
            var tempMap = new HashMap<Integer, Integer>();
            if (cards[i] == -1 || cards[i] - 1 == i) continue;
            var next = cards[i] - 1;
            cards[i] = -1;
            tempList.add(next + 1);
            tempMap.put(i, next + 1);
            sum++;
            while (cards[next] != -1) {
                if (cards[next] == -1) break;
                var tempIndex = next;
                tempMap.put(next, cards[next]);
                next = cards[next] - 1;
                tempList.add(next + 1);
                sum++;
                cards[tempIndex] = -1;
            }
            list.add(tempList);
            recursive(cards, index + 1, sum, list, result);
            sum -= tempList.size();
            for (var entry : tempMap.entrySet()) {
                cards[entry.getKey()] = entry.getValue();
            }
        }
    }


    //택배 상자
    //order	result
    //[4, 3, 1, 2, 5]	2
    //[5, 4, 3, 2, 1]	5
    public int solution35(int[] order) {
        int answer = 0;
        var main = new LinkedList<Integer>();
        var sub = new Stack<Integer>();
        for (int i = 1; i <= order.length; i++) {
            if (i == 1) sub.push(i);
            else main.add(i);
        }
        for (int i = 0; i < order.length; i++) {
            var target = order[i];
            if (!main.isEmpty() && target == main.peek()) {
                main.poll();
                answer++;
            } else if (!sub.isEmpty() && target == sub.peek()) {
                sub.pop();
                answer++;
            } else if (!main.isEmpty()) {
                sub.push(main.poll());
                i = i - 1;
            } else if (sub.peek() != target) {
                break;
            }
        }
        return answer;
    }

    //귤 고르기
    //  k	tangerine	result
    // 6	[1, 3, 2, 5, 4, 5, 2, 3]	3
    // 4	[1, 3, 2, 5, 4, 5, 2, 3]	2
    // 2	[1, 1, 1, 1, 2, 2, 2, 3]	1
    public int solution34(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }
        for (var entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        list = list.stream().sorted((a, b) -> b - a).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            k -= list.get(i);
            answer++;
            if (k <= 0) {
                break;
            }
        }
        return answer;
    }

    //디펜스 게임
    //n	k	enemy	result
    // 7	3	[4, 2, 4, 5, 3, 3, 1]	5
    // 2	4	[3, 3, 3, 3]	4
    public int solution33(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < enemy.length; i++) {
            var current = enemy[i];
            queue.add(current);
            n -= current;
            if (n < 0 && k == 0) break;
            if (n < 0) {
                for (int j = 0; j < k; j++) {
                    n += queue.poll();
                    if (n >= 0) {
                        k = k - j - 1;
                        break;
                    }
                }
            }
            answer++;
        }
        return answer;
    }

    //마법의 엘리베이터
    //storey	result
    //16	6
    // 2554	16
    public int solution32(int storey) {
        int answer = 0;
        return answer;
    }

    public int solution31(int x, int y, int n) {
        if (x == y) return 0;
        if (x % 2 == 0 && y % 2 == 1 && n % 2 == 0) return -1;
        int count = 0;
        var queue = new LinkedList<Integer>();
        queue.add(y);
        while (!queue.isEmpty()) {
            int find = -1;
            var size = queue.size();
            for (int i = 0; i < size; i++) {
                var target = queue.poll();
                var one = target / 2.0F;
                var two = target / 3.0F;
                var three = target - n;
                if (one == x || two == x || three == x) {
                    find = 1;
                    break;
                }
                if (one > x || two > x || three > x) {
                    find = 0;
                }
                if (one == (int) one) queue.add((int) one);
                if (two == (int) two) queue.add((int) two);
                if (three == (int) three) queue.add((int) three);
            }
            count++;
            if (find == 1) {
                break;
            } else if (find == -1) {
                count = 0;
                break;
            }
        }
        return count == 0 ? -1 : count;
    }

    //숫자 변환하기
    //x	y	n	result
    //10	40	5	2
    //10	40	30	1
    //2	5	4	-1
    public int solution30(int x, int y, int n) {
        if (x % 2 == 0 && n % 2 == 0 && y % 2 == 1) return -1;
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        permutation(x, y, n, 0, min);
        print(min.get());
        return min.get() == Integer.MAX_VALUE ? -1 : min.get();
    }

    public void permutation(int x, float y, int n, int count, AtomicInteger min) {
        if (x > y || count > min.get()) {
            return;
        }
        if (x == y) {
            if (count < min.get()) min.getAndSet(count);
            return;
        }

        for (int i = 0; i <= 2; i++) {
            var prev = y;

            switch (i) {
                case 0 -> y /= 2;
                case 1 -> y /= 3;
                case 2 -> y = y - n;
            }
            if (y != (int) y) {
                y = prev;
                continue;
            }
            permutation(x, y, n, count + 1, min);
            y = prev;
        }
    }

    //뒤에있는 큰 수 찾기
    //numbers	result
    //[2, 3, 3, 5]	[3, 5, 5, -1]
    //[9, 1, 5, 3, 6, 2]	[-1, 5, 6, 6, -1, -1]
    public int[] solution29(int[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            var target = numbers[i];
            for (int j = i + 1; j < numbers.length; j++) {
                var compare = numbers[j];
                if (target < compare) {
                    answer[i] = compare;
                    break;
                }
            }
            if (answer[i] == 0) answer[i] = -1;
        }
        print(answer);
        return answer;
    }

    //무인도 여행
    //maps	result
    //["X591X","X1X5X","X231X", "1XXX1"]	[1, 1, 27]
    //["XXX","XXX","XXX"]	[-1]
    public int[] solution28(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'X' || visited[i][j]) continue;
                visited[i][j] = true;
                var list = new ArrayList<Integer>();
                answer.add(recursion(maps, i, j, list, visited));
            }
        }
        return answer.isEmpty() ? new int[]{-1} : answer.stream().sorted().mapToInt(i -> i).toArray();
    }

    public int recursion(String[] maps, int x, int y, List<Integer> list, boolean[][] visited) {
        var currentX = maps[x];
        if (x - 1 != -1 && !visited[x - 1][y] && maps[x - 1].charAt(y) != 'X') {
            visited[x - 1][y] = true;
            recursion(maps, x - 1, y, list, visited);
        }
        if (x + 1 < maps.length && !visited[x + 1][y] && maps[x + 1].charAt(y) != 'X') {
            visited[x + 1][y] = true;
            recursion(maps, x + 1, y, list, visited);
        }
        if (y - 1 != -1 && !visited[x][y - 1] && currentX.charAt(y - 1) != 'X') {
            visited[x][y - 1] = true;
            recursion(maps, x, y - 1, list, visited);
        }
        if (y + 1 < maps[0].length() && !visited[x][y + 1] && currentX.charAt(y + 1) != 'X') {
            visited[x][y + 1] = true;
            recursion(maps, x, y + 1, list, visited);
        }
        list.add(Integer.parseInt(String.valueOf(currentX.charAt(y))));

        return list.stream().mapToInt(i -> i).sum();
    }


    // 호텔 대실(미해결)
    //[["08:00", "08:30"], ["08:00", "13:00"], ["12:30", "13:30"]]
    //[["15:00", "17:00"], ["16:40", "18:20"], ["14:20", "15:20"], ["14:10", "19:20"], ["18:20", "21:20"]]	3
    //[["09:10", "10:10"], ["10:20", "12:20"]]	1
    //[["10:20", "12:30"], ["10:20", "12:30"], ["10:20", "12:30"]]3
    public int solution27(String[][] book_time) {
        int answer = 0;
        List<Time> times = new LinkedList<>();
        for (var val : book_time) {
            var startTime = Integer.parseInt(val[0].replace(":", ""));
            var endTime = Integer.parseInt(val[1].replace(":", ""));
            times.add(new Time(startTime, endTime + 10));
        }

        while (!times.isEmpty()) {
            List<Time> sets = new ArrayList<>();
            sets.add(times.get(0));
            for (int j = 1; j < times.size(); j++) {
                var compare = times.get(j);
                boolean check = true;
                for (var set : sets) {
                    if (!(set.endTime <= compare.startTime || compare.endTime <= set.startTime)) {
                        check = false;
                    }
                }
                if (check) {
                    sets.add(times.get(j));
                    times.remove(j);
                    j = j - 1;
                }
            }
            print(sets);
            times.remove(0);
            answer++;
        }
        print(answer);
        return answer;
    }

    public static class Time {
        int startTime;
        int endTime;

        public Time(int startTime, int endTime) {
            this.startTime = startTime;
            if (endTime % 100 >= 60) {
                this.endTime = ((endTime / 100 + 1) * 100) + endTime % 10;
            } else {
                this.endTime = endTime;
            }
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public String toString() {
            return startTime + "," + endTime;
        }
    }

    //광물캐기(미해결)
    //picks	minerals	result
    //[1, 3, 2]	["diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"]	12
    //[0, 1, 1]	["diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"]	50
    public int solution26(int[] picks, String[] minerals) {
        int answer = 0;
        Queue<String> queue = new LinkedList<>(Arrays.asList(minerals));


        return answer;
    }

    public int permutation(int[] picks, Queue<String> queue, int tired) {
        if (queue.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < picks.length; i++) {
            if (picks[i] == 0) continue;
            Queue<String> temp = new LinkedList<>(queue);
            var original = picks[i];
            var originalTired = tired;
            picks[i] = picks[i] - 1;
            var count = 5;
            while (!queue.isEmpty() && count != 0) {
                if (i == 0) {
                    tired++;
                } else if (i == 1) {
                    if (queue.peek().equals("diamond")) {
                        tired += 5;
                    } else {
                        tired++;
                    }
                } else if (i == 2) {
                    if (queue.peek().equals("diamond")) {
                        tired += 25;
                    } else if (queue.peek().equals("iron")) {
                        tired += 5;
                    } else {
                        tired++;
                    }
                }
                queue.poll();
                count--;
            }
            if (!queue.isEmpty() && picks[i] != 0) {
                i = i - 1;
                print(i);
                continue;
            }
            permutation(picks, queue, tired);
            if (queue.isEmpty()) {
                print(tired);
                print(picks);
            }
            picks[i] = original;
            tired = originalTired;
            queue.clear();
            queue.addAll(temp);
        }
        return tired;
    }

    //멀리 뛰기(미해결)
    // n	result
    //4	5
    //3	3
    public long solution25(int n) {
//        AAAA BAA ABA AAB BB AAAA
//                AAAAA BBA ABB BAB
        String str = "AAAAA";
        var set = new HashSet<String>();
        print(set);
        return answer;
    }

    public void permutation(String str, int count, Set<String> set, String find, boolean[] visited, int start) {

    }

    public void permutation(int sum, List<Integer> list) {
        if (total > sum) {
            return;
        }
        if (total == sum) {
            answer++;
            print(list);
            return;
        }

        for (int i = 2; i >= 1; i--) {
            total += i;
            list.add(i);
            permutation(sum, list);
            total -= i;
            list.remove(list.size() - 1);
        }
    }

    //숫자의 표현
    // n	result
    //15	4
    public int solution24(int n) {
        int answer = 1;
        for (int i = 1; i <= n; i++) {
            var sum = i;
            for (int j = i + 1; j <= n; j++) {
                sum += j;
                if (sum == n) {
                    answer++;
                    break;
                }
                if (sum > n) {
                    break;
                }
            }
        }

        return answer;
    }

    public int[] solution23(int n, Long k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(n);
        }
        String str = "";
        int answer[] = new int[n];
        while (n != 1) {
            int num = getFactorial(--n);
            var temp = (int) Math.ceil((double) k / num);
            k = (long) Math.floor((double) k % num);
            print(temp, k, num);
        }
        print(str);
        return answer;
    }

    public int getFactorial(int n) {
        if (n == 1) return 1;
        return n * getFactorial(n - 1);
    }

    //줄 서는 방법(미해결)
    //n	k	result
    //3	5	[3,1,2]
    public int[] solution22(int n, long k) {
        int[] arr = new int[n];
        boolean[] visited = new boolean[n];
        var list = new ArrayList<Integer>();
        var res = new ArrayList<List<Integer>>();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        permutation(arr, visited, list, res, n, (int) k);
        var result = res.get((int) k - 1);
        print(result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    public void permutation(int[] arr, boolean[] visited, List<Integer> list, List<List<Integer>> res, int n, int k) {
        if (list.size() == n) {
            res.add(new ArrayList<>(list));
            print(res);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            ;
            visited[i] = true;
            list.add(arr[i]);
            permutation(arr, visited, list, res, n, k);
            if (res.size() == k) return;
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }


    //피보나치 수
    //n	return
    //3	2
    //5	5
    public Long solution21(int n, List<Long> list) {
        for (int i = 2; i <= n; i++) {
            list.add((list.get(i - 1) + list.get(i - 2)) % 1234567);
        }
        return list.get(n);
    }

    //최대값과 최소값
    //s	return
    // "1 2 3 4"	"1 4"
    // "-1 -2 -3 -4"	"-4 -1"
    // "-1 -1"	"-1 -1"
    public String solution20(String s) {
        String answer = "";
        var max = Integer.MIN_VALUE;
        var min = Integer.MAX_VALUE;
        for (var val : s.split(" ")) {
            if (max < Integer.parseInt(val)) {
                max = Integer.parseInt(val);
            }
            if (min > Integer.parseInt(val)) {
                min = Integer.parseInt(val);
            }
        }
        answer = min + " " + max;
        return answer;
    }

    //최소 값 만들기
    //A	B	answer
    //[1, 4, 2]	[5, 4, 4]	29
    //[1,2]	[3,4]	10
    public int solution19(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[B.length - i - 1];
        }
        return answer;
    }

    //짝지어 제거하기
    //s	result
    //baabaa	1
    //cdcd	0
    public int solution18(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty()) {
                if (stack.peek() == s.charAt(i)) {
                    stack.pop();
                    continue;
                }
            }
            stack.push(s.charAt(i));
        }

        return stack.isEmpty() ? 1 : 0;
    }

    //최소 공배수
    //arr	result
    //[2,6,8,14]	168
    //[1,2,3]	6
    public int solution17(int[] arr) {
        return Arrays.stream(arr).reduce(this::lcm).getAsInt();
    }

    public int lcm(int val1, int val2) {
        var num1 = Math.max(val1, val2);
        var num2 = Math.min(val1, val2);

        while (num1 % num2 != 0) {
            if (num1 % num2 != 0) {
                var temp = num1;
                num1 = num2;
                num2 = temp % num2;
            } else if (num1 % num2 == 0) {
                break;
            }
        }
        return val1 * val2 / num2;
    }

    //다음 큰 숫자
    //n	result
    //78	83
    //15	23
    public int solution16(int n) {
        int count = 0;
        int check;
        var copy = n;
        while (n != 0) {
            if (n % 2 == 1) {
                count++;
            }
            n /= 2;
        }
        var current = ++copy;
        while (true) {
            check = 0;
            while (current != 0) {
                if (current % 2 == 1) {
                    check++;
                }
                current /= 2;
            }
            if (count == check) {
                current = copy;
                break;
            }
            current = ++copy;
        }
        return current;
    }

    //올바른 괄호
    // s	answer
    //"()()"	true
    //"(())()"	true
    //")()("	false
    //"(()("	false
    public boolean solution15(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty()) {
                if (stack.peek() == '(' && s.charAt(i) == ')') {
                    stack.pop();
                    continue;
                }
            }
            stack.push(s.charAt(i));
        }

        return stack.isEmpty();
    }


    //구명보트
    //people	limit	return
    //[70, 50, 80, 50]	100	3
    //[70, 80, 50]	100	3
    public int solution14(int[] people, int limit) {
        int answer = 0;
        var arr = Arrays.stream(people).sorted().toArray();
        var start = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            print(start, i);
            if (arr[i] + arr[start] <= limit) {
                start++;
            }
            answer++;
            if (start >= i) break;
        }
        print(answer);

        return answer;
    }

    //큰 수 만들기
    //number	k	return
    //"1924"	2	"94"
    //"1231234"	3	"3234"
    //"4177252841"	4	"775841"
    public String solution13(String number, int k) {
        var answer = new StringBuilder();
        var arr = Arrays.stream(number.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < arr.length; i++) {
            var target = arr[i];
            var prev = k;
            if (k == 0 && i + 1 == arr.length) {
                answer.append(arr[i]);
                break;
            } else if (k == 1 && i + 1 == arr.length) {
                break;
            }
            for (int j = i + 1; j < arr.length; j++) {
                var compare = arr[j];
                if ((target < compare && j - i <= k) || (arr.length - i <= k)) {
                    k--;
                    break;
                }
            }
            if (prev == k) answer.append(arr[i]);
        }
        return answer.toString();
    }

    //소수 찾기
    //numbers	return
    //"17"	3
    //"011"	2
    public int solution12(String numbers) {
        int answer = 0;
        var set = new HashSet<String>();
        var arr = numbers.split("");
        boolean[] visted = new boolean[arr.length];
        for (int i = 1; i <= numbers.length(); i++) {
            set = combination(set, arr, visted, i, "");
        }
        answer = set.size();
        for (var val : set) {
            var check = Integer.parseInt(val);
            if (check == 1 || check == 0) answer--;
            for (int i = 2; i <= Math.sqrt(check); i++) {
                if (check % i == 0) {
                    answer--;
                    break;
                }
            }
        }
        return answer;
    }

    public static HashSet<String> combination(HashSet<String> set, String[] arr, boolean[] visited, int count, String str) {
        if (count == 0) {
            while (str.length() != 1 && str.charAt(0) == '0' && !str.replaceFirst("0", "").equals(str)) {
                str = str.replaceFirst("0", "");
            }
            set.add(str);
            return set;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            str += arr[i];
            combination(set, arr, visited, count - 1, str);
            visited[i] = false;
            str = str.substring(0, str.length() - 1);
        }

        return set;
    }

    //전화번호 목록
    //phone_book	return
    //["119", "97674223", "1195524421"]	false
    //["123","456","789"]	true
    //["12","123","1235","567","88"]	false
    public boolean solution11(String[] phone_book) {
        boolean answer = true;
        HashMap<String, String> map = new HashMap<>();
        for (var phone : phone_book) {
            map.put(phone, phone);
        }
        for (var phone : phone_book) {
            var compare = "";
            for (int i = 0; i < phone.length(); i++) {
                compare += phone.charAt(i);
                if (phone.length() == i + 1) break;
                var check = map.getOrDefault(compare, "Not Found");
                if (!check.equals("Not Found")) {
                    answer = false;
                    break;
                }
            }
            if (!answer) break;
        }
        return answer;
    }

    //H-Index
    //citations	return
    //[3, 0, 6, 1, 5]	3
    public int solution10(int[] citations) {
        int answer = 0;
        for (int i = 0; i <= 10000; i++) {
            var H = i;
            var min = 0;
            var max = 0;
            for (int j = 0; j < citations.length; j++) {
                var compare = citations[j];
                if (H == compare) {
                    min++;
                    max++;
                } else if (H > compare) {
                    min++;
                } else if (H < compare) {
                    max++;
                }
            }
            if (H >= min && H <= max && H > answer) {
                answer = H;
            }
        }
        return answer;
    }


    //가장 큰 수
    //numbers	return
    //[6, 10, 2]	"6210"
    //[3, 30, 34, 5, 9]	"9534330"
    public String solution9(int[] numbers) {
        String answer = "";
        String[] res = Arrays.stream(numbers)
                .boxed()
                .map(String::valueOf)
                .sorted((v1, v2) -> {
                    var val1 = v1 + v2;
                    var val2 = v2 + v1;
                    return val2.compareTo(val1);
                })
                .toArray(String[]::new);
        for (var val : res) {
            answer += val;
        }
        while (answer.length() != 1 && answer.charAt(0) == '0' && !answer.replaceFirst("0", "").equals(answer)) {
            answer = answer.replaceFirst("0", "");
        }
        print(answer);
        return answer;
    }

    // 더 맵게
    //scoville	K	return
    //[1, 2, 3, 9, 10, 12]	7	2
    public int solution8(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((v1, v2) -> v1 - v2);
        for (var val : scoville) {
            queue.add(val);
        }
        while (queue.size() >= 2 && queue.peek() < K) {
            var val1 = queue.poll();
            var val2 = queue.poll();
            var check = val1 < val2 ? val1 + (val2 * 2) : val2 + (val1 * 2);
            queue.add(check);
            answer++;
        }
        return queue.peek() < K ? -1 : answer;
    }

    //위장(솔루션2)
    public int solution7(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        for (var clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }
        for (var entry : map.entrySet()) {
            answer *= entry.getValue() + 1;
        }
        return answer - 1;
    }

    //위장
    //clothes	return
    //[["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]]	5
    //[["crow_mask", "face"], ["blue_sunglasses", "face"], ["smoky_makeup", "face"]]	3 //1번 실패
    public int solution6(String[][] clothes) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        List<Integer> category = new ArrayList<>();
        for (var clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }
        for (var entry : map.entrySet()) {
            answer += entry.getValue();
            category.add(entry.getValue());
        }
        var combination = 0;
        for (int i = 2; i <= map.size(); i++) {
            combination += combination(category, new ArrayList<>(), i, 0);
        }
        answer += combination;
        print(answer);
        return answer;
    }

    public static int combination(List<Integer> category, List<Integer> picks, int count, int current) {
        if (picks.size() == count) {
            return picks.stream().reduce((v1, v2) -> v1 * v2).get();
        }

        int sum = 0;
        for (int i = current; i < category.size(); i++) {
            var pick = category.get(i);
            picks.add(pick);
            sum += combination(category, picks, count, i + 1);
            picks.remove(pick);
        }
        return sum;
    }

    //기능 개발
    //progresses	speeds	return
    //[93, 30, 55]	[1, 30, 5]	[2, 1]
    //[95, 90, 99, 99, 80, 99]	[1, 1, 1, 1, 1, 1]	[1, 3, 2]
    public int[] solution5(int[] progresses, int[] speeds) {
        int[] answer = new int[progresses.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            var remGress = (100 - progresses[i]) % speeds[i] == 0 ? (100 - progresses[i]) / speeds[i] : (100 - progresses[i]) / speeds[i] + 1;
            queue.add(remGress);
        }
        var index = 0;
        while (!queue.isEmpty()) {
            var target = queue.poll();
            var count = 1;
            if (queue.isEmpty()) {
                answer[index] = 1;
                break;
            }
            while (!queue.isEmpty() && target >= queue.peek()) {
                count++;
                queue.poll();
            }
            answer[index] = count;
            index++;
        }
        answer = Arrays.stream(answer).filter(val -> val != 0).toArray();
        return answer;
    }

    //주식가격
    //prices	return
    //[1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
    public int[] solution4(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            var target = prices[i];
            var count = 1;
            for (int j = i + 1; j < prices.length; j++) {
                var compare = prices[j];
                if (target > compare) {
                    answer[i] = count;
                    break;
                }
                count++;
            }
            if (answer[i] == 0) {
                answer[i] = count - 1;
            }
        }
        return answer;
    }

    //프로세스
    // priorities	location	return
    // [2, 1, 3, 2]	2	1
    // [1, 1, 9, 1, 1, 1]	0	5
    public int solution3(int[] priorities, int location) {
        int answer = 0;
        var size = priorities.length - 1;
        Queue<Process> processes = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            processes.add(new Process(priorities[i], i));
        }

        while (!processes.isEmpty()) {
            var val = processes.peek().getPriority();
            var max = processes.stream().map(Process::getPriority).max(Integer::compare).get();
            if (val < max) {
                processes.add(processes.poll());
            } else {
                var check = processes.poll();
                size -= 1;
                answer++;
                if (check.getIndex() == location) break;
            }
        }
        return answer;
    }

    public static class Process {
        int priority;
        int index;

        public Process(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }

        public int getPriority() {
            return this.priority;
        }

        public int getIndex() {
            return this.index;
        }
    }

    // 다리를 지나는 트럭
//    0	[]	[]	[7,4,5,6]
//            1~2	[]	[7]	[4,5,6]
//            3	[7]	[4]	[5,6]
//            4	[7]	[4,5]	[6]
    //    bridge_length	weight	truck_weights	 return
    //     2	10	[7,4,5,6]	8
    //     100	100	[10]	101
    //     100	100	[10,10,10,10,10,10,10,10,10,10]	110
    public int solution2(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        AtomicInteger totalWeight = new AtomicInteger();
        totalWeight.addAndGet(truck_weights[0]);
        Queue<Integer> trucks = Arrays.stream(truck_weights).boxed().collect(Collectors.toCollection(LinkedList::new));
        Queue<Truck> on = new LinkedList<>(Arrays.asList(new Truck(trucks.poll(), 1)));
        while (!on.isEmpty()) {
            on = on.stream().map(Truck::updateTime).collect(Collectors.toCollection(LinkedList::new));
            if (on.peek().getTime() == bridge_length + 1) {
                totalWeight.addAndGet(-on.peek().getWeight());
                on.poll();
            }
            if (!trucks.isEmpty() && trucks.peek() + totalWeight.get() <= weight) {
                totalWeight.addAndGet(trucks.peek());
                on.add(new Truck(trucks.poll(), 1));
            }
            answer++;
        }
        return answer;
    }

    public static class Truck {
        int weight;
        int time;

        private Truck(int weight, int time) {
            this.weight = weight;
            this.time = 1;
        }

        private Truck updateTime() {
            this.time += 1;
            return this;
        }

        private int getTime() {
            return this.time;
        }

        private int getWeight() {
            return this.weight;
        }
    }

    //스킬트리
    //skill	skill_trees	return
    //"CBD"	["BACDE", "CBADF", "AECB", "BDA"]	2
    public int solution1(String skill, String[] skill_trees) {
        int answer = 0;
        for (var s : skill_trees) {
            s = s.replaceAll("[^" + skill + "]", "");
            if (skill.indexOf(s) == 0)
                answer++;
        }

        return answer;
    }


}

