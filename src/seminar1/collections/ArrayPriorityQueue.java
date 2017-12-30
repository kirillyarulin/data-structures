package seminar1.collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

    private static final int DEFAULT_CAPACITY = 10;

    private Key[] elementData;
    private Comparator<Key> comparator;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue() {
        elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue(Comparator<Key> comparator) {
        elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }


    @Override
    public void add(Key key) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = key;
        size++;
        siftUp();
    }

    @Override
    public Key peek() {
        return elementData[0];
    }

    @Override
    public Key extractMin() {
        if (isEmpty()) {
            return null;
        }
        Key minElement = elementData[0];
        elementData[0] = elementData[--size];
        if (size() < elementData.length / 4) {
            shrink();
        }
        siftDown();
        return minElement;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    private void siftUp() {
        int i = size - 1;

        while (true) {
            int parent = (i - 1) / 2;

            if (i > 0 && greater(parent, i)) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown() {
        int i = 0;
        int min = i;


        while (true) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            if (leftChild < size && greater(min, leftChild)) {
                min = leftChild;
            }

            if (rightChild < size && greater(min, rightChild)) {
                min = rightChild;
            }

            if (min != i) {
                swap(i, min);
                i = min;
            } else {
                break;
            }
        }

    }

    private void swap(int a, int b) {
        Key tmp = elementData[a];
        elementData[a] = elementData[b];
        elementData[b] = tmp;
    }


    private void grow() {
        elementData = Arrays.copyOf(elementData, (int) (elementData.length * 1.5));
    }

    private void shrink() {
        elementData = Arrays.copyOf(elementData, elementData.length / 2);
    }

    private boolean greater(int i, int j) {
        return comparator == null
                ? elementData[i].compareTo(elementData[j]) > 0
                : comparator.compare(elementData[i], elementData[j]) > 0
                ;
    }

    @Override
    public Iterator<Key> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Key> {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Key next() {
            if (hasNext()) {
                return elementData[i++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
