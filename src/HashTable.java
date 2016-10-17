import java.util.ArrayList;
import java.util.Collections;
import java.util.jar.Pack200;

/**
 * Created by Andrew_2 on 10/13/2016.
 */
public class HashTable {
    public static ArrayList<ArrayList<Word>> concordance;
    public static ArrayList<Word> words;
    public static ArrayList<Word> outputSorted;
    static int comparisons;
    static int assignments;
    static int collisions;

    HashTable(ArrayList<Word> input) {
        // This size of ArrayList should give us no collisions.
        concordance = new ArrayList<>(input.size() / 2);
        outputSorted = new ArrayList<>();
        // Initialize the array to blank values.
        for (int i = 0; i < input.size(); ++i) {
            concordance.add(new ArrayList<>());
        }
        // Assign the input values to an array.
        words = input;
    }

    public static void go() {
        // Loop through each word in the input.
        for (Word w : words) {
            // Get the index by using the Word class' hash function,
            // do modular division by the size of the hashTable to
            // get an index that fits.
            int index = Math.abs(w.hashCode() % (words.size() / 2));
            // If the current index position is null, place the value here.
            if (concordance.get(index).size() == 0) {
                ++assignments;
                concordance.get(index).add(w);
            }
            // Otherwise, we increment the count for this word.
            else {
                Word temp = new Word();
                temp.word = w.word;
                boolean exists = false;
                // We search through all values held at this index.
                for (int i = 0; i < concordance.get(index).size(); ++i) {
                    ++comparisons;
                    if (concordance.get(index).get(i).word.equals(temp.word)) {
                        ++assignments;
                        temp.count = concordance.get(index).get(i).count + 1;
                        concordance.get(index).set(i, temp);
                        exists = true;
                        break;
                    }

                }
                // Collision has occurred.
                if (!exists) {
                    ++collisions;
                    //System.out.println("Collision occurred at index " + index +".");
                    // System.out.println("Word at index is " + concordance.get(index).get(0).word + " but word to be inserted is " + w.word);
                    ++assignments;
                    concordance.get(index).add(w);
                }
            }
        }
        // We remove all Words with null values, then sort the array.
        ArrayList<Word> temp = new ArrayList<>();
        concordance.removeAll(Collections.singleton(temp));
        for (ArrayList<Word> e : concordance) {
            for (int i = 0; i < e.size(); ++i) {
                outputSorted.add(e.get(i));
            }
        }
        Collections.sort(outputSorted);
        // Now we print out the number of unique words, and the first and last ten words,
        System.out.println(outputSorted.size() + " unique words.");
        for (int i = 0; i < 10; ++i) {
            System.out.println("\'" + outputSorted.get(i).word + "\' " + ": " + outputSorted.get(i).count);
        }
        System.out.println();
        for (int i = outputSorted.size() - 11; i < outputSorted.size(); ++i) {
            System.out.println("\'" + outputSorted.get(i).word + "\' " + ": " + outputSorted.get(i).count);
        }
        // Print out the number of comparisons and assignments performed.
        System.out.println(comparisons + " comparisons performed.");
        System.out.println(assignments + " assignments performed.");
        System.out.println(collisions + " collisions occurred while inserting");
    }
}
