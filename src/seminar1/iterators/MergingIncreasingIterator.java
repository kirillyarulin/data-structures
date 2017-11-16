package seminar1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(k),
 *  k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {

    private IncreasingIterator first;
    private IncreasingIterator second;
    private Integer firstNum;
    private Integer secondNum;

    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;
        /* TODO: implement it */
    }

    @Override
    public boolean hasNext() {
        //todo imp
        return first.hasNext() || second.hasNext();
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            if (firstNum == null && first.hasNext()) firstNum = first.next();
            if (secondNum == null && second.hasNext()) secondNum = second.next();

            if (firstNum == null) return second.next();
            if (secondNum == null) return first.next();

            int minNum;

            if (firstNum < secondNum) {
                minNum = firstNum;
                firstNum = null;
            } else {
                minNum = secondNum;
                secondNum = null;
            }
            return minNum;
        } else {
            throw new NoSuchElementException();
        }
    }
}
