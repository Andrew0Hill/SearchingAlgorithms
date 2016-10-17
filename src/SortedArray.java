
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Andrew_2 on 10/13/2016.
 */
public class SortedArray {
    static ArrayList<Word> words;
    static int comparisons;
    static int insertions;
    static int wordsProcessed;
    static DynamicArray<Word> concordance;

    SortedArray(ArrayList<Word> input) {
        words = new ArrayList<>();
        concordance = new DynamicArray<>(10000);
        comparisons = 0;
        insertions = 0;
        wordsProcessed = 0;
        words = input;
    }

    public static void go() {
        // Loop through each word, call the insert() function on it.
        for (Word w : words) {
            ++wordsProcessed;
            insert(0, concordance.size() - 1, w);
            if (wordsProcessed % 1000 == 0) {
                //System.out.println(wordsProcessed + " insertions.");
            }
        }
        // Print the number of unique words.
        System.out.println(concordance.size() + " unique words.");
        // Array is already sorted, so we can just print the first and last
        // ten elements in the array + their counts.
        for (int i = 0; i < 10; ++i) {
            System.out.println("\'" + concordance.get(i).word + "\' " + ": " + concordance.get(i).count);
        }
        System.out.println();
        for (int i = concordance.size() - 11; i < concordance.size(); ++i) {
            System.out.println("\'" + concordance.get(i).word + "\' " + ": " + concordance.get(i).count);
        }
        // Print the number of assignments and comparisons performed.
        System.out.println(comparisons + " comparisons performed.");
        System.out.println(concordance.assignments + " assignments performed.");
    }

    private static void insert(int startIndex, int endIndex, Word w) {
        // Calculate the middle based on start and end indices.
        int middle = (startIndex + endIndex) / 2;
        // Create a temp variable to hold word info.
        Word temp = new Word();
        temp.word = w.word;
        // If startIndex > endIndex, the search has failed.
        // This means we need to insert the element at startIndex.
        if (startIndex > endIndex) {
            temp.count = 1;
            concordance.add(startIndex, temp);
        }
        // Check if the middle value equals the value we're looking for.
        else if (concordance.get(middle).equals(w)) {
            // If it does, increment the count for this word.
            ++comparisons;
            temp.count = concordance.get(middle).count + 1;
            concordance.set(middle, temp);
        }
        // If value is less than middle, call the function starting
        // at the beginning, and ending before the middle value.
        else if (concordance.get(middle).greaterThan(w)) {
            ++comparisons;
            insert(startIndex, middle - 1, w);
        }
        // If the value is greater than middle, call the function starting
        // at the middle+1, and ending at the endIndex.
        else {
            insert(middle + 1, endIndex, w);
        }
    }

}
