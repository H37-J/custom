package com.hjk.custom.utils.algorithms.etc.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PreemptivePriorityScheduling {

    public static void main(String... args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 0, 5, 10));
        processes.add(new Process("P2", 1, 4, 20));
        processes.add(new Process("P3", 2, 2, 30));
        processes.add(new Process("P4", 4, 1, 40));
        var res = preemptivePriorityScheduling(processes);
    }

    public static  List<String> preemptivePriorityScheduling(List<Process> processes) {
        List<String> ganttChart = new ArrayList<>();
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(p -> - p.priority));
        int currentTime = 0;

        while(!processes.isEmpty() || !readyQueue.isEmpty()) {
            while(!processes.isEmpty() && processes.getFirst().arrivalTime <= currentTime) {
                readyQueue.add(processes.removeFirst());
            }

            if(!readyQueue.isEmpty()) {
                var currentProcess = readyQueue.poll();

                ganttChart.add(currentProcess.name);
                currentProcess.burstTime--;

                if(currentProcess.burstTime > 0) {
                    readyQueue.add(currentProcess);
                }
            } else {
                ganttChart.add("Idle");
            }
            currentTime++;
        }
        return ganttChart;
    }

    private static class Process {
        String name;
        int arrivalTime;
        int burstTime;
        int priority;

        public Process(String name, int arrivalTime, int burstTime, int priority) {
            this.name = name;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.priority = priority;
        }
    }

}

