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

    @SuppressWarnings("unchecked")
    private ArrayPriorityQueue(ArrayPriorityQueue queue) {
        this.elementData = (Key[]) new Comparable[queue.size];
        for (int i = 0;i<elementData.length;i++) {
            this.elementData[i]=(Key)queue.elementData[i];
        }
        this.comparator = (Comparator<Key>) queue.comparator;
        this.size = queue.size();
    }

    @Override
    public void add(Key key) {
        if (size == elementData.length) grow();
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
        if (isEmpty()) return null;
        Key minElement = elementData[0];
        elementData[0] = elementData[--size];
        if (size() < elementData.length / 4) shrink();
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
            if (i > 0 && greater((i - 1) / 2, i)) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            } else {
                break;
            }
        }
    }

    private void siftDown() {
        int i = 0;
        int min = i;

        while (true) {
            if (2 * i + 1 < size && greater(min, 2 * i + 1)) min = 2 * i + 1;
            if (2 * i + 2 < size && greater(min, 2 * i + 2)) min = 2 * i + 2;
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
        IPriorityQueue<Key> dupQueue;

        public Itr() {
            dupQueue = new ArrayPriorityQueue<>(ArrayPriorityQueue.this);
        }

        @Override
        public boolean hasNext() {
            return !dupQueue.isEmpty();
        }

        @Override
        public Key next() {
            if (hasNext()) {
                return dupQueue.extractMin();
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
