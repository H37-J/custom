package com.hjk.custom.utils.algorithms.programmers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class Two {

    public static void main(String... args) {
        var two = new Two();
        two.solution3(new int[]{1,1,9,1,1,1}, 0);
    }

    //프로세스
   // priorities	location	return
   // [2, 1, 3, 2]	2	1
   // [1, 1, 9, 1, 1, 1]	0	5
    public int solution3(int[] priorities, int location) {
        int answer = 0;
        var size = priorities.length - 1;
        Queue<Process> processes = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            processes.add(new Process(priorities[i], i));
        }

        while(!processes.isEmpty()) {
            var val = processes.peek().getPriority();
            var max = processes.stream().map(Process::getPriority).max(Integer::compare).get();
            if(val < max) {
                processes.add(processes.poll());
            } else {
                var check = processes.poll();
                size -= 1;
                answer++;
                if(check.getIndex() == location) break;
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

