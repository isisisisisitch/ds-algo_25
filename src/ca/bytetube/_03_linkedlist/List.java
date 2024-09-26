package ca.bytetube._03_linkedlist;

public interface List<E> {

    public static final int ELEMENT_NOT_FOUND = -1;
    // Number of elements
    int size();


    // Is it empty
    boolean isEmpty();


    // Clear all the elements
    void clear();


    // Add elements to the end
    void add(E element);

    // Add elements to the index position
    public void add(int index, E element);


    // Returns the element corresponding to the index position
    E get(int index);


    // Set the element at the index position
    E set(int index, E element);

    // Delete elements to the index position
    E remove(int index);


    // Return the index of the element
    int indexOf(E element);

    //Contains a certain element
    boolean contains(E element);


}
