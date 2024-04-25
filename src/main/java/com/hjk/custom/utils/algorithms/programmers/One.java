package com.hjk.custom.utils.algorithms.programmers;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class One<T> {

    public static void main(String... args) {
        var one = new One();
        one.t68(3,20,4);
    }

    // 부족한 금액 계산하기
    //price	money	count	result
    //3	20	4	10
    public long t68(int price, int money, int count) {
        long answer = -1;
        long total = 0;
        for(int i = 1; i <= count; i++) {
            var next = price * i;
            total += next;
        }
        return money > total ? 0 : total - money;
    }

    //3진법 뒤집기
//    n	result
//45	7
// 125	229
    public int t67(int n) {
        int answer = 0;
        var str = new StringBuilder();
        while(n != 0) {
            str.append(n % 3);
            n = n / 3;
        }
        for(int i = str.toString().length() - 1, j = 0; i >= 0 && j  < str.toString().length(); i--, j++) {
            int num =  str.toString().charAt(j) - '0';
            answer += (int) (Math.pow(3, i) * num);
        }
        return answer;
    }

    //숫자 짝궁
//    X	Y	result
//"100"	"2345"	"-1"
// "100"	"203045"	"0"
// "100"	"123450"	"10"
// "12321"	"42531"	"321"
// "5525"	"1255"	"552"
    public String t66(String X, String Y) {
        String answer = "";
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (var val : X.split("")) {
            map1.put(val, map1.getOrDefault(val, 0) + 1);
        }
        for (var val : Y.split("")) {
            map2.put(val, map2.getOrDefault(val, 0) + 1);
        }
        print(map1, map2);
        for (int i = 0; i <= 9; i++) {
            var val1 = map1.getOrDefault(String.valueOf(i), 0);
            var val2 = map2.getOrDefault(String.valueOf(i), 0);
            if (val1 > 0 && val2 > 0 && val1.compareTo(val2) > 0) {
                answer += String.valueOf(i).repeat(val2);
            } else if (val1 > 0 && val2 > 0 && val1.compareTo(val2) <= 0) {
                answer += String.valueOf(i).repeat(val1);
            }
        }
        if (answer.isEmpty()) {
            return "-1";
        }
        var res = Arrays.stream(answer.split("")).sorted((v1, v2) -> v2.compareTo(v1)).collect(Collectors.joining(""));
        while (res.length() != 1 && res.charAt(0) == '0') {
            res = res.replaceFirst("0", "");
        }
        return res;
    }

    //햄버거 만들기
//    ingredient	result
//[2, 1, 1, 2, 3, 1, 2, 3, 1]	2
//        [1, 3, 2, 1, 2, 1, 3, 1, 2]	0
    public int t65(int[] ingredient) {
        int answer = 0;
        Stack<String> stack = new Stack<>();
        for (var val : ingredient) {
            stack.push(String.valueOf(val));
            if (stack.size() >= 4 && stack.peek().equals("1")) {
                var check = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    check.append(stack.pop());
                }
                if (check.toString().equals("1321")) {
                    answer++;
                } else {
                    for (var prev : check.reverse().toString().split("")) {
                        stack.push(prev);
                    }
                }
            }
        }
        return answer;
    }

    //기사단원 무기
//    5	3	2	10
//            10	3	2	21
    public int t64(int number, int limit, int power) {
        int answer = 0;
        int[] arr = new int[number];
        for (int i = 1; i <= number; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int num = i;
            int divide = 2;
            while (num != 1) {
                if (num % divide == 0) {
                    map.put(divide, map.getOrDefault(divide, 0) + 1);
                    num /= divide;
                } else {
                    divide++;
                }
            }
            int sum = 1;
            for (var entry : map.entrySet()) {
                sum *= entry.getValue() + 1;
            }
            arr[i - 1] = sum;
        }
        for (var val : arr) {
            if (val > limit) {
                answer += power;
            } else {
                answer += val;
            }
        }
        return answer;
    }

    //명예의 전당
//    k	score	result
//3	[10, 100, 20, 150, 1, 100, 200]	[10, 10, 10, 20, 20, 100, 100]
//        4	[0, 300, 40, 300, 20, 70, 150, 50, 500, 1000]	[0, 0, 0, 0, 20, 40, 70, 70, 150, 300]
    public int[] t63(int k, int[] score) {
        int[] answer = new int[score.length];
        int index = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((v1, v2) -> v1 - v2);
        for (var s : score) {
            if (queue.size() == k && queue.peek() < s) {
                queue.poll();
                queue.add(s);
            } else if (queue.size() == k && queue.peek() >= s) {

            } else {
                queue.add(s);
            }
            answer[index] = queue.peek();
            index++;
        }
        print(answer);
        return answer;
    }

    //문자열 나누기
//    s	result
//"banana"3
//  "abracadabra"6
//  "aaabbaccccabba"3
    public int t62(String s) {
        int answer = 0;

        while (!s.isEmpty()) {
            var first = s.charAt(0);
            var same = 1;
            var other = 0;
            for (int i = 1; i < s.length(); i++) {
                if (first == s.charAt(i)) {
                    same++;
                } else {
                    other++;
                }
                if (same == other) {
                    s = s.substring(i + 1);
                    answer++;
                    break;
                }
            }
            if (same != other) {
                answer++;
                break;
            }
        }
        return answer;
    }

    //가장 가까운 글자
//    s	result
//"banana"	[-1, -1, -1, 2, 2, 2]
//        "foobar"	[-1, -1, 1, -1, -1, -1]
    public int[] t61(String s) {
        int[] answer = new int[s.length()];
        int index = 0;
        Map<String, Integer> map = new HashMap<>();
        for (var str : s.split("")) {
            var val = map.getOrDefault(str, -1);
            if (val == -1) {
                answer[index] = -1;
            } else {
                answer[index] = index - val;
            }
            map.put(str, index);
            index++;
        }
        return answer;
    }

    //크기가 작은 부분 문자열
//    t	p	result
//"3141592"	"271"	2
// "500220839878"	"7"	8
// "10203"	"15"	3
    public int t60(String t, String p) {
        int answer = 0;
        int index = 0;
        int size = p.length();
        int length = t.length();
        while (index + size <= length) {
            var str = t.substring(index, index + size);
            index += 1;
            if (Long.parseLong(str) <= Long.parseLong(p)) {
                print(str);
                answer += 1;
            }
        }
        return answer;
    }

    //둘만의 암호
//    s	skip	index	result
//"aukks"	"wbqd"	5	"happy"
    public String t59(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        String dic = "abcdefghijklmnopqrstuvwxyz";
        for (var str : skip.split("")) {
            dic = dic.replace(str, "");
        }
        for (var word : s.split("")) {
            var toIndex = dic.indexOf(word) + index >= dic.length()
                    ? (dic.indexOf(word) + index) % dic.length()
                    : dic.indexOf(word) + index;
            answer.append(dic.split("")[toIndex]);
        }
        return answer.toString();
    }

    // 카드뭉치(해결)
//    cards1	cards2	goal	result
//["i", "drink", "water"]	["want", "to"]	["i", "want", "to", "drink", "water"]	"Yes"
//    ["i", "water", "drink"]	["want", "to"]	["i", "want", "to", "drink", "water"]	"No"
    public String t58(String[] cards1, String[] cards2, String[] goals) {
        String answer = "";
        Queue<String> card1 = new LinkedList<>(Arrays.asList(cards1));
        Queue<String> card2 = new LinkedList<>(Arrays.asList(cards2));
        Queue<String> goal = new LinkedList<>(Arrays.asList(goals));

        while (!goal.isEmpty()) {
            var word = goal.poll();
            var check = false;
            if (!card1.isEmpty() && card1.peek().equals(word)) {
                card1.poll();
                check = true;
            }
            if (!card2.isEmpty() && card2.peek().equals(word)) {
                card2.poll();
                check = true;
            }
            if (!check) return "No";
        }
        return "Yes";
    }

    //대충만든 자판(해결)
//    keymap	targets	result
//["ABACD", "BCEFD"]	["ABCD","AABB"]	[9, 4]
//["AA"]	["B"]	[-1]
//["AGZ", "BSSS"]	["ASA","BGZ"]	[4, 6]
    public int[] t57(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        int num = 0;
        for (var s : targets) {
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                var target = s.charAt(i);
                var index = 102;
                for (var key : keymap) {
                    for (int j = 0; j < key.length(); j++) {
                        if (target == key.charAt(j) && j + 1 < index) {
                            index = j + 1;
                        }
                    }
                }
                if (index == 102) {
                    sum = -1;
                    break;
                } else {
                    sum += index;
                }
            }
            answer[num] = sum;
            num++;
        }
        return answer;
    }

    //덧칠하기 (해결)
//    8	4	[2, 3, 6]	2
//    5	4	[1, 3]	1
//    4	1	[1, 2, 3, 4]	4
    public int t56(int n, int m, int[] section) {
        int answer = 0;
        int to = 0;
        for (int i = 0; i < section.length; i++) {
            if (to < section[i]) {
                to = section[i] + m - 1;
                answer++;
            }
        }
        return answer;
    }

    //공원 산책 (해결)
//    park	routes	result
//["SOO","OOO","OOO"]	["E 2","S 2","W 1"]	[2,1]
// ["SOO","OXX","OOO"]	["E 2","S 2","W 1"]	[0,1]
// ["OSO","OOO","OXO","OOO"]	["E 2","S 3","W 1"]	[0,0]
    public int[] t55(String[] park, String[] routes) {
        int[] answer = new int[2];
        int currentX = 0, currentY = 0;
        int brickX = park[0].toCharArray().length - 1, brickY = park.length - 1;
        for (int i = 0; i < park.length; i++) {
            var p = park[i].toCharArray();
            for (int j = 0; j < p.length; j++) {
                if (p[j] == 'S') {
                    currentY = i;
                    currentX = j;
                }
            }
        }

        for (var route : routes) {
            var direction = route.split(" ")[0];
            var distance = route.split(" ")[1];
            var prevY = currentY;
            var prevX = currentX;
            switch (direction) {
                case "N" -> currentY -= Integer.parseInt(distance);
                case "S" -> currentY += Integer.parseInt(distance);
                case "W" -> currentX -= Integer.parseInt(distance);
                case "E" -> currentX += Integer.parseInt(distance);
            }
            if (currentY > brickY || currentY < 0 || currentX > brickX || currentX < 0) {
                currentY = prevY;
                currentX = prevX;
                continue;
            }
            if (direction.equals("S") || direction.equals("N")) {
                for (int i = Math.min(prevY, currentY); i <= Math.max(prevY, currentY); i++) {
                    var p = park[i];
                    var check = p.toCharArray()[currentX];
                    if (check == 'X') {
                        currentY = prevY;
                    }
                }
            } else {
                for (int i = Math.min(prevX, currentX); i <= Math.max(prevX, currentX); i++) {
                    var p = park[currentY];
                    var check = p.toCharArray()[i];
                    if (check == 'X') {
                        currentX = prevX;
                    }
                }
            }
            print(currentY, currentX);
        }
        print(currentY, currentX);
        answer[0] = currentY;
        answer[1] = currentX;
        return answer;
    }

    // 추억 점수 (해결)
//    name	yearning	photo	result
//["may", "kein", "kain", "radi"]	[5, 10, 1, 3]	[["may", "kein", "kain", "radi"],["may", "kein", "brin", "deny"], ["kon", "kain", "may", "coni"]]	[19, 15, 6]
    public int[] t54(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            map.put(name[i], yearning[i]);
        }
        int index = 0;
        for (var p : photo) {
            int sum = 0;
            for (var val : p) {
                sum += map.getOrDefault(val, 0);
            }
            answer[index] = sum;
            index++;
        }
        return answer;
    }

    //달리기 경주 (해결)
    public void t1() {
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine", "mine", "kai"};
        Map<String, Integer> cash = new HashMap<>();

        for (var call : callings) {
            var index = cash.getOrDefault(call, null);
            if (index != null) {
                swap(players, index - 1, index);
                cash.put(call, index - 1);
                cash.put(players[index], index);
            } else {
                for (int j = 0; j < players.length; j++) {
                    var player = players[j];
                    if (call.equals(player)) {
                        swap(players, j - 1, j);
                        cash.put(call, j - 1);
                        cash.put(players[j], j);
                        break;
                    }
                }
            }
        }
        print(players);
    }

    //약수 구하기 (해결)
    public void t2(int num) {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        System.out.println(sum);
    }

    //    가운데 글자 가져오기 (해결)
    public String t3(String s) {
        var size = s.length();
        String answer = String.valueOf(s.charAt(size / 2));
        if (size % 2 == 0) {
            answer = String.valueOf(s.charAt(size / 2 - 1)) + answer;
        }
        return answer;
    }

    //    같은 숫자는 싫어
    public void t4() {
        int[] arr = {5, 4, 0, 1, 0};
        int[] result = IntStream.range(0, arr.length)
                .filter(i -> i == 0 || arr[i - 1] != arr[i])
                .map(i -> arr[i])
                .toArray();
        Arrays.stream(result).forEach(System.out::println);
    }

    //    문자열 내 마음대로 정렬하기
    public void t5() {
        String[] str = {"abce", "abcd", "cdx"};
        int n = 2;
        var result = Arrays.stream(str).sorted((s1, s2) -> {
                    int compare = String.valueOf(s1.charAt(n)).compareTo(String.valueOf(s2.charAt(n)));
                    if (compare < 0) {
                        return -1;
                    } else if (compare > 0) {
                        return 1;
                    } else {
                        return s1.compareTo(s2);
                    }
                })
                .toArray(String[]::new);
    }

    //"pPoooyY"	true
    //"Pyy"	false
    //p와 y의ㅐ 개수
    public void t6() {
        var str = "pPoooyY";
        var sum = 0;
        for (var c : str.toCharArray()) {
            sum += c == 'p' || c == 'P' ? 1 : c == 'y' || c == 'Y' ? -1 : 0;
        }
    }

    //문자열 내림차순으로 배치하기
    public void t7() {
        var str = "acb";
        var result = str.chars().mapToObj(c -> (char) c)
                .sorted((c1, c2) -> c2.compareTo(c1))
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }

    public void t8() {
        String str = "1234a";
        var res1 = str.chars().mapToObj(c -> (char) c)
                .filter(Character::isDigit).count();
        var res2 = (str.length() == 4 || str.length() == 6) && res1 == str.length();
    }

    //    ["Jane", "Kim"]	"김서방은 1에 있다"
    public void t9() {
        String[] str = {"Jane", "Kim"};
        String answer = "";
        int index = 0;

        for (var s : str) {
            if (s.equals("Kim")) {
                answer = "김서방은 " + index + "에 있다";
            }
            index += 1;
        }
        System.out.println(answer);
    }

    //소수찾기
    public static void t10() {
        int size = 100;
        int[] arr = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            arr[i] = i;
        }
        for (int i = 2, index = 0; i < Math.sqrt(size); i++) {
            int next = 2;
            while (i * next <= size) {
                index = i * next;
                arr[index] = -1;
                next += 1;
            }
        }
        var count = Arrays.stream(arr).filter(val -> val != 0 && val != 1 && val != -1).count();
    }

    //    [3,1,2,3]	2
    //    [3,3,3,2,2,4]	3
    //    [3,3,3,2,2,2]	2
    //폰켓몬
    public void t11() {
        int[] arr = {3, 1, 2, 3};
        int size = arr.length >>> 1;

        Set<Integer> set = new HashSet<>();
        for (int val : arr) {
            set.add(val);
        }
        var result = Math.min(size, set.size());
    }

    //수박수
    public void t12() {
        int count = 3;
        var builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                builder.append("수");
            } else {
                builder.append("박");
            }
        }
        System.out.println(builder.toString());
    }

    //시저암호
    public void t13() {
        var str = "a B z";
        var num = 4;
        str.chars().forEach(System.out::println);
        var res = str.chars().map(val -> {
                    if (val + num > 122) {
                        return val + num - 26;
                    } else if (val < 97 && val + num > 90) {
                        return val + num - 26;
                    } else if (val == 32) {
                        return val;
                    } else {
                        return val + num;
                    }
                })
                .mapToObj(c -> (char) c)
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }

    //    "try hello world"	"TrY HeLlO WoRlD"
// 이상한 문자열 만들기
    public void t14() {
        var str = "AA aa ZZ zz";
        var arr = str.split(" ");

        var joiner = new StringJoiner(" ");
        for (var val : arr) {
            System.out.println(val);
            var builder = new StringBuilder();
            for (int i = 0; i < val.length(); i++) {
                if (i % 2 == 0) {
                    builder.append(String.valueOf(val.charAt(i)).toUpperCase());
                } else {
                    builder.append(String.valueOf(val.charAt(i)).toLowerCase());
                }
            }
            joiner.add(builder.toString());
        }
        var builder = new StringBuilder();
        for (int i = joiner.toString().length(); i < str.length(); i++) {
            builder.append(" ");
        }
        if (!builder.toString().isEmpty()) {
            joiner.add(builder.toString().replaceFirst(" ", ""));
        }
        System.out.println(joiner.toString().length());
    }

    //자릿수 더하기
    public void t15() {
        int num = 987;
        var res = String.valueOf(num).chars().mapToObj(c -> (int) c)
                .map(Character::getNumericValue).reduce(Integer::sum).orElse(-1);
        System.out.println(res);
    }

    //자연수 뒤집어 배열로
    public void t16() {
        long n = 12345;
        var builder = new StringBuilder(String.valueOf(n)).reverse();
        var str = builder.toString();
        var res = str.chars().map(Character::getNumericValue).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(res));
    }

    public String t16_1(String str) {
        if (str.length() == 1) {
            return str;
        }
        var size = str.length();
        return str.charAt(size - 1) + t16_1((str.substring(0, size - 1)));
    }

    public void t17() {
        long n = 13245;
        var res = String.valueOf(n).chars().map(Character::getNumericValue)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
        System.out.println(res[1]);
    }

    //정수내림차순   118372	873211
    public void t18() {
        long n = 118372;
        var str = String.valueOf(n).chars()
                .map(Character::getNumericValue)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToLong(i -> i)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
        var res = Long.parseLong(str);
        System.out.println(res);
    }

    //    정수 제곱근 판별
    public void t19() {
        long n = 144;
        var res = Math.pow((int) Math.sqrt(n), 2) == n ? Math.pow(Math.sqrt(n) + 1, 2) : -1;
        System.out.println((int) res);
    }

    //제일 작은 수 제거하기
    public int[] t20(int[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }
        return Arrays.stream(arr)
                .filter(val -> Arrays.stream(arr).min().getAsInt() != val)
                .toArray();
    }

    //짝수와 홀수
    public String t21(int num) {
        if (num % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }

    //소인수 분해
    public void t22() {
        int m = 21;
        int i = 2;
        List<Integer> list = new ArrayList<>();
        while (m != 1) {
            if (m % i == 0) {
                list.add(i);
                m /= i;
                i = 2;
            } else {
                i += 1;
            }
        }
        list.forEach(System.out::println);
    }

    //최대공약수 최소공배수
    public int[] t23() {
        int n1 = 24;
        int n2 = 16;
        int origin1 = n1;
        int origin2 = n2;

        while (n1 % n2 != 0) {
            var temp = n1;
            n1 = n2;
            n2 = temp % n2;
        }
        return new int[]{n2, origin1 * origin2 / n2};
    }

    //콜라츠 추축
//    6	8
//    16	4
//    626331	-1
    public int t24(int n) {
        long num = n;
        if (num == 1) {
            return 0;
        }

        int count = 0;
        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num = num * 3 + 1;
            }
            if (count == 500) {
                count = -1;
                break;
            }
            count += 1;
        }
        return count;
    }

    //평균구하기
    public double t25(int[] arr) {
        return Arrays.stream(arr).sum() / (arr.length / 10d * 10);
    }

    //하샤드수
    public boolean t26(int num) {
        var res = String.valueOf(num).chars().mapToObj(Character::getNumericValue).mapToInt(i -> i).sum();
        return num % res == 0;
    }

    //전화번호가리기 "01033334444"	"*******4444"
    public String t27(String str) {
        return IntStream.range(0, str.length())
                .mapToObj((index) -> {
                    if (index <= str.length() - 5) {
                        return "*";
                    } else {
                        return str.charAt(index);
                    }
                }).map(Objects::toString).collect(Collectors.joining(""));
    }

    //행렬의덧셈
    public void t28(int[][] arr1, int[][] arr2) {
//        Arrays.stream(arr1)
//                .flatMapToInt(Arrays::stream).boxed()
//                .forEach(System.out::println);
        var oneSize = arr1.length;
        var twoSize = arr1[0].length;
        int[][] answers = new int[oneSize][twoSize];
        for (int i = 0; i < oneSize; i++) {
            for (int j = 0; j < twoSize; j++) {
                answers[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        System.out.println(answers[0][0]);
    }

    //x만큼 간격있는 숫자
    public long[] t29(int x, int n) {
        long[] answer = new long[n];
        int index = 0;
        long copy = x;
        do {
            answer[index] = copy;
            copy += x;
        } while (index++ != n - 1);
        return answer;
    }

    //직사각형 별 찍기
    public void t30() {
        int a = 5;
        int b = 3;
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //소수 만들기
    public List<Integer> t31(int[] nums, int sum, int count, int to, int select, List<Integer> list) {
        if (count == to) {
            list.add(sum);
            return list;
        }
        for (int i = select; i < nums.length; i++) {
            t31(nums, sum + nums[i], count + 1, to, i + 1, list);
        }
        return list;
    }

    //예산
    // [1,3,2,5,4]	9	3
    //[2,2,3,3]	10	4
    public int t32(int[] d, int budget) {
        int answer = 0;
        var sorted = Arrays.stream(d).boxed()
                .sorted()
                .toArray(Integer[]::new);
        for (int val : sorted) {
            budget -= val;
            if (budget < 0) {
                budget += val;
                break;
            }
            answer += 1;
        }

        return answer;
    }


    //완주하지못한 선수 ["mislav", "stanko", "mislav", "ana"]	["stanko", "ana", "mislav"]	"mislav"
    public String t33(String[] participant, String[] completion) {
        String answer = "";

        List<String> list = new ArrayList<>(Arrays.stream(participant).collect(Collectors.toList()));

        Arrays.stream(completion)
                .forEach(val1 -> {
                    for (int index = 0; index < list.size(); index++) {
                        if (list.get(index).equals(val1)) {
                            list.remove(index);
                            break;
                        }
                    }
                });

        answer = list.stream().map(Objects::toString).collect(Collectors.joining());

        return answer;
    }

    //완주하지 못한선수 2(효율성)
    public String t34(String[] participant, String[] completion) {
        AtomicReference<String> answer = new AtomicReference<>("");

        Map<String, Integer> map = new HashMap<>();

        for (var val1 : participant) {
            var val = map.getOrDefault(val1, 0);
            map.put(val1, val + 1);
        }

        for (var val2 : completion) {
            var val = map.getOrDefault(val2, 0);
            map.put(val2, val - 1);
        }

        map.forEach((key, value) -> {
            if (value == 1) {
                answer.set(key);
            }
        });


        return answer.get();
    }

    //k번째 수 [1, 5, 2, 6, 3, 7, 4]	[[2, 5, 3], [4, 4, 1], [1, 7, 3]]	[5, 6, 3]
    public int[] t35(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0];
            int end = commands[i][1];
            int pick = commands[i][2];
            int[] arr = Arrays.stream(array).skip(start - 1).limit(end - start + 1).sorted().toArray();
            answer[i] = arr[pick - 1];
        }
        return answer;
    }

    //모의고사 [1,3,2,4,2]	[1,2,3]
//    1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
//    2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
//    3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
    public int[] t36(int[] answers) {
        int[] answer = new int[3];
        int[][] result = {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};

        for (int i = 0; i < result.length; i++) {
            int count = 0;
            for (int j = 0; j < answers.length; j++) {
                int x = j;
                if (i == 0 && j >= 5) {
                    x = j % 5;
                }
                if (i == 1 && j >= 8) {
                    x = j % 8;
                }
                if (i == 2 && j >= 10) {
                    x = j % 10;
                }
                if (answers[j] == result[i][x]) {
                    count += 1;
                }
            }
            answer[i] = count;
        }
        var copy = answer.clone();
        IntStream.range(0, answer.length)
                .forEach(index -> {
                    for (int i = 0; i < answer.length; i++) {
                        if (copy[index] < copy[i]) {
                            answer[index] = -1;
                        }
                    }
                    if (answer[index] != -1) {
                        answer[index] = index + 1;
                    }
                });
        return Arrays.stream(answer).filter(val -> val != -1).toArray();
    }

    //체육복 5	[2, 4]	[1, 3, 5]	5
    //5	[2, 4]	[3]	4
    //3	[3]	[1]	2
    public int t37(int n, int[] lost, int[] reserve) {
        AtomicInteger answer = new AtomicInteger();

        answer.set(n - lost.length);

        var reserves = Arrays.stream(reserve).boxed().sorted().collect(Collectors.toList());
        var losts = Arrays.stream(lost).boxed().sorted().collect(Collectors.toList());

        IntStream.range(0, losts.size()).forEach(index1 -> {
            for (int index2 = 0; index2 < reserves.size(); index2++) {
                if (Objects.equals(losts.get(index1), reserves.get(index2))) {
                    losts.set(index1, -losts.get(index1) * 2);
                    reserves.set(index2, -reserves.get(index2) * 2);
                    answer.addAndGet(1);
                }
            }
        });

        IntStream.range(0, losts.size()).forEach(index1 -> {
            for (int index2 = 0; index2 < reserves.size(); index2++) {
                if (losts.get(index1) == reserves.get(index2) - 1 || losts.get(index1) == reserves.get(index2) + 1) {
                    reserves.set(index2, -reserves.get(index2) * 2);
                    answer.addAndGet(1);
                    break;
                }
            }
        });

        return answer.get();
    }

    //두수 뽑아서 더하기
    // [2,1,3,4,1]	[2,3,4,5,6,7]
    //[5,0,2,7]	[2,5,7,9,12]
    public static Set<Integer> t38(int[] numbers, Set<Integer> set, int count, int pick, int sum) {
        if (count == 2) {
            set.add(sum);
            return set;
        }


        for (int i = pick; i < numbers.length; i++) {
            t38(numbers, set, count + 1, i + 1, sum + numbers[i]);
        }

        return set;
    }

    //3진수 뒤집기(메모리 초과)
    public static int t39(int n) {
        List<String> list = new ArrayList<>();
        do {
            list.add(String.valueOf(n % 3));
            n /= 3;
        } while (n != 1);
        list.add(String.valueOf(n % 3));

        AtomicInteger pow = new AtomicInteger((int) Math.pow(3, list.size()));
        int answer = list.stream().map(Integer::parseInt).map(val -> {
            pow.updateAndGet(v -> v / 3);
            return val * pow.get();
        }).mapToInt(i -> i).sum();


        return answer;
    }

    //내적 [1,2,3,4]	[-3,-1,0,2]	3
    //[-1,0,1]	[1,0,-1]	-2
    public static int t40(int[] a, int[] b) {
        int answer = 0;

        for (int i = 0; i < a.length; i++) {
            answer += a[i] * b[i];
        }


        return answer;
    }

    //    음양 더하기 [4,7,12]	[true,false,true]	9
    //            [1,2,3]	[false,false,true]	0
    public static int t41(int[] absolutes, boolean[] signs) {
        int answer = 0;

        for (int i = 0; i < absolutes.length; i++) {
            if (signs[i]) {
                answer += absolutes[i];
            } else {
                answer -= absolutes[i];
            }
        }

        return answer;
    }

    //약수 더하기
    public static int t42(int left, int right) {
        int answer = 0;


        for (int number = left; number <= right; number++) {
            int count = 0;
            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    count += 1;
                }
            }
            if (count % 2 == 0) {
                answer += number;
            } else {
                answer -= number;
            }
        }


        return answer;
    }

    //부족한 금액 계산하기
    //    price	money	count	result
    //3	20	4	10
    public static long t43(int price, long money, int count) {
        long answer = -1;
        long sum = 0;

        for (int i = 1; i <= count; i++) {
            sum += (long) price * i;
        }

        return money - sum < 0 ? Math.abs(money - sum) : 0;
    }

    //없는 숫자 더하기
    public static int t44(int[] numbers) {
        int answer = 45;
        for (int num : numbers) {
            answer -= num;
        }
        return answer;
    }

    //나머지가 1이 되는 숫자 찾기
//    10	3
//            12	11
    public static int t45(int n) {
        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (n % i == 1) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
        return answer;
    }

    //숫자 짝궁
//    "100"	"2345"	"-1"
//            "100"	"203045"	"0"
//            "100"	"123450"	"10"
//            "12321"	"42531"	"321"
//            "5525"	"1255"	"552
    public static String t46(String str1, String str2) {
        var builder = new StringBuilder();

        var arr1 = str1.chars().map(c -> c - '0').boxed().sorted(Comparator.reverseOrder())
                .map(String::valueOf).toArray(String[]::new);
        var arr2 = str2.chars().map(c -> c - '0').boxed().sorted(Comparator.reverseOrder())
                .map(String::valueOf).toArray(String[]::new);


        for (int i = 0; i < arr1.length; i++) {
            var val1 = arr1[i];
            for (int j = 0; j < arr2.length; j++) {
                var val2 = arr2[j];
                if (val1.equals(val2)) {
                    builder.append(val1);
                    arr2[j] = "-1";
                    break;
                }
            }
        }

        return builder.toString().isEmpty() ? "-1" : Long.parseLong(builder.toString()) == 0 ? "0" : builder.toString();
    }

    //숫자 짝궁
//    [-2, 3, 0, 2, -5]	2
//            [-3, -2, -1, 0, 1, 2, 3]	5
//            [-1, 1, -1, 1]	0
    public static AtomicInteger t47(int[] number, int sum, int count, int pick, AtomicInteger answer) {
        if (count == 3 && sum == 0) {
            answer.addAndGet(1);
        }

        for (int i = pick; i < number.length; i++) {
            t47(number, sum + number[i], count + 1, i + 1, answer);
        }
        return answer;
    }

    //콜라문제
//    2	1	20	19
//    3	1	20	9
    public static int t48(int a, int b, int total) {
        int answer = 0;

        while (total >= a) {
            int count = total / a;
            answer += b * count;
            total = (total % a) + (b * count);
        }


        return answer;
    }

    //옹알이
//    ["aya", "yee", "u", "maa"]	1 "aya", "ye", "woo", "ma"
//    ["ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"]	2
    public static int t49(String[] babbling) {
        int answer = 0;
        String[] list = {"ye", "aya", "woo", "ma"};

        for (var str : babbling) {
            Set<String> set = new HashSet<>();
            while (!str.isEmpty()) {
                boolean check = true;
                for (var val : list) {
                    if (!set.contains(val) && str.startsWith(val)) {
                        str = str.replaceFirst(val, "");
                        set.clear();
                        set.add(val);
                        check = false;
                    }
                }
                if (check) break;
            }
            System.out.println(str);
            if (str.isEmpty()) {
                answer += 1;
            }
        }

        System.out.println(answer);

        return answer;
    }

    //햄버거만들기
//    [2, 1, 1, 2, 3, 1, 2, 3, 1]	2
//            [1, 3, 2, 1, 2, 1, 3, 1, 2]	0
    // 1- 빵, 2 - 야채, 3 - 고기
    // 빵 야채 고기 빵 1 2 3 1
    public static int t50(int[] ingredient) {
        int answer = 0;
        String check = "1231";
        String str = "";
        for (var val : ingredient) {
            str += val;
            if (str.length() >= 4) {
                var compare = str.substring(str.length() - 4);
                if (compare.equals(check)) {
                    answer += 1;
                    str = str.substring(0, str.length() - 4);
                }
            }
        }
        System.out.println(answer);

        return answer;
    }

    //시간초과
    public static int t51(int[] ingredient) {
        int answer = 0;
        var str = Arrays.stream(ingredient).boxed().map(Objects::toString).collect(Collectors.joining());
        var compare = "1231";

        while (true) {
            boolean check = true;
            String temp = str.replaceFirst(compare, "");
            if (temp.length() != str.length()) {
                check = false;
                str = temp;
                answer += 1;
            }
            if (check) break;
        }


        return answer;
    }

    //푸드 파이터 대회[1, 3, 4, 6]	"1223330333221"
    //[1, 7, 1, 2]	"111303111"
    public static String t52(int[] food) {
        StringBuilder answer = new StringBuilder();

        for (int i = 1; i < food.length; i++) {
            var f = food[i];
            answer.append(String.valueOf(i).repeat(f / 2));
        }

        answer.append(0);

        for (int i = food.length - 1; i >= 1; i--) {
            var f = food[i];
            answer.append(String.valueOf(i).repeat(f / 2));
        }

        return answer.toString();
    }

    //과일 장수
//    3	4	[1, 2, 3, 1, 2, 3, 1]	8
//    4	3	[4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2]	33
    public static int t53(int k, int m, int[] score) {
        int answer = 0;


//        Integer[] arr = Arrays.stream(score).boxed().sorted(Comparator.reverseOrder())
//                .takeWhile()


        return answer;
    }


}

