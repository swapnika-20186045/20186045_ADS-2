/**
 * Min PQ.
 */
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for minimum pq.
 *
 * @param      <Key>  The key
 */
class MinPQ<Key> implements Iterable<Key> {
    /**
     * { var_description }.
     */
    private Key[] pq; // store items at indices 1 to n
    /**
     * { var_description }.
     */
    private int n; // number of items on priority queue
    /**
     * { var_description }.
     */
    private Comparator<Key> comparator;  // optional comparator
    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param      initCapacity  the initial capacity of this priority queue
     */
    public MinPQ(final int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }
    /* Initializes an empty priority queue with the given initial capacity,
    * using the given comparator.
    *
    * @param  initCapacity the initial capacity of this priority queue
    * @param  comparator the order in which to compare the keys
    */
    public MinPQ(final int initCapacity, final Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MinPQ() {
        this(1);
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return     {@code true} if this priority queue is empty; {@code false}
     *             otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return     the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns a smallest key on this priority queue.
     *
     * @return     a smallest key on this priority queue
     * @throws     NoSuchElementException  if this priority queue is empty
     */
    public Key min() {
        return pq[1];
    }


    /**
     * {helper function to double the size of the heap array}.
     *
     * @param      capacity  The capacity
     */
    private void resize(final int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param      x     the key to add to this priority queue
     */
    public void insert(final Key x) {
        // double size of array if necessary
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return     a smallest key on this priority queue
     * @throws     NoSuchElementException  if this priority queue is empty
     */
    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException(
                "Priority queue underflow");
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        // to avoid loiterig and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }


    /**************************************************************
     * Helper functions to restore the heap invariant.
     **************************************************************/

    /**
     * { function_description }.
     *
     * @param      k     { parameter_description }
     */
    private void swim(final int a) {
        int k = a;
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    /**
     * { function_description }.
     *
     * @param      k     { parameter_description }
     */
    private void sink(final int a) {
        int k = a;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    /**
     * { function_description }.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean greater(final int i, final int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }
    /**
     * { function_description }.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     */
    private void exch(final int i, final int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    /**
     * Determines if minimum heap.
     *
     * @return     True if minimum heap, False otherwise.
     */
    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    /**
     * Determines if minimum heap.
     *
     * @param      k     { parameter_description }
     *
     * @return     True if minimum heap, False otherwise.
     */
    private boolean isMinHeap(final int k) {
        if (k > n) {return true;}
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left  <= n && greater(k, left))  {return false;}
        if (right <= n && greater(k, right)) {return false;}
        return isMinHeap(left) && isMinHeap(right);
    }
    /**
     * Returns an iterator that iterates over the keys on this priority queue in
     * ascending order. <p> The iterator doesn't implement {@code remove()}
     * since it's optional.
     *
     * @return     an iterator that iterates 9over the keys in ascending order
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    /**
     * Class for heap iterator.
     */
    private class HeapIterator implements Iterator<Key> {
        /**
         * { var_description }.
         */
        private MinPQ<Key> copy;
        /**
         * Constructs the object.
         */
        public HeapIterator() {
            if (comparator == null) {
                copy = new MinPQ<Key>(size());
            } else {
                copy = new MinPQ<Key>(size(), comparator);
            }
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }

        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return !copy.isEmpty();
        }
        /**
         * { function_description }.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * { function_description }.
         *
         * @return     { description_of_the_return_value }
         */
        public Key next() {
            return copy.delMin();
        }
    }
}
