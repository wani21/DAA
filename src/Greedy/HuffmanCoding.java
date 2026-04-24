package Greedy;

import java.util.*;

// Node class
class Node {
    int freq;
    char ch;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        left = right = null;
    }
}

public class HuffmanCoding {

    // Comparator for priority queue
    static class MyComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return a.freq - b.freq;
        }
    }

    // Print Huffman Codes
    public static void printCodes(Node root, String code) {
        if (root == null) return;

        // Leaf node (actual character)
        if (root.left == null && root.right == null) {
            System.out.println("Character: " + root.ch + " -> Code: " + code);
            return;
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {

        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] freq  = {5, 9, 12, 13, 16, 45};

        int n = chars.length;

        // Step 1: Create Priority Queue
        PriorityQueue<Node> pq = new PriorityQueue<>(new MyComparator());

        // Step 2: Add all nodes
        for (int i = 0; i < n; i++) {
            pq.add(new Node(chars[i], freq[i]));
        }

        // Step 3: Build Huffman Tree
        while (pq.size() > 1) {

            Node left = pq.poll();
            Node right = pq.poll();

            Node newNode = new Node('-', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        // Root of tree
        Node root = pq.peek();

        // Step 4: Print codes
        System.out.println("Huffman Codes:");
        printCodes(root, "");
    }
}
