package com.hjk.custom.utils.algorithms.programmers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class Two {

    public static void main(String... args) {
        var two = new Two();
        long startTime = System.currentTimeMillis();

        two.solution14(new int[]{30,70,73}, 100);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Execution time: " + duration + "ms");
    }



    //구명보트
    //people	limit	return
    //[10,11,20,30,50,70,80,90]	100	3
    //[70, 80, 50]	100	3
    public int solution14(int[] people, int limit) {
        var arr = Arrays.stream(people).sorted().toArray();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(var val : arr) {
            var key = (int) Math.floor(val * 0.1) * 10;
            var list = map.getOrDefault(key, new ArrayList<>());
            list.add(val);
            map.put(key, list);
        }
        print(map);
        for(var entry : map.entrySet()) {
            var key = entry.getKey();
            var list = entry.getValue();

        }
        return -1;
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

