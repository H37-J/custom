package com.hjk.custom.utils.algorithms.etc.datastructure;

import java.util.ArrayList;
import java.util.List;

public class Graphs<E extends Comparable<E>> {

    private final List<Node<E>> nodeList;
    private final List<Edge<E>> edgeList;

    public Graphs() {
        nodeList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }

//    public boolean add(Node<E> startNode, Node<E> endNode) {
//        Node<E> start = null;
//        Node<E> end = null;
//        for(var node : nodeList) {
//            if(node.name.compareTo(startNode.name) == 0) {
//                start = node;
//            }
//        }
//    }

    private static class Node<E> {
        E name;

        public Node(E name) {
            this.name = name;
        }
    }

    private static class Edge<E> {
       Node<E> fromNode;
       Node<E> toNode;
       int weight;

       public Edge(Node<E> fromNode, Node<E> toNode) {
           this.fromNode = fromNode;
           this.toNode = toNode;
       }
    }
}
