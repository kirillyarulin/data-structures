package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class CyclicArrayQueue<Item> implements IQueue<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    private Item[] elementData=(Item[]) new Object[DEFAULT_CAPACITY];
    private int head=0;
    private int tail=0;
    private int size=0;


    @Override
    public void enqueue(Item item) {
        elementData[tail]=item;
        size++;
        tail++;
        if (size == elementData.length) {
            grow();
        } else if (tail==elementData.length) {
            tail=0;
        }
    }

    @Override
    public Item dequeue() {
        if (size == 0) { return null; }
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
        Item[] newArray = (Item[]) new Object[newSize];
        int i = 0;

        if (head<tail) {
            System.arraycopy(elementData,head,newArray,0,size);
        } else {
            System.arraycopy(elementData,head,newArray,0,elementData.length-head);
            System.arraycopy(elementData,0,newArray,elementData.length-head,tail);
        }
        elementData=newArray;
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
            return currentPosition!=tail;
        }

        @Override
        public Item next() {
            Item item = elementData[currentPosition];
            if (currentPosition==elementData.length-1) {
                currentPosition=0;
            } else {
                currentPosition++;
            }
            return item;
        }
    }
}
