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

    public static void main(String... args) {

        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 10; i++) {
            tree.add((int) (Math.random() * 100));

        }

        tree.balanceTree();
        System.out.println("root: " + tree.root);
        List<TreeNode<Integer>> listTree = tree.getInOrderList(tree.root);

        for (TreeNode node : listTree) {
            int i = tree.getDepth(node);
            System.out.println("depth of " + node.toString() + " is " + i);
        }
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

    private void balanceTree() {
        treeList = getInOrderList(root);
        root = null;
        int middle = 0;
        while (treeList.size() != 0) {
            middle = treeList.size() / 2;
            TreeNode<T> node = treeList.remove(middle);
            this.add(node.element);
        }
    }

    public int getDepth(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = getDepth(root.leftChild);
        int rightDepth = getDepth(root.rightChild);

        if (root.leftChild != null) {
            return 1 + rightDepth;
        }
        if (root.rightChild != null) {
            return 1 + leftDepth;
        }
        return 1 + Math.min(leftDepth, rightDepth);
    }

    public List<TreeNode<T>> getInOrderList(TreeNode root) {

        if (root != null) {
            getInOrderList(root.leftChild);
            treeList.add(root);
            getInOrderList(root.rightChild);
        }
        return treeList;
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
}

