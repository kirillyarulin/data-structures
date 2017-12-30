package seminar1;

import seminar1.collections.*;
import seminar1.iterators.IncreasingIterator;
import seminar1.iterators.MergingIncreasingIterator;
import seminar1.iterators.MergingPeekingIncreasingIterator;
import seminar1.iterators.PeekingIncreasingIterator;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

//        System.out.println("LinkedStack");
//        IStack<Integer> stack = new LinkedStack<>();
//        for (int i = 0; i < 20; i++) {
//            stack.push(i);
//        }
//        for (int i : stack) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < 30; i++) {
//            System.out.print(stack.pop() + " ");
//        }
//        System.out.println();
//
//        System.out.println();
//-------------------------------------------------------
//        System.out.println("ArrayStack");
//        IStack<Integer> stack = new ArrayStack<>();
//        for (int i = 0; i < 20; i++) {
//            stack.push(i);
//        }
//        for (int i : stack) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < 30; i++) {
//            System.out.print(stack.pop() + " ");
//        }
//        System.out.println();
//
//        System.out.println();
//-------------------------------------------------------
/*        System.out.println("LinkedQueue");
        IQueue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
        for (int i : queue) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < 102; i++) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();

        System.out.println();*/
////-------------------------------------------------------
//        System.out.println("TwoStackQueue");
//        IQueue<Integer> queue2 = new TwoStackQueue<>();
//        for (int i = 0; i < 20; i++) {
//            queue2.enqueue(i);
//        }
//                for (int i : queue2) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < 30; i++) {
//            System.out.print(queue2.dequeue() + " ");
//        }
////-------------------------------------------------------
//        System.out.println("CyclicArrayDeque");
//        IDeque<Integer> dec = new CyclicArrayDeque<>() ;
//        for (int i = 0;i < 100;i++) {
//            dec.pushFront(i);
//            dec.pushBack(-i);
//        }
//
//        for (Integer x : dec) {
//            System.out.print(x + " ");
//        }
//        System.out.println();
//        System.out.println();
//        for (int i = 0;i<102;i++) {
//            System.out.print(dec.popFront() + " ");
//            System.out.print(dec.popBack() + " ");
//        }
//-------------------------------------------------------
//        System.out.println("ArrayPriorityQueue");
//        ArrayPriorityQueue<Integer> pq = new ArrayPriorityQueue<>();
//        for (int i = 0;i<10;i++) {
//            pq.add((int)(Math.random()*100));
//        }
//
//        for (Integer x: pq) {
//            System.out.print(x + " ");
//        }
//        System.out.println("\n");
//
//        for (int i = 0; i<20;i++) {
//            System.out.print(pq.extractMin() + " ");
//        }
//        System.out.println();
// -----------------------------------------------------------------
//        PeekingIncreasingIterator peekingIncreasingIterator1 = new PeekingIncreasingIterator(5,5,15);
//        PeekingIncreasingIterator peekingIncreasingIterator2 = new PeekingIncreasingIterator(5,10,15);
//        PeekingIncreasingIterator peekingIncreasingIterator3 = new PeekingIncreasingIterator(40,40,15);
//
//
//        MergingPeekingIncreasingIterator m = new MergingPeekingIncreasingIterator(peekingIncreasingIterator1,peekingIncreasingIterator2,peekingIncreasingIterator3);
//
//        while (m.hasNext()) {
//            System.out.print(m.next() + " ");
//        }
// -----------------------------------------------------------------
/*        IncreasingIterator increasingIterator1 = new IncreasingIterator(5,20,3);
        IncreasingIterator increasingIterator2 = new IncreasingIterator(5,10,2);

        *//*while (increasingIterator1.hasNext()) System.out.print(increasingIterator1.next() + " ");
        System.out.println();
        while (increasingIterator2.hasNext()) System.out.print(increasingIterator2.next() + " ");*//*

        MergingIncreasingIterator m = new MergingIncreasingIterator(increasingIterator1,increasingIterator2);
        while (m.hasNext()) {
            System.out.print(m.next() + " ");
        }*/



    }
}
