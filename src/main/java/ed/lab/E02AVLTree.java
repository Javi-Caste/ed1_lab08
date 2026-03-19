package ed.lab;

import java.util.Comparator;

public class E02AVLTree<T> {

    private final Comparator<T> comparator;
    private Node<T> root;
    private int size;

    public E02AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }

    public void insert(T value) {
        this.root = insert(this.root, value);
    }
    private Node<T> insert(Node<T> root, T value){
        if(root == null){
            this.size ++;
            return new Node<T>(value);
        }
        int compare = comparator.compare(value, root.value);
        if (compare < 0) root.left = insert(root, value);
        else if (compare > 0) root.right = insert (root, value);
        else{
            return root;
        }
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        int balance = getBalance(root);

        //left heavy
        if(balance < -1 && comparator.compare(value, root.left.value) > 0){
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        //right heavy
        if(balance > 1 && comparator.compare(value, root.left.value) < 0) {
            root.right = rotateRight(root);
            return rotateLeft(root);
        }
        return root;
    }

    private Node<T> rotateLeft(Node<T> root){
        if(root ==null) return null;

        Node<T> newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;

        updateHeights(newRoot);
        updateHeights(root);
        return newRoot;


    }
    private Node<T> rotateRight (Node<T> root){
        if(root ==null) return null;

        Node<T> newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;

        updateHeights(newRoot);
        updateHeights(root);
        return newRoot;

    }

    private void updateHeights (Node<T> root){
        if (root == null) return;
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public void delete(T value) {

    }

    public T search(T value) {
        return null;
    }

    public int height() {
        if(this.root == null) return 0;
        return this.root.height;
    }

    public int size() {
        return this.size;
    }

    private int getBalance(Node<T> root){
        if (root == null) return 0;
        return getHeight(root.right) - getHeight(root.left);
    }
    private int getHeight(Node<T> root){
       if (root == null) return 0;
        return root.height;
    }

    protected static class Node<T>{
        protected T value;
        protected Node<T> left;
        protected Node<T> right;
        protected int height = 1;

        public Node (T value){
            this.value = value;
            this.height = 1;
        }
    }
}
