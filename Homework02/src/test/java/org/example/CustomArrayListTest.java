package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomArrayListTest {

    CustomArrayList<Integer> customArrayListInteger = new CustomArrayList<>();

    @Test
    public void customArrayListConstructorTest() {
        Assertions.assertDoesNotThrow(() -> {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            CustomArrayList<Integer> list2 = new CustomArrayList<>(20);
            CustomArrayList<Integer> list3 = new CustomArrayList<>(0);
            CustomArrayList<Long> customArrayListLong = new CustomArrayList<>();
            CustomArrayList<Float> customArrayListFloat = new CustomArrayList<>();
            CustomArrayList<Double> customArrayListDouble = new CustomArrayList<>();
            CustomArrayList<Byte> customArrayListByte = new CustomArrayList<>();
            CustomArrayList<Boolean> customArrayListBoolen = new CustomArrayList<>();
            CustomArrayList<String> customArrayListString = new CustomArrayList<>();
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CustomArrayList<Integer> list4 = new CustomArrayList<>(-20);
        });
    }

    @Test
    public void customArrayListAddTest() {
        Assertions.assertDoesNotThrow(() -> {
            customArrayListInteger.add(100);
            customArrayListInteger.add(-100);
            customArrayListInteger.add(0);
        });
    }

    @Test
    public void customArrayListGetTest() {
        customArrayListInteger.add(100);
        customArrayListInteger.add(-100);
        customArrayListInteger.add(0);

        Assertions.assertEquals(100, customArrayListInteger.get(0));
        Assertions.assertEquals(-100, customArrayListInteger.get(1));
        Assertions.assertEquals(0, customArrayListInteger.get(2));

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayListInteger.get(200);
        });
        ;

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayListInteger.get(-200);
        });
        ;
    }

    @Test
    public void customArrayListAddAtIndexTest() {

        Assertions.assertDoesNotThrow(() -> {
            customArrayListInteger.add(0, 100);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayListInteger.add(200, 100);
        }, "Index out of Bound at index 200");

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayListInteger.add(-1, 100);
        }, "Index out of Bound at negative index");

        Assertions.assertEquals(customArrayListInteger.get(0), 100);

    }

    @Test
    public void customArrayAddFirstTest() {
        customArrayListInteger.add(100);
        customArrayListInteger.add(200);
        customArrayListInteger.addFirst(300);

        Assertions.assertEquals(300, customArrayListInteger.get(0));
        Assertions.assertEquals(100, customArrayListInteger.get(1));
        Assertions.assertEquals(200, customArrayListInteger.get(2));
    }

    @Test
    public void customArrayListAddLastTest() {
        customArrayListInteger.add(100);
        customArrayListInteger.add(200);
        customArrayListInteger.addLast(300);

        Assertions.assertEquals(100, customArrayListInteger.get(0));
        Assertions.assertEquals(200, customArrayListInteger.get(1));
        Assertions.assertEquals(300, customArrayListInteger.get(2));
    }

    @Test
    public void customArrayListRemoveTest() {
        customArrayListInteger.add(0, 100);
        customArrayListInteger.add(1, 200);
        customArrayListInteger.add(2, 300);
        customArrayListInteger.add(3, 400);

        Assertions.assertDoesNotThrow(() -> {
            customArrayListInteger.remove(2);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayListInteger.remove(-2);
        });

        Assertions.assertEquals(100, customArrayListInteger.get(0));
        Assertions.assertEquals(200, customArrayListInteger.get(1));
        Assertions.assertEquals(400, customArrayListInteger.get(2));
        Assertions.assertNull(customArrayListInteger.get(3));
    }

    @Test
    public void customArrayListIsEmptyTest() {
        Assertions.assertTrue(customArrayListInteger.isEmpty());

        customArrayListInteger.add(1);
        Assertions.assertFalse(customArrayListInteger.isEmpty());
    }

    @Test
    public void customArrayListIsEmptySize() {
        customArrayListInteger.add(1);
        Assertions.assertEquals(1, customArrayListInteger.size());
    }

    @Test
    public void customArrayListSetTest() {
        customArrayListInteger.add(100);
        customArrayListInteger.add(200);
        customArrayListInteger.add(300);
        int oldvalue = customArrayListInteger.set(1, 400);

        Assertions.assertEquals(200, oldvalue);
        Assertions.assertEquals(400, customArrayListInteger.get(1));
    }

    @Test
    public void mergeSortTest() {
        Integer[] array = {1, 2, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10, 11, 11};

        customArrayListInteger.add(11);
        customArrayListInteger.add(8);
        customArrayListInteger.add(11);
        customArrayListInteger.add(9);
        customArrayListInteger.add(1);
        customArrayListInteger.add(10);
        customArrayListInteger.add(7);
        customArrayListInteger.add(2);
        customArrayListInteger.add(6);
        customArrayListInteger.add(3);
        customArrayListInteger.add(5);
        customArrayListInteger.add(4);
        customArrayListInteger.add(5);
        customArrayListInteger.add(6);

        customArrayListInteger.mergeSort();

        assertArrayEquals(array, customArrayListInteger);
    }

    private static <T extends Comparable<T>> void assertArrayEquals(T[] array, CustomArrayList<T> list) {
        if (array.length != list.size()) {
            throw new IllegalStateException("Length should be EQUALS! - Array size: " + array.length + " List size: " + list.size());
        }
        for (int i = 0; i < array.length; i++) {
            T arrayVal = array[i];
            T listVal = list.get(i);
            Assertions.assertEquals(arrayVal, listVal);
        }
    }
}
