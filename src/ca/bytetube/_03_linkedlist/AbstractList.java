package ca.bytetube._03_linkedlist;

public abstract class AbstractList<E> implements List<E> {
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    // Add elements to the end
    public void add(E element) {
        add(size, element);
    }

    @Override
    //Contains a certain element
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index: " + index + ",size: " + size);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index + ",size: " + size);
    }

}
