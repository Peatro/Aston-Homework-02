package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomArrayList<E extends Comparable<E>> {

    private final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity can't be negative");
        }
        this.elements = new Object[initialCapacity];
    }

    public void add(E element) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = element;
    }

    public void add(int index, E element) {
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        this.size++;
    }

    public void addFirst(E element) {
        this.add(0, element);
    }

    public void addLast(E element) {
        this.add(element);
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removedElement = (E) elements[index];
        fastRemove(index);
        return removedElement;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    private void grow() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
    }

    public void mergeSort() {
        if (size <= 1) {
            return;
        }
        mergeSort(0, size - 1);
    }

    private void mergeSort(int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(int left, int mid, int right) {
        Object[] leftArray = Arrays.copyOfRange(elements, left, mid + 1);
        Object[] rightArray = Arrays.copyOfRange(elements, mid + 1, right + 1);

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftArray.length && j < rightArray.length) {
            E leftElement = (E) leftArray[i];
            E rightElement = (E) rightArray[j];

            if (leftElement.compareTo(rightElement) <= 0) {
                elements[k++] = leftArray[i++];
            } else {
                elements[k++] = rightArray[j++];
            }
        }

        while (i < leftArray.length) {
            elements[k++] = leftArray[i++];
        }

        while (j < rightArray.length) {
            elements[k++] = rightArray[j++];
        }
    }
}
