package datastructures;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {

    private Node<T> root;

    public BinaryTree() {

    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public BinaryTree(List list) {
        createTreeFromList(list);
    }

    private void createTreeFromList(List list) {

    }

    public void add(T element) {
        addNode(root, new Node(element));
    }

    private void addNode(Node tmpNode, Node newNode) {
        if(root == null) {
            root = newNode;
        } else if (newNode.element.compareTo(tmpNode.element) <= 0) {
            if (tmpNode.leftChild != null){
                addNode(tmpNode.leftChild, newNode);
            } else {
                tmpNode.leftChild = newNode;
            }
        } else {
            if (tmpNode.leftChild == newNode) {
                addNode(tmpNode.rightChild, newNode);
            } else {
                tmpNode.rightChild = newNode;
            }
        }
    }

    private void balanceTree(Node root) {

    }

    public void inOrderTraverse(Node root) {
        Node tmpNode = root;

        if (tmpNode.leftChild != null) {
            inOrderTraverse(root.leftChild);
        }
        if (tmpNode.rightChild != null) {
            inOrderTraverse(root.rightChild);
        }
    }

    private class Node<T extends Comparable<T>> {
        T element;
        Node leftChild;
        Node rightChild;

        public Node(T element) {
            this.element = element;
        }
    }

    public static void main(String... args) {
       List<Integer> list = new ArrayList<>();
       for (int i = 0; i < 20; i++) {
           list.add(i);
       }
       BinaryTree<Integer> tree = new BinaryTree<>(list);

    }
}
