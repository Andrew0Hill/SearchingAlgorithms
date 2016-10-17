import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew_2 on 10/13/2016.
 */
public class UnsortedArray {
    static ArrayList<Word> words;
    static Word[] concordance2;
    static ArrayList<Word> concordance;
    static int currentIndex;
    static int comparisons;
    static int assignments;
    UnsortedArray(ArrayList<Word> input){
        words = new ArrayList<>();
        concordance = new ArrayList<>();
        concordance2 = new Word[10000];
        comparisons = 0;
        currentIndex = 0;
        words = input;
    }
    public static void go(){
        int wordsProcessed = 0;
        for(Word w : words){
            ++wordsProcessed;
            int result = contains(w);
            // Word doesn't exist
            if(result == -1){
                concordance.add(w);
            }
            // Word already exists
            else{
                Word temp = new Word();
                temp.word = concordance.get(result).word;
                temp.count = concordance.get(result).count+1;
                concordance.set(result,temp);
            }
            if(wordsProcessed % 1000 == 0){
                System.out.println(wordsProcessed + " words processed.");
            }
        }

        System.out.println("Done. " + concordance.size() + " unique words.");
        Collections.sort(concordance);

        for(int i = 0; i < 10; ++i){
            System.out.println("\'" + concordance.get(i).word + "\' " +": " + concordance.get(i).count);
        }
        System.out.println();
        for(int i = concordance.size()-11; i < concordance.size(); ++i){
            System.out.println("\'" + concordance.get(i).word + "\' " +": " + concordance.get(i).count);
        }
        System.out.println(comparisons + " comparisons performed.");
    }
    public static int contains(Word w){
        for(int i = 0; i < concordance.size(); ++i){
            ++comparisons;
            if(concordance.get(i).word.equals(w.word)){
                return i;
            }
        }
        return -1;
    }
}
