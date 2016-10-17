import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrew_2 on 10/13/2016.
 */
public class BinarySearchTree {
    static Node head;
    static ArrayList<Word> words;
    static ArrayList<Word> traversalArray;
    static int comparisons;
    static int assignments;
    static int nodeCount;

    BinarySearchTree(ArrayList<Word> input) {
        head = new Node();
        words = new ArrayList<>();
        traversalArray = new ArrayList<>();
        nodeCount = 1;
        comparisons = 0;
        assignments = 0;
        for(Word w : input){
            Word temp = new Word();
            temp.word = w.word;
            temp.count = w.count;
            words.add(temp);
        }
    }

    public static void go() {
        // Loop through each word in the input array.
        for (Word w : words) {
            // Call insert on head of tree, pass it the
            // value to be inserted.
            insert(w, head);
        }
        System.out.println("Done. " + nodeCount + " nodes in tree.");

        // Function to traverse the array and dump each element into
        // an array. The tree is sorted, so the array this creates
        // is automatically sorted as well.
        traverse(head);

        // Print the first and last ten values of that array.
        for (int i = 0; i < 10; ++i) {
            System.out.println("\'" + traversalArray.get(i).word + "\' " + ": " + traversalArray.get(i).count);
        }
        System.out.println();
        for (int i = traversalArray.size() - 11; i < traversalArray.size(); ++i) {
            System.out.println("\'" + traversalArray.get(i).word + "\' " + ": " + traversalArray.get(i).count);
        }
        // Print the number of assignments and comparisons.
        System.out.println(comparisons + " comparisons performed.");
        System.out.println(assignments + " assignments performed.");
    }

    private static void insert(Word w, Node top) {
        // If this node is null, we're done. Insert
        // the value here.
        if (top.data == null) {
            ++assignments;
            top.data = w;
        }
        // If the value is identical to this node's value
        // The word already exists. Increment the count.
        else if (w.equals(top.data)) {
            ++comparisons;
            ++assignments;
            int newCount = top.data.count;
            top.data.count = ++newCount;
        }
        // If the value if less than this node's value
        // call recursively on this node's left child.
        else if (w.lessThan(top.data)) {
            comparisons += 2;
            if (top.leftChild == null) {
                ++nodeCount;
                top.leftChild = new Node();
            }
            insert(w, top.leftChild);
        }
        // If the value if greater than this node's value
        // call recursively on this node's right child.
        else if (w.greaterThan(top.data)) {
            comparisons += 3;
            if (top.rightChild == null) {
                ++nodeCount;
                top.rightChild = new Node();
            }
            insert(w, top.rightChild);
        }
    }

    private static void traverse(Node current) {
        // We do a in-order traversal of the tree and put each element into the array.
        if (current != null) {
            traverse(current.leftChild);
            traversalArray.add(current.data);
            traverse(current.rightChild);
        }
        return;

    }

    // Node class for our implementation of a BST.
    private static class Node {
        Node leftChild;
        Node rightChild;
        Word data;
    }
}
