package com.hjk.custom.utils.algorithms.levelone;

import lombok.Setter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class One<T> {

    public static void main(String... args) {
        var one = new One();
//        one.t34(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"});
//        one.t35(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}});
//        one.t37(5, new int[]{4, 2}, new int[]{3, 5});

        time1();
    }

    //달리기 경주
    public void t1() {
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};

        var start = System.nanoTime();
        IntStream.range(0, callings.length).forEach((index1) -> {
            var val1 = callings[index1];
            IntStream.range(0, players.length).forEach((index2) -> {
                var val2 = players[index2];
                if (val1.equals(val2)) {
                    swap(players, index2 - 1, index2);
                }
            });
        });


        Arrays.stream(players).forEach(System.out::println);
        var end = System.nanoTime();
        System.out.println((end - start) / 1000);
    }

    //약수 구하기
    public void t2(int num) {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        System.out.println(sum);
    }

    //    가운데 글자 가져오기
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

    public static void stream1() {
        int[] array = {1, 2, 3, 4, 5, 6};
        Arrays.stream(array).mapToObj(a -> new int[]{a, a + 1, a + 2})
                .forEach(System.out::println);
    }

    public static void stream2() {
        BiFunction<Integer, Integer, Integer> add = Integer::sum;
        int res = add.apply(1, 2);
        System.out.println(res);
    }

    public static void stream3() {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4);
        numbers.takeWhile(val -> val != 3).forEach(System.out::println);
    }

    public static void stream4() {
        Comparator<Integer> integerComparator = Comparator.naturalOrder();
        BinaryOperator<Integer> max = BinaryOperator.maxBy(integerComparator);
        var res = max.apply(1, 2);
        System.out.println(res);
    }

    public static void stream5() {
        BiPredicate<Integer, String> predicate = (val1, val2) -> {
            return val1 == 3 && val2.equals("test");
        };
        var res = predicate.test(3, "test");
        System.out.println(res);
    }

    public static void stream6() {
        Stream<String> stream = Stream.of("a", "b", "c");
        String[] arr = stream.flatMap(val -> Stream.of(val.split(""))).toArray(String[]::new);
        System.out.println(Arrays.toString(arr));
    }

    public static void stream7() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        var res = IntStream.range(0, 4).boxed().
                flatMap(val -> {
                    if (val >= 1) val += val;
                    return Stream.of(new Integer[][]{{arr[val], arr[val + 1]}});
                }).
                toArray(Integer[][]::new);
        print(res);

        var copy = Arrays.copyOfRange(arr, 1, 4);
    }

    public static void stream8() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> result = numbers.stream()
                .mapMulti((num, consumer) -> {
                    consumer.accept(num * num);
                    consumer.accept((int) Math.sqrt(num));
                }).mapToInt(i -> (int) i)
                .boxed().collect(Collectors.toList());

        System.out.println(result);
    }

    public static void stream9() {
        Function<Integer, String> converter = String::valueOf;
        String res = converter.apply(1);
    }

    public static void stream10() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        stream.peek(System.out::println);
    }

    //        48 ~ 57 digit 0 ~ 9
    public static void stream11() {
        String str = "1a345t0123900aa4";
        var answer = "";
        var sum = 0;
        var arr = str.split("");
        for (var s : arr) {
            if (s.codePointAt(0) >= 48 && s.codePointAt(0) <= 57) {
                answer += s;
                sum += Integer.parseInt(s);
            }
        }
        print(answer, sum);
    }

    public static void stream12() {
        int[] arr1 = {1, 2, 3};
        Integer[][] arr2 = {{1, 2}, {3, 4}};
        String[] str = {"test1", "test2"};
        var res1 = Arrays.stream(arr1).average().getAsDouble();
        var res2 = Arrays.stream(str).mapMulti((val, consumer) -> {
            consumer.accept(val.toUpperCase());
            consumer.accept(val.toLowerCase());
        }).collect(Collectors.toList());

        Integer[] res3 = Arrays.stream(arr2)
                .flatMap(Arrays::stream).toArray(Integer[]::new);

        print(res1);
        print(res2);
        print(res3);
    }

    public static void stream13() {
        //소수찾기
        int size = 30;
        IntStream.range(2, size + 1).filter(val -> {
            for (int i = 2; i <= val; i++) {
                if (val % i == 0 && val == i) {
                    return true;
                } else if (val % i == 0) {
                    return false;
                }
            }
            return false;
        }).forEach(System.out::println);
    }

    public static void stream14() {
        int size = 10;
        Random random = new Random();
        var users = IntStream.range(1, size + 1)
                .mapToObj(val -> new User(val, "hjk" + val, random.nextInt(10 + 1)))
                .toList();

        AtomicInteger sum = new AtomicInteger();
        var map = users.stream()
                .peek(user -> {
                    var score = user.getScore();
                    int rank = 1;
                    for (var compare : users) {
                        if (score < compare.getScore()) {
                            rank += 1;
                        }
                    }
                    sum.addAndGet(user.getScore());
                    user.setRank(rank);
                })
                .collect(Collectors.groupingBy(User::getRank));
        print(map);
        print("sum", sum.get());
        print("avg", sum.get() / (double) size);

        Integer num = 1;

    }

    public static void stream15() {
        int[] arr = {1, 2, 3, 4};
        int[] find = {2, 3};
        int[] filter = {2, 4};
        int[] filterIndex = {0, 3};

        var index = Arrays.stream(find).map(val -> findIndex(arr, val)).toArray();
        var filtered = Arrays.stream(arr).filter(val1 -> {
            for (var val2 : filter) {
                if (val1 == val2) {
                    return true;
                }
            }
            return false;
        }).toArray();

        List<Integer> filteredByIndex = new ArrayList<>();
        IntStream.range(0, arr.length)
                .forEach(index1 -> {
                    for (var index2 : filterIndex) {
                        if (index1 == index2) {
                            filteredByIndex.add(arr[index1]);
                            break;
                        }
                    }
                });

        print(index);
        print(filtered);
        print(filteredByIndex);
    }

    public static <T> List<T> ArrayToList(T[] arr) {
        return new ArrayList<>(Arrays.stream(arr).collect(Collectors.toList()));
    }

    public static <Integer> void groupMap(Integer[] arr) {
        Map<Integer, Long> map = Arrays.stream(arr)
                .collect(Collectors.groupingBy(val -> val, Collectors.counting()));
        Map<Integer, List<Integer>> map1 = Arrays.stream(arr)
                .collect(Collectors.groupingBy(val -> val));
        print(map);
    }


    public static int[] range(int[] arr, int start, int end) {
        return Arrays.copyOfRange(arr, start, end);
    }

    public static <T> T[] range(T[] arr, int start, int end) {
        return Arrays.copyOfRange(arr, start, end);
    }

    public static <T> void swap(T[] arr, int index1, int index2) {
        var temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void swap(List<String> list, int index1, int index2) {
        var temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    public static void print(int val) {
        System.out.println(val);
    }

    public static <T> void print(T val) {
        System.out.println(val);
    }

    public static <T> void print(int val1, int val2) {
        System.out.print(val1 + ",");
        System.out.print(val2);
        System.out.println();
    }

    public static <T> void print(int val1, String val2) {
        System.out.print(val1 + ",");
        System.out.print(val2);
        System.out.println();
    }

    public static <T> void print(String val1, int val2) {
        System.out.print(val1 + ",");
        System.out.print(val2);
        System.out.println();
    }

    public static <T> void print(T val1, T val2) {
        System.out.print(val1 + ",");
        System.out.print(val2);
        System.out.println();
    }

    public static void print(int[] arr) {
        var joiner = new StringJoiner(",");
        for (var val : arr) {
            joiner.add(String.valueOf(val));
        }
        System.out.println(joiner.toString());
    }

    public static void print(String[] arr) {
        for (var val : arr) {
            System.out.println(val);
        }
    }

    public static <T> void print(T[] arr) {
        for (var val : arr) {
            System.out.println(val);
        }
    }

    public static <T> void print(T[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static <T, R> void print(Map<T, R> map) {
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }

    public static void consumer() {
        Consumer<String> print1 = System.out::println;
        Consumer<String> print2 = (val) -> {
            System.out.println("val2");
        };
        print1.andThen(print2).accept("val1");
    }

    public static int findIndex(int[] arr, int find) {
        int index = 0;
        for (var val : arr) {
            if (val == find) {
                return index;
            } else {
                index += 1;
            }
        }
        return -1;
    }



    public static void time1() {
        long start = System.currentTimeMillis();


        stream15();


        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Execution time: " + duration + "ms");
    }


    public static class User {
        int age;
        String name;

        int score;

        @Setter
        int rank;

        public User() {

        }

        public User(int age, String name, int score, int rank) {
            this.age = age;
            this.name = name;
            this.score = score;
            this.rank = rank;
        }

        public User(int age, String name, int score) {
            this.age = age;
            this.name = name;
            this.score = score;
        }

        public User age(int age) {
            this.age = age;
            return this;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public int getRank() {
            return rank;
        }

        public User name(String name) {
            this.name = name;
            return this;
        }

        public static User builder() {
            return new User();
        }

        @Override
        public String toString() {
            return this.name + "," + this.age + "," + this.score;
        }

    }
}

