package seminar1.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<Item> implements IDeque<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void pushFront(Item item) {
        if (head==null) {
            head=tail=new Node<>(item);
        } else {
            Node<Item> newHead = new Node<Item>(item,head,null);
            head.prev=newHead;
            head=newHead;
        }
        size++;
    }

    @Override
    public void pushBack(Item item) {
        if (tail==null) {
            tail=head=new Node<>(item);
        } else {
            Node<Item> newTail = new Node<Item>(item,null,tail);
            tail.next=newTail;
            tail = newTail;
        }
        size++;
    }

    @Override
    public Item popFront() {
        if (head==null) {
            return null;
        } else {
            Item item = head.item;
            if (head.next==null) {
                head = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            size--;
            return item;
        }

    }

    @Override
    public Item popBack() {
        if (tail==null) {
            return null;
        } else {
            Item item = tail.item;
            if (tail.prev==null) {
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            size--;
            return item;
        }
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Item> {
        Node<Item> currentNode = new Node<>(null,head,null);
        @Override
        public boolean hasNext() {
            return currentNode.next!=null;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                currentNode = currentNode.next;
                return currentNode.item;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
