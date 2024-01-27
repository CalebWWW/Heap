package impl;
/**
 * PQQueue.java
 *
 * Class to implement a queue using a priority queue.
 * 
 * CS 345, Wheaton College
 * Originally for CSCI 245, Spring 2007
 * Revised Jan 4, 2016
 */

import java.util.Comparator;
import java.util.NoSuchElementException;

import adt.FullContainerException;
import adt.Map;
import adt.Queue;
//import test.CollectionTest.MeasEq;

public class PQQueue<E> implements Queue<E> {

    /**
     * The priority queue to use as an internal representation.
     */
   private HeapPriorityQueue<E> pq;

    /**
     * Used to see the lifespan of each new object
     */
    int life = 2147483647;
    
    
    /**
     * Place to store data associated with representative
     * values in the priority queue.
     */
    private Map<E, Integer> arrivalTimes;
    
    public class MapComparator implements Comparator<E> {
		@Override
		public int compare(E o1, E o2) {
			return arrivalTimes.get(o1).compareTo(arrivalTimes.get(o2));
		}
    }

    /**
     * Constructor.
     * @param maxSize The capacity of this queue.
     */
    public PQQueue(int maxSize) {
        arrivalTimes = new ListMap<E, Integer>();
        MapComparator compy = new MapComparator();
        pq = new HeapPriorityQueue<E>(maxSize, compy);
        life = 0;
    }

    /**
     * Is this queue empty? It is if the pq is empty.
     * @return True if this is empty, false otherwise.
     */
    public boolean isEmpty() { return pq.isEmpty(); }

    /**
     * Is this queue full? It is if the pq is full.
     * @return True if this is full, false otherwise.
     */
    public boolean isFull() { return pq.isFull(); }

    /**
     * Retrieve (but do not remove) the front element of this queue.
     * @return The front element.
     */
    public E front() { 
    	if(!isEmpty()) {
    		//System.out.println("front  "+ pq.max());
    		return pq.max();
    	}
        throw new NoSuchElementException();
    }

    /**
     * Retrieve and remove the front element of this queue.
     * @return The front element.
     */
    public E remove() {
    	if(!isEmpty()) {
    	E temp = pq.max();
        //System.out.println("dequeue"+ temp);
        pq.extractMax();
        return temp;
    	}
    	throw new NoSuchElementException();
    }

    /**
     * Add an element to the back of this queue.
     * @param x The element to add.
     */
    public void enqueue(E x) {
        if (isFull()) throw new FullContainerException();
        	//System.out.println("enqueue  "+ x);
        	arrivalTimes.put(x, life);
        	life--;
        	pq.insert(x);
    }
}
