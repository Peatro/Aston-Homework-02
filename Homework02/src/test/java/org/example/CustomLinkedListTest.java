package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.stream.Stream;

public class CustomLinkedListTest {
    CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();

    @Test
    public void constructorTest() {
        Assertions.assertDoesNotThrow(() -> {
            CustomLinkedList<Integer> customLinkedListInt = new CustomLinkedList<>();
            CustomLinkedList<Long> customLinkedListLOng = new CustomLinkedList<>();
            CustomLinkedList<Byte> customLinkedListByte = new CustomLinkedList<>();
            CustomLinkedList<Float> customLinkedListFloat = new CustomLinkedList<>();
            CustomLinkedList<Double> customLinkedListDouble = new CustomLinkedList<>();
            CustomLinkedList<Boolean> customLinkedListBool = new CustomLinkedList<>();
            CustomLinkedList<String> customLinkedListStr = new CustomLinkedList<>();
        });
    }

    @Test
    public void addAndGetTest() {
        Integer[] array = {100, 200, -100, 1100};

        Assertions.assertDoesNotThrow(() -> {
            customLinkedList.addFirst(100);
            customLinkedList.addLast(200);
            customLinkedList.addLast(-100);
            customLinkedList.addLast(1100);
        });

        assertArrayEquals(array, customLinkedList);
    }

    @Test
    public void removeTest() {
        Integer[] array = {100, 300, 400};

        customLinkedList.addFirst(100);
        customLinkedList.addLast(200);
        customLinkedList.addLast(300);
        customLinkedList.addLast(400);

        int removedValue = customLinkedList.remove(1);
        Assertions.assertEquals(200, removedValue);

        assertArrayEquals(array, customLinkedList);
    }

    @Test
    public void addAtIndexTest() {
        Integer[] array = {100, 200, 500, 300, 400};

        customLinkedList.addFirst(100);
        customLinkedList.addLast(200);
        customLinkedList.addLast(300);
        customLinkedList.addLast(400);
        customLinkedList.add(2, 500);

        assertArrayEquals(array, customLinkedList);
    }

    @Test
    public void quickSortTest() {
        Integer[] array = {1, 2, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10, 11, 11};

        customLinkedList.addLast(11);
        customLinkedList.addLast(8);
        customLinkedList.addLast(11);
        customLinkedList.addLast(9);
        customLinkedList.addLast(10);
        customLinkedList.addLast(1);
        customLinkedList.addLast(7);
        customLinkedList.addLast(2);
        customLinkedList.addLast(6);
        customLinkedList.addLast(3);
        customLinkedList.addLast(5);
        customLinkedList.addLast(4);
        customLinkedList.addLast(5);
        customLinkedList.addLast(6);

        customLinkedList.quicksort();

        assertArrayEquals(array, customLinkedList);
    }

    private static <T extends Comparable<T>> void assertArrayEquals(T[] array, CustomLinkedList<T> list) {
        if (array.length != list.getSize()) {
            throw new IllegalStateException("Length should be EQUALS! - Array size: " + array.length + " List size: " + list.getSize());
        }
        for (int i = 0; i < array.length; i++) {
            T arrayVal = array[i];
            T listVal = list.get(i);
            Assertions.assertEquals(arrayVal, listVal);
        }
    }
}
