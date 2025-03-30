package org.example;

import java.util.NoSuchElementException;

public class CustomLinkedList<E extends Comparable<E>> {

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E item) {
            this.item = item;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    public void addByIndex(int index, E element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index value!");
        }

        if (index == size) {
            addLast(element);
        } else {
            linkBefore(element, getNode(index));
        }
    }

    public E remove(int index) {

        checkIndex(index);

        Node<E> toRemove = getNode(index);
        E removedData = toRemove.item;

        if (size == 1) {
            first = null;
            last = null;
        } else if (toRemove == first) {
            first = first.next;
            first.prev = null;
        } else if (toRemove == last) {
            last = last.prev;
            last.next = null;
        } else {
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }

        size--;
        return removedData;
    }

    public E removeFirst() {

        if (first == null) {
            throw new NoSuchElementException("List is empty!");
        }

        E removedItem = first.item;

        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }

        size--;
        return removedItem;
    }

    public E removeLast() {

        if (last == null) {
            throw new NoSuchElementException("List is Empty!");
        }

        E removedItem = last.item;

        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        size--;
        return removedItem;
    }

    public E get(int index) {
        return getNode(index).item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
    }

    private Node<E> getNode(int index) {

        checkIndex(index);

        Node<E> current;

        if (index < size / 2) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private void linkBefore(E element, Node<E> replacedNode) {
        Node<E> prevNode = replacedNode.prev;
        Node<E> newNode = new Node<>(element);
        Node<E> nextNode = replacedNode.next;

        if (prevNode == null) {
            first = newNode;
            newNode.next = replacedNode;
            replacedNode.prev = newNode;;
        } else {
            if (nextNode == null) {
                last = replacedNode;
            }
            prevNode.next = newNode;
            replacedNode.prev = newNode;
            newNode.prev = prevNode;
            newNode.next = replacedNode;
        }
        size++;
    }

    public void quicksort() {
        if (first == null || last == null) {
            return;
        }
        quicksort(first, last);
    }

    private void quicksort(Node<E> low, Node<E> high) {
        if (low != null && high != null && low != high && low.prev != high) {
            Node<E> pivot = partition(low, high);
            quicksort(low, pivot.prev);
            quicksort(pivot.next, high);
        }
    }

    private Node<E> partition(Node<E> low, Node<E> high) {
        E pivot = high.item;
        Node<E> i = low.prev;

        for (Node<E> j = low; j != high; j = j.next) {
            if (j.item.compareTo(pivot) <= 0) {
                i = (i == null) ? low : i.next;
                swap(i, j);
            }
        }
        i = (i == null) ? low : i.next;
        swap(i, high);
        return i;
    }

    private void swap(Node<E> a, Node<E> b) {
        E temp = a.item;
        a.item = b.item;
        b.item = temp;
    }
}
