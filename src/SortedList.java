import java.util.ArrayList;

/**
 * Created by Andrew_2 on 10/14/2016.
 */
public class SortedList {
    static ArrayList<Word> words;
    static int comparisons;
    static int assignments;
    static int insertions;
    static int wordsProcessed;
    static ArrayList<Word> concordance;
    static Node head;
    SortedList(ArrayList<Word> input){
        words = input;
        comparisons = 0;
        assignments = 0;
        insertions = 0;
        wordsProcessed = 0;
        head = new Node();
    }
    public static void go(){
        for(Word w : words){
            ++wordsProcessed;
            insert(head,w);
            if(wordsProcessed % 1000 == 0){
                System.out.println(wordsProcessed + " wordsProcessed");
            }
        }
        System.out.println("Done");
    }
    private static void insert(Node n, Word w) {
        Node temp = new Node();
        temp.data = w;
        temp.data.count = 1;
        if(n.next == null){
            n.next = new Node();
        }
        if (n.data == null) {
            head = temp;
        }
        else {
            while (n.next.data != null && n.next.data.lessThan(w)) {
                n = n.next;
            }
            if(n.next.data != null && n.next.data.equals(w)){
                int newCount = n.next.data.count+1;
                Word tempWord = new Word();
                tempWord.count = newCount;
                tempWord.word = w.word;
                n.data = tempWord;
            }
            else {
                temp.next = n.next;
                n.next = temp;
            }
        }
    }
    private static class Node{
        Node next;
        Word data;
    }
}
