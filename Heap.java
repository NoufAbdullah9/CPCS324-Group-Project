/*CPCS 324: Algorithms and Data Structures (II)
Group Project 
Section: BAR
Team members
Sarah Hamoud Aljabri   - 1907215
Khadija Salem Balfagih - 1914895
Nouf Abdullah Alharbi  - 1906257 */
package cpcs324_project_part1;

import java.util.NoSuchElementException;

/**
 * @author KHADIJA.B, Nouf, Sarah
 * @param <E> generic class with type E (Element)
 */
public class Heap<E> {

    /**
     * capacity with randoly inital value
     */
    private final int INITIAL_CAPACITY = 15;

    /**
     * the number of integer in heap curuntly
     */
    private int numElements;

    /**
     *array of element
     */
    private E[] elements;

    /**
     *the constructor of heap
     */
    public Heap() {
        numElements = 0;
        elements = (E[]) (new Object[INITIAL_CAPACITY]);
    }

    /**
     * add new element to heap
     * @param element element 
     * @return true element is added
     */
    public boolean add(E element) {
        if (numElements >= elements.length) {
            expandCapacity();
        }
        elements[numElements++] = element;
        if (numElements > 1) {
            restoreHeapAfterAdd();
        }
        return true;
    }


    /**
     * if the number of elements exceed the array size. this method will expand the array capacity.
     */
    private void expandCapacity() {
        E[] largerArray
                = (E[]) (new Object[elements.length * 2 + 1]);
        for (int i = 0; i < numElements; i++) {
            largerArray[i] = elements[i];
        }
        elements = largerArray;
    }


    /**
     * after adding new element to heap, then we need to fix heap.
     */
    private void restoreHeapAfterAdd() {
        int currentIndex = numElements - 1;
        int parentIndex = (currentIndex - 1) / 2;
        while (currentIndex > 0 && compare(elements[currentIndex],
                elements[parentIndex]) < 0) {
            E temp = elements[currentIndex];
            elements[currentIndex] = elements[parentIndex];
            elements[parentIndex] = temp;
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }


    /**
     * removes the minimum number (first index 0)
     * @return minimum Element
     * @throws NoSuchElementException exception for element
     */
    public E removeMin() throws NoSuchElementException {
        if (numElements == 0) {
            throw new NoSuchElementException();
        }
        E minElement = elements[0];
        // replace the root of heap with its last element
        elements[0] = elements[--numElements];
        elements[numElements] = null;
        restoreHeapAfterRemove();
        return minElement;
    }


    /**
     * fix heap after delete the root.
     */
    private void restoreHeapAfterRemove() {
        int currentIndex = 0;
        int smallerChild = getSmallerChildIndex(currentIndex);
        while (smallerChild >= 0 && compare(elements[currentIndex],
                elements[smallerChild]) > 0) {
            E temp = elements[currentIndex];
            elements[currentIndex] = elements[smallerChild];
            elements[smallerChild] = temp;
            currentIndex = smallerChild;
            smallerChild = getSmallerChildIndex(currentIndex);
        }
    }


    /**
     * This method gets the smaller child (left or right child) 
     * @param parentIndex parent of index
     * @return smaller child
     */
    private int getSmallerChildIndex(int parentIndex) {
        int smallerChild = -1;
        int leftChild = 2 * parentIndex + 1;
        int rightChild = 2 * parentIndex + 2;
        if (leftChild < numElements) {
            if (rightChild < numElements) {
                if (compare(elements[leftChild], elements[rightChild]) < 0) {
                    smallerChild = leftChild;
                } else {
                    smallerChild = rightChild;
                }
            } else {
                smallerChild = leftChild;
            }
        }
        return smallerChild;
    }


    /**
     * returns true if heap is empty
     * @return number of Elements is 0
     */
    public boolean isEmpty() {
        return numElements == 0;
    }


    /**
     * gets the size of the heap
     * @return number of Elements
     */
    public int size() {
        return numElements;
    }

    /**
     * to compare edges weights.
     * @param element1 first element
     * @param element2 second element
     * @return 1 e1 grater than e2, -1 e2 smaller than e1, 0 is equal.
     */
    private int compare(E element1, E element2) {
        Edge e1 = (Edge) element1;
        Edge e2 = (Edge) element2;
        if (e1.weight > e2.weight) {
            return 1;
        } else if (e1.weight < e2.weight) {
            return -1;
        } else {
            return 0;
        }
    }
}

