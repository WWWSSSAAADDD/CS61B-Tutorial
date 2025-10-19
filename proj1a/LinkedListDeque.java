public class LinkedListDeque<T>{
    /** 用于实现LinkListDeque的嵌套类*/
    private class ListNode<T> {
        private T item;
        private ListNode<T> pre;
        private ListNode<T> next;
        ListNode() {
            item = null;
            pre = null;
            next = null;
        }
        ListNode(T item, ListNode<T> pre, ListNode<T> next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    /** 需要维护的数据*/
    private ListNode<T> sentinel;
    private int size;

    /** 类的方法*/
    /** 创建空双端列表*/
    public LinkedListDeque() {
        sentinel = new ListNode<T>(null, sentinel, sentinel);
        size = 0;
    }

    /** 返回双端列表的大小*/
    public int size() {
        return size;
    }

    /** 返回双端列表是是否为空*/
    public boolean isEmpty() {
        return size == 0;
    }

    /** 向双端列表的开头添加节点*/
    public void addFirst(T item) {
//        ListNode<T> newNode = new ListNode<>(item, sentinel, sentinel.next);
        if (isEmpty()) {
            ListNode<T> newNode = new ListNode<>(item, sentinel, sentinel);
            sentinel.next = newNode;
            sentinel.pre = newNode;
        } else {
            ListNode<T> newNode = new ListNode<>(item, sentinel, sentinel.next);
            sentinel.next.pre = newNode;
            sentinel.next = newNode;
        }
        size += 1;
    }

    /** 向双端队列的末尾添加节点*/
    public void addLast(T item) {
        if (isEmpty()) {
            addFirst(item);
        } else {
            ListNode<T> newNode = new ListNode<>(item, sentinel.pre, sentinel);
            sentinel.pre.next = newNode;
            sentinel.pre = newNode;
            size += 1;
        }
    }

    /** 从双端队列中将第一个节点移除，并且返回该节点的元素*/
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            ListNode<T> nextNode = sentinel.next.next;
            nextNode.pre = sentinel;

            T returnVal = sentinel.next.item;

    //      remove(sentinel.next);

            sentinel.next = nextNode;

            size -= 1;
            return returnVal;
        }
    }

//    /** 将某一节点的作用效果移除*/
//    private void remove(ListNode<T> toBeRemoved) {
//        toBeRemoved.next = null;
//        toBeRemoved.pre = null;
//    }

    /** 从双端队列中将最后一个节点移除，并且返回该节点的元素*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            ListNode<T> newLastNode = sentinel.pre.pre;
            newLastNode.next = sentinel;

            T returnVal = sentinel.pre.item;
            //remove(sentinel.pre);

            sentinel.pre = newLastNode;
            size -= 1;
            return returnVal;
        }
    }

    /** 迭代实现，输入index返回该index处的item*/
    public T get(int index) {
        if (index > size() - 1) {
            return null;
        } else {
            ListNode<T> cur = sentinel.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.item;
        }
    }

    /** 递归实现，输入index返回该index处的item*/
    public T getRecursive(int index) {
        if (index > size() - 1) {
            return null;
        } else {
            return getRecursive(index, sentinel.next);
        }
    }

    private  T getRecursive(int index, ListNode<T> cur) {
        if (cur == null) {
            return null;
        } else if (index == 0) {
            return cur.item;
        } else {
            return getRecursive(index - 1, cur.next);
        }
    }

    /** 从头到尾输出Deque的item，每个item之间间隔一个空格*/
    public void printDeque() {
        if (size() == 0) {
            return;
        } else {
            System.out.print(sentinel.next.item);
            ListNode<T> cur = sentinel.next.next;
            for (int i = 1; i < size(); i++) {
                System.out.print(" " + cur.item);
                cur = cur.next;
            }
        }
    }

}
