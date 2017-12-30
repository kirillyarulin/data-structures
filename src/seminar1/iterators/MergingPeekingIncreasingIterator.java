package seminar1.iterators;

import seminar1.collections.ArrayPriorityQueue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор возвращающий последовательность из N возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(n + k * log n),
 *  n — количество итераторов
 *  k — суммарное количество элементов
 */
public class MergingPeekingIncreasingIterator implements Iterator<Integer> {

    private Comparator<PeekingIncreasingIterator> comparator = (p1, p2) -> p1.peek().compareTo(p2.peek());

    private ArrayPriorityQueue<PeekingIncreasingIterator> priorityQueue = new ArrayPriorityQueue<>(comparator);


    public MergingPeekingIncreasingIterator(IPeekingIterator... peekingIterator) {
        for (IPeekingIterator x : peekingIterator){
            priorityQueue.add((PeekingIncreasingIterator) x);
        }
    }

    @Override
    public boolean hasNext() {
        return priorityQueue.size()>0;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            Integer t = priorityQueue.peek().next();
            if (!priorityQueue.peek().hasNext()) {
                priorityQueue.extractMin();
            }
            return t;
        } else {
            throw new NoSuchElementException();
        }

    }
}
