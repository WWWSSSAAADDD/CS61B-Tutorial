// package <package name>;
package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        fillCount = 0;
        first = 0;
        last = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) throws RuntimeException {
        if (fillCount == capacity) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            fillCount++;
            rb[last] = x;
            last = (last + 1) % capacity;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() throws RuntimeException {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            fillCount--;
            T temp = rb[first];
            first = (first + 1) % capacity;
            return temp;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() throws RuntimeException {
        if (fillCount == 0) {
            return null;
        } else {
            return rb[first];
        }
    }

    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int relativePos;

        MyIterator() {
            relativePos = 0;
        }

        public boolean hasNext() {
            return relativePos < fillCount;
        }

        public T next() {
            int index = (first + relativePos) % capacity;
            T returnItem = rb[index];
            relativePos++;
            return returnItem;
        }
    }
}

