package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class CyclicArrayDeque<Item> implements IDeque<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    private Item[] elementData=(Item[]) new Object[DEFAULT_CAPACITY];
    private int head=0;
    private int tail=0;
    private int size=0;

    @Override
    public void pushFront(Item item) {
        if (head==0) {
            head=elementData.length-1;
        } else {
            head--;
        }
        elementData[head]=item;
        size++;
        if (size==elementData.length) grow();
    }

    @Override
    public void pushBack(Item item) {
        elementData[tail++]=item;
        size++;
        if (size==elementData.length) {
            grow();
        } else if (tail==elementData.length) {
            tail = 0;
        }
    }

    @Override
    public Item popFront() {
        Item item = elementData[head];
        elementData[head]=null;
        size--;
        if (head==elementData.length-1) {
            head=0;
        } else {
            head++;
        }
        if (size<elementData.length/4 && elementData.length>DEFAULT_CAPACITY) shrink();
        return item;
    }

    @Override
    public Item popBack() {
        if (tail==0) {
            tail=elementData.length-1;
        } else {
            tail--;
        }
        Item item = elementData[tail];
        elementData[tail]=null;
        size--;
        if (size< elementData.length/4 && elementData.length>DEFAULT_CAPACITY) shrink();
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        changeSize((int)(elementData.length*1.5));
    }

    private void shrink() {
        changeSize(elementData.length/2);
    }

    @SuppressWarnings("unchecked")
    private void changeSize(int newSize) {
        if (head<tail) {
            elementData= Arrays.copyOf(Arrays.copyOfRange(elementData,head,tail),newSize);
        } else {
            Item[] newArray = (Item[]) new Object[newSize];
            int i = 0;
            for (int j = head;j<elementData.length;j++) {
                newArray[i]=elementData[j];
                i++;
            }
            for (int j = 0; j<tail;j++) {
                newArray[i]=elementData[j];
                i++;
            }
            elementData=newArray;
        }
        head=0;
        tail=size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Item> {
        private int currentPosition = head;

        @Override
        public boolean hasNext() {
            return currentPosition != tail;
        }

        @Override
        public Item next() {
            Item item = elementData[currentPosition];
            if (currentPosition == elementData.length - 1) {
                currentPosition = 0;
            } else {
                currentPosition++;
            }
            return item;
        }
    }
}
