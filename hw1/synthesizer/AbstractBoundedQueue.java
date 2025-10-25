package synthesizer;

public abstract class  AbstractBoundedQueue<T> implements BoundedQueue<T> {
    //protected访问权限：最多到 不同包的子类 可以访问，而不同包的无关类（如不同包的main类中，不可读取这个数据）
    protected int fillCount;
    protected int capacity;
    public int capacity() {
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }

}
