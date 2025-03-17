package org.example;

public class CustomLinkedList<E> {

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

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == this.size) {
            addLast(element);
        } else {
            linkBefore(element, getNode(index));
        }
    }

    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

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
            System.out.println("List is empty");
            return null;
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
            System.out.println("List is empty");
            return null;
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

    public int getSize() {
        return this.size;
    }

    private Node<E> getNode(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

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

    private void linkBefore(E element, Node<E> succNode) {
        Node<E> prev = succNode.prev;
        Node<E> newNode = new Node<>(element);

        if (prev == null) {
            first = prev;
        } else {
            prev.next = newNode;
            newNode.next = succNode;
            succNode.prev = newNode;
        }
        size++;
    }
}
