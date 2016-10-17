import java.util.Arrays;

/**
 * Created by Andrew_2 on 10/14/2016.
 */
public class DynamicArray<E>  {
    Object[] array;
    int currentSize;
    int lastIndex;
    DynamicArray(int initialSize){
        array = new Object[initialSize];
        currentSize = 0;
        lastIndex = 0;
    }

    public void add(int index, Object object){
        rangeCheckAdd(index);

        if (array[index] == null){
            array[index] = object;
        }else{

        }
    }
    public void add(Object object){
        if(currentSize+1 > array.length){
            resizeArray();
        }
        array[currentSize]

    }
    public void rangeCheck(int index){
        if (index >= array.length){
            throw new IndexOutOfBoundsException("Out of bounds at " + index);
        }
    }
    public void rangeCheckAdd(int index){
        if (index > array.length || index < 0){
            throw new IndexOutOfBoundsException("Out of bounds at " + index);
        }
    }
    private void freeIndex(int index){
        if(currentSize+1 > array.length){
            resizeArray();
        }
        for(int i = index; i < currentSize+1; ++i){

        }
    }
    private void resizeArray(){
        Object[] newArray = Arrays.copyOf(array,2*array.length);
        array = newArray;
    }
}
