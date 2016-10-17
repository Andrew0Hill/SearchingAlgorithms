import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Andrew_2 on 10/13/2016.
 */
public class Driver {
    static ArrayList<Word> inputData;

    Driver() {
        inputData = new ArrayList<>();
    }

    public static void main(String[] args) {
        inputData = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(args[0]));
            String currentLine;

            while ((currentLine = input.readLine()) != null) {
                currentLine = currentLine.toLowerCase();
                if (!currentLine.isEmpty() && (currentLine.charAt(0) >= 'a' && currentLine.charAt(0) <= 'z')) {
                    Word temp = new Word();
                    temp.word = currentLine;
                    temp.count = 1;
                    inputData.add(temp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(inputData.size() + " words read.");

        // Linear Unsorted Search
        System.out.println("Unsorted Array");
        UnsortedArray ua = new UnsortedArray(inputData);
        ua.go();
        System.out.println("");
        // Sorted Search (Binary Search)
        System.out.println("Sorted Array");
        SortedArray sa = new SortedArray(inputData);
        sa.go();
        System.out.println("");
        // Binary Search Tree
        System.out.println("Binary Tree");
        BinarySearchTree bt = new BinarySearchTree(inputData);
        bt.go();
        System.out.println("");
        // Hash Map
        System.out.println("Hash Table");
        HashTable ht = new HashTable(inputData);
        ht.go();
        System.out.println("");
        System.out.println("All tests complete.");
    }
}
