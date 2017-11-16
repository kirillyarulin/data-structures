package seminar1.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void enqueue(Item item) {
        if (tail==null) {
            tail = head = new Node<>(item);
        } else {
            Node<Item> node = new Node<>(item);
            tail.next =node;
            tail=node;
        }
        size++;
    }

    @Override
    public Item dequeue() {
        if (head==null) return null;
        if (head==tail) {
            Item item = head.item;
            head=tail=null;
            size--;
            return item;
        } else {
            Item item = head.item;
            head=head.next;
            size--;
            return item;
        }

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {
        Node<Item> currentPosition = new Node<Item>(null,head);

        @Override
        public boolean hasNext() {
           return currentPosition.next!=null;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                currentPosition = currentPosition.next;
                return currentPosition.item;
            } else {
                throw new NoSuchElementException();
            }
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
