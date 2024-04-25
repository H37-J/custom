package com.hjk.custom.utils.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class AddTwoNumbers {

    public static void main(String... args) {
        var node1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        var node2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        addTwoNumbers(node1, node2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> list = new ArrayList<>();
        recur(l1, l2, 0, list);
        print(list);
        var node = new ListNode(list.remove(0));
        while (!list.isEmpty()) {
            var current = node;
            var nextNode = new ListNode(list.remove(0));
            while (current.next != null) {
                current = current.next;
            }
            current.next = nextNode;
        }
        print(node);
        return node;
    }

    public static void recur(ListNode node1, ListNode node2, int next, List<Integer> current) {
        if (node1 == null && node2 == null) {
            if(next == 1) current.add(next);
            return;
        }

        var number = (node1 != null ? node1.val : 0) + (node2 != null ? node2.val : 0) + next;
        if (number >= 10) {
            number %= 10;
            next = 1;
        } else {
            next = 0;
        }
        current.add(number);
        var next1 = node1 != null && node1.next != null ? node1.next : null;
        var next2 = node2 != null && node2.next != null ? node2.next : null;
        recur(next1, next2, next, current);
    }

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode() {

        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public String toString() {
            return this.val + "->" + this.next;
        }
    }
}


