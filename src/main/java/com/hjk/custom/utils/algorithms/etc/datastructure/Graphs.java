package com.hjk.custom.utils.algorithms.etc.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Graphs<E extends Comparable<E>> {

    public static void main(String... args) {
        var graphs = new Graphs<Integer>();
        graphs.add(new Node<Integer>(1), new Node<Integer>(2));
        graphs.add(new Node<Integer>(1), new Node<Integer>(3));
        graphs.add(new Node<Integer>(2), new Node<Integer>(3));
        var edges = graphs.findEdge(1);
        for(var edge : edges) {
            System.out.println(edge);
        }
    }

    private final List<Node<E>> nodeList;
    private final List<Edge<E>> edgeList;

    public Graphs() {
        nodeList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }

    public void add(Node<E> startNode, Node<E> endNode) {
        Node<E> start = null;
        Node<E> end = null;
        for (var node : nodeList) {
            if (node.name.compareTo(startNode.name) == 0) {
                start = node;
            }
        }
        for (var node : nodeList) {
            if (node.name.compareTo(endNode.name) == 0) {
                end = node;
            }
        }
        if (start == null) {
            start = startNode;
            nodeList.add(startNode);
        }
        if (end == null) {
            end = endNode;
            nodeList.add(endNode);
        }
        edgeList.add(new Edge<E>(start, end));
    }

    public List<Edge<E>> findEdge(E name) {
        List<Edge<E>> edges = new ArrayList<>();
        for(var edge : edgeList) {
                if(edge.fromNode.name.compareTo(name) == 0) {
                    edges.add(edge);
                }
        }
        return edges;
    }

    public String toString() {
        var builder = new StringBuilder();
        var joiner = new StringJoiner(", ");
        builder.append("nodeList: ");
        for(var node : nodeList) {
            joiner.add(String.valueOf(node.name));
        }
        builder.append(joiner.toString());
        builder.append("\nedgeList: ");
        joiner = new StringJoiner(", ");
        for(var edge : edgeList) {
           joiner.add(String.valueOf(edge.fromNode.name + " -> " + edge.toNode.name));
        }
        builder.append(joiner.toString());
        return builder.toString();
    }

    public static class Node<E> {
        E name;

        public Node(E name) {
            this.name = name;
        }

        public String toString() {
            return String.valueOf(this.name);
        }
    }

    public static class Edge<E> {
        Node<E> fromNode;
        Node<E> toNode;
        int weight;

        public Edge(Node<E> fromNode, Node<E> toNode) {
            this.fromNode = fromNode;
            this.toNode = toNode;
        }

        public Edge(Node<E> fromNode, Node<E> toNode, int weight) {
            this.fromNode = fromNode;
            this.toNode = toNode;
            this.weight = weight;
        }

        public String toString() {
            var builder = new StringBuilder();
            builder.append(fromNode.name).append(" -> ").append(toNode.name);
            return builder.toString();
        }
    }
}
