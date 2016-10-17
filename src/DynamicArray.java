import java.util.Arrays;

/**
 * Created by Andrew_2 on 10/15/2016.
 */
public class DynamicArray<E> {
    Object array[];
    private int currentSize;
    public int assignments;

    DynamicArray(int initialSize) {
        array = new Object[initialSize];
        currentSize = 0;
    }

    public void add(E w) {
        // If the insertion would make the array larger than its capacity,
        // We allocate a larger array and copy the old stuff over.
        if (currentSize + 1 > array.length) {
            Word[] newArray = new Word[2 * array.length];
            System.arraycopy(array, 0, newArray, 0, array.length);
            assignments += array.length;
            array = newArray;
        }
        array[currentSize++] = w;
        ++assignments;
    }

    // Adds a new element at the given index. Shifts all elements forward one position to make
    // room for the new element.
    public void add(int index, E w) {
        // Same as above. If the shift of elements would go out of bounds,
        // we allocate a new array of double size and copy everything over.
        if (currentSize + 1 > array.length) {
            Word[] newArray = new Word[2 * array.length];
            System.arraycopy(array, 0, newArray, 0, array.length);
            assignments += array.length;
            array = newArray;
        }
        // Use the arraycopy() method for ease of use. currentSize-index is the number
        // of elements that are shifted during the operation.
        System.arraycopy(array, index, array, index + 1, currentSize - index);
        array[index] = w;
        ++currentSize;
        // Increment the assignments variable by the number of elements shifted.
        assignments += (currentSize - index);
    }

    public E get(int index) {
        return (E) array[index];
    }

    public void set(int index, E newWord) {
        // Sets the array value at index to newWord.
        array[index] = newWord;
        ++assignments;
    }

    public int size() {
        // Returns the size.
        return currentSize;
    }

    public void sort() {
        // Sorts the array.
        Arrays.sort(array, 0, currentSize);
    }
}
