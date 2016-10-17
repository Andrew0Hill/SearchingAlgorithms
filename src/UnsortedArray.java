import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew_2 on 10/13/2016.
 */
public class UnsortedArray {
    static ArrayList<Word> words;
    static DynamicArray<Word> concordance;
    static int currentIndex;
    static int comparisons;
    UnsortedArray(ArrayList<Word> input){
        words = new ArrayList<>();
        concordance = new DynamicArray<>(10000);
        comparisons = 0;
        currentIndex = 0;
        words = input;
    }
    public static void go(){
        // Process each word in input
        for(Word w : words){
            int result = contains(w);
            // Word doesn't exist
            if(result == -1){
                concordance.add(w);
            }
            // Word already exists
            else{
                // Update the entry for that word with incremented
                // count.
                Word temp = new Word();
                temp.word = concordance.get(result).word;
                temp.count = concordance.get(result).count+1;
                concordance.set(result,temp);
            }
        }

        System.out.println("Done. " + concordance.size() + " unique words.");
        // Sort the concordance.
        concordance.sort();
        // Print out the first and last ten words in the concordance.
        for(int i = 0; i < 10; ++i){
            System.out.println("\'" + concordance.get(i).word + "\' " +": " + concordance.get(i).count);
        }
        System.out.println();
        for(int i = concordance.size()-11; i < concordance.size(); ++i){
            System.out.println("\'" + concordance.get(i).word + "\' " +": " + concordance.get(i).count);
        }
        // Print the number of comparisons. The number of assignments is held by the concordance.
        // It also counts the assignments that occur when the array is resized/shifted.
        System.out.println(comparisons + " comparisons performed.");
        System.out.println(concordance.assignments + " assignments performed.");
    }
    public static int contains(Word w){
        // Simple linear search algorithm. Look through
        // each element, return the index if we find it.
        for(int i = 0; i < concordance.size(); ++i){
            ++comparisons;
            if(concordance.get(i).word.equals(w.word)){
                return i;
            }
        }
        // -1 signals that the index doesn't exist in
        // this array.
        return -1;
    }
}
