package datastructures;

import java.util.LinkedList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {

    private TreeNode<T> root;
    private List<TreeNode<T>> treeList = new LinkedList<>();

    public BinaryTree() {

    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    public BinaryTree(List list) {
        createTreeFromList(list);
    }

    private void createTreeFromList(List list) {

    }

    public void add(T element) {
        addNode(root, new TreeNode(element));
    }

    private void addNode(TreeNode tmpNode, TreeNode newNode) {
        if(root == null) {
            root = newNode;
        } else if (newNode.element.compareTo(tmpNode.element) <= 0) {
            if (tmpNode.leftChild != null){
                addNode(tmpNode.leftChild, newNode);
            } else {
                tmpNode.leftChild = newNode;
            }
        } else {
            if (tmpNode.rightChild != null) {
                addNode(tmpNode.rightChild, newNode);
            } else {
                tmpNode.rightChild = newNode;
            }
        }
    }

    private List<TreeNode<T>> leftList = new LinkedList<>();
    private List<TreeNode<T>> rightList = new LinkedList<>();

    public static void main(String... args) {
        TreeNode<Integer> node = new TreeNode<>(3);

        BinaryTree<Integer> tree = new BinaryTree<>(node);
        for (int i = 0; i < 50; i++) {
            tree.add((int) (Math.random() * 100));
        }
        /*
        tree.root.leftChild = new TreeNode<>(5);
        tree.root.leftChild.leftChild = new TreeNode<>(6);
        tree.root.leftChild.leftChild.leftChild = new TreeNode<>(7);
        tree.root.leftChild.leftChild.leftChild.leftChild = new TreeNode<>(8);
        tree.root.leftChild.leftChild.leftChild.leftChild.leftChild = new TreeNode<>(9);
        tree.root.leftChild.leftChild.leftChild.leftChild.leftChild.leftChild = new TreeNode<>(10);
        tree.root.leftChild.leftChild.leftChild.leftChild.leftChild.leftChild.leftChild = new TreeNode<>(20);
        */

        int depth = tree.getDepth(tree.root.leftChild);
        System.out.println("Left tree Depth = " + depth);


        depth = tree.getDepth(tree.root.rightChild);
        System.out.println("Right tree Depth = " + depth);
        depth = tree.getDepth(tree.root);
        System.out.println("Depth = " + depth);
        tree.balanceTree();
        depth = tree.getDepth(tree.root);
        System.out.println("Depth = " + depth);

        depth = tree.getDepth(tree.root.leftChild);
        System.out.println("Left tree Depth = " + depth);


        depth = tree.getDepth(tree.root.rightChild);
        System.out.println("Right tree Depth = " + depth);

    }

    private void balanceTree() {
        treeList.clear();
        treeList = getInOrderList(root);
        root = null;
        int middle = (treeList.size() / 2);

        splitList(middle);
        // if (leftList.size() > rightList.size()) {
        // System.out.println("Root Node: " + leftList.get(leftList.size() - 1));
        // this.add(leftList.remove(leftList.size() - 1).element);
        //} else {
        System.out.println("Root Node: " + rightList.get(0));
        this.add(rightList.remove(0).element);
        //}
        int leftMiddle = 0;
        int rightMiddle = 0;
        while (leftList.size() != 0 || rightList.size() != 0) {
            if (((leftList.size() / 2) - 1) >= 0) {
                leftMiddle = Math.round((leftList.size() / 2) - 1);
            } else {
                leftMiddle = Math.round(leftList.size() / 2);
            }
            if (((rightList.size() / 2) - 1) >= 0) {
                rightMiddle = Math.round((rightList.size() / 2) - 1);
            } else {
                rightMiddle = Math.round(rightList.size() / 2);
            }
            if (leftList.size() != 0) {
                System.out.println("Node: " + leftList.get(leftMiddle) + " Added");
                this.add(leftList.remove(leftMiddle).element);
            }
            if (rightList.size() != 0) {
                System.out.println("Node: " + rightList.get(rightMiddle) + " Added");
                this.add(rightList.remove(rightMiddle).element);
            }

        }
    }

    private void splitList(int middle) {
        for (int i = 0; i < middle; i++) {
            System.out.println("Added to left list " + treeList.get(i));
            leftList.add(treeList.get(i));
        }
        for (int i = middle; i < treeList.size(); i++) {
            System.out.println("Added to right list " + treeList.get(i));
            rightList.add(treeList.get(i));
        }

    }

    public int getDepth(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = getDepth(root.leftChild);
        int rightDepth = getDepth(root.rightChild);

        if (leftDepth > rightDepth) {
            return leftDepth + 1;
        } else {
            return rightDepth + 1;
        }
    }

    public void reverseInOrder(TreeNode root) {
        TreeNode tmpNode = root;
        if (tmpNode.rightChild != null) {
            reverseInOrder(tmpNode.rightChild);
        }
        if (tmpNode.leftChild != null) {
            reverseInOrder(tmpNode.leftChild);
        }
    }

    private static class TreeNode<T extends Comparable<T>> {
        T element;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(T element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public List<TreeNode<T>> getInOrderList(TreeNode root) {
        if (root != null) {
            getInOrderList(root.leftChild);
            // System.out.println(root);
            treeList.add(root);
            getInOrderList(root.rightChild);
        }
        return treeList;
    }
}

