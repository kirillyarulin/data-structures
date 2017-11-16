package seminar1.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoStackQueue<Item> implements IQueue<Item> {

    private IStack<Item> stack1;
    private IStack<Item> stack2;

    public TwoStackQueue() {
        stack1 = new ArrayStack<>();
        stack2 = new ArrayStack<>();
    }

    @Override
    public void enqueue(Item item) {
        stack1.push(item);
    }

    @Override
    public Item dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) stack2.push(stack1.pop());
        }
        return (stack2.isEmpty()) ? null : stack2.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    @Override
    public int size() {
        return stack1.size()+stack2.size();
    }

    @Override
    public Iterator<Item> iterator() {

        return new TwoStackQueueIterator();
    }

    private class TwoStackQueueIterator implements Iterator<Item> {
        Iterator<Item> iterator2 = stack2.iterator();
        Iterator<Item> iterator1;


        TwoStackQueueIterator() {
            IStack<Item> tmpStack = new ArrayStack<>();
            for (Item x : stack1) tmpStack.push(x);
            iterator1 = tmpStack.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator2.hasNext() || iterator1.hasNext();
        }

        @Override
        public Item next() {
            if (iterator2.hasNext()) {
                return iterator2.next();
            } else {
                if (iterator1.hasNext()) {
                    return iterator1.next();
                } else {
                    throw new NoSuchElementException();
                }
            }

        }
    }

}
