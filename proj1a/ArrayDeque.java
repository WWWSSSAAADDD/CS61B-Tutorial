public class ArrayDeque<T> {
    /**Invariants:
     * 1.First = null -> 0 -> 7 : First = 偏移 + DequeSize % DequeSize; 偏移 <= 0 ：指向First元素的index
     * 2.Last = null -> 0 -> 1 : Last = 偏移 + DequeSize % DequeSize; 偏移 >= 0 ： 指向Last元素的index
     * 3.addFirst: 重新赋值First值；在First的位置插入元素；修改size大小
     * 4.addLast: 重新赋值Last值； 在Last的位置插入元素；修改size大小
     * 5.size: (DequeSize - First) % DequeSize + Last + 1
     * 6.Deque: 以数组的形式存储着元素
     * */

    /** 一些维护的变量*/
    private int dequeSize = 8;
    private T[] deque;
    private int size;
    private int firstPos;
    private int lastPos;

    /** 一些方法*/
    public ArrayDeque() {
        deque = (T[]) new Object[dequeSize];
        size = 0;
        firstPos = 1;
        lastPos = -1;
    }

    /** 返回Deque的size*/
    public int size() {
        return size;
    }

    /** 返回Deque是否为空*/
    public boolean isEmpty() {
        return size() == 0;
    }

    /** （只有当size == DequeSize时候才调用）重新设置大小*/
    private void resize(int newSize) {
        T[] newDeque = (T[]) new Object[newSize];
        int curPos = firstPos;

        for (int i = 0; i < dequeSize; i++) {
            newDeque[i] = deque[curPos];
            curPos = (curPos + 1) % dequeSize;
        }

        for (int i = 0; i < dequeSize; i++) {
            deque[i] = null;
        }

        firstPos = 0;
        lastPos = dequeSize - 1;
        dequeSize = newSize;
        deque = newDeque;
    }

    /** 向Deque的开头添加元素*/
    public void addFirst(T item) {
        if (isEmpty()) {
            deque[0] = item;
            firstPos = 0;
            lastPos = 0;
        } else {
            if (size() == dequeSize) {
                resize(dequeSize * 2);
            }
            firstPos = (firstPos - 1 + dequeSize) % dequeSize;

            deque[firstPos] = item;
        }
        size += 1;
    }

    /** 向Deque的末尾添加元素*/
    public void addLast(T item) {
        if (isEmpty()) {
            deque[0] = item;
            firstPos = 0;
            lastPos = 0;
        } else {
            if (size() == dequeSize) {
                resize(dequeSize * 2);
            }
            lastPos = (lastPos + 1) % dequeSize;

            deque[lastPos] = item;
        }
        size += 1;
    }

    /** 去除第一个元素，并且返回该元素值*/
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T returnVal = deque[firstPos];
            deque[firstPos] = null;

            if (size() == 1) {
                firstPos = 1;
                lastPos = -1;
            } else {
                firstPos = (dequeSize + firstPos + 1) % dequeSize;
            }
            size -= 1;

            return returnVal;
        }
    }

    /** 去除最后一个元素，并且返回该元素值*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T returnVal = deque[lastPos];
            deque[lastPos] = null;

            if (size() == 1) {
                firstPos = 1;
                lastPos = -1;
            } else {
                lastPos = (dequeSize + lastPos - 1) % dequeSize;
            }

            size -= 1;

            return returnVal;
        }
    }

    /** 返回Deque的index处的元素*/
    public T get(int index) {
        if (index > size() - 1) {
            return null;
        } else {
            int curPos = firstPos;
            for (int i = 0; i < index; i++) {
                curPos = (curPos + 1) % dequeSize;
            }
            return deque[curPos];
        }
    }

    /** 从Deque的first开始打印到last的所有item，每个item间隔一个空格*/
    public void printDeque() {
        if (isEmpty()) {
            return;
        } else {
            System.out.print(deque[firstPos]);
            int curPos = firstPos;
            for (int i = 1; i < size(); i++) {
                curPos = (curPos + 1) % dequeSize;
                System.out.print(" " + deque[curPos]);
            }

        }
    }
}
