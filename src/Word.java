/**
 * Created by Andrew_2 on 10/13/2016.
 */
public class Word implements Comparable<Word>{
    public String word;
    public int count;
    @Override
    public int compareTo(Word w){
        return this.word.compareTo(w.word);
    }
    @Override
    public int hashCode(){
        return (this.word.hashCode());
    }
    @Override
    public boolean equals(Object obj){
        if(obj.getClass() != this.getClass()){
            return false;
        }else{
            Word temp = (Word)obj;
            if(temp.word == null && this.word == null){
                return true;
            }
            else if (temp.word == null || this.word == null){
                return false;
            }
            else {
                return (temp.word.equals(this.word));
            }
        }
    }
    public boolean greaterThan(Word w){
        return (this.word.compareTo(w.word) >= 1);
    }
    public boolean lessThan(Word w){
        return (this.word.compareTo(w.word) <= -1);
    }
}
