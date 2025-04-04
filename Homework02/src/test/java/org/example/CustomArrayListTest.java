package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

    @Test
    public void customArrayListConstructorTest() {
        assertDoesNotThrow(() -> {
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
    }

    @Test
    public void customArrayListNegativeInitialCapacityTest() {
        assertThrows(IllegalArgumentException.class, () -> new CustomArrayList<>(-1));
    }

    @Test
    public void customArrayListGetIndexOutOfBoundsExceptionTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

        list.add(1);
        list.add(2);

        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(2))
        );
    }

    @Test
    public void customArrayListSetIndexOutOfBoundExceptionTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 56));

        list.add(1);
        list.add(2);

        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 92)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.set(2, 30))
        );
    }

    @Test
    public void customArrayListAddByIndexOutOfBoundExceptionTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 56));

        list.add(1);
        list.add(2);

        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 92)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.add(12, 30))
        );
    }

    @Test
    public void customArrayListRemoveIndexOutOfBoundExceptionTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));

        list.add(1);
        list.add(2);

        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.remove(20))
        );
    }

    @Test
    public void customArrayListMergeSortShouldNotThrowExceptionOnEmptyListTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.mergeSort();
    }

    @Test
    public void customArrayListMergeSortShouldNotThrowExceptionOnOneElementTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.mergeSort();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 100, 1000})
    public void customArrayListArrayGrowTest(int initialCapacity) throws Exception {
        CustomArrayList<Integer> list = new CustomArrayList<>(initialCapacity);

        Field elementsField = CustomArrayList.class.getDeclaredField("elements");
        elementsField.setAccessible(true);

        Object[] elements = (Object[]) elementsField.get(list);
        assertEquals(initialCapacity, elements.length);

        for (int i = 0; i <= initialCapacity; i++) {
            list.add(1);
        }

        elements = (Object[]) elementsField.get(list);
        assertEquals(initialCapacity * 2, elements.length);
    }


    @ParameterizedTest
    @ValueSource(ints = {100, -100, 0})
    public void customArrayListAddTest(int number) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, -100, 0})
    public void customArrayListGetTest(int number) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(number);

        assertEquals(number, list.get(0));
    }

    @ParameterizedTest
    @MethodSource("addAtIndexTestArgs")
    public void customArrayListAddAtIndexTest(int index, int value, Integer[] expected) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(100);
        list.add(100);
        list.add(100);
        list.add(100);
        list.add(100);

        list.add(index, value);

        assertAll(
                () -> assertEquals(6, list.size()),
                () -> assertArrayEquals(expected, list)
        );
    }

    @ParameterizedTest
    @MethodSource("addFirstTestArgs")
    public void customArrayAddFirstTest(int value, Integer[] expected) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.addFirst(100);
        list.addFirst(200);
        list.addFirst(300);
        list.addFirst(400);
        list.addFirst(value);

        assertAll(
                () -> assertEquals(5, list.size()),
                () -> assertArrayEquals(expected, list)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {300, 400, 500})
    public void customArrayListAddLastTest(int value) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(100);
        list.add(200);
        list.addLast(value);

        assertAll(
                () -> assertEquals(3, list.size()),
                () -> assertEquals(value, list.get(2))
        );
    }

    @ParameterizedTest
    @MethodSource("removeTestArgs")
    public void customArrayListRemoveTest(int index, Integer[] expected) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);

        list.remove(index);

        assertAll(
                () -> assertEquals(4, list.size()),
                () -> assertArrayEquals(expected, list)
        );
    }

    @ParameterizedTest
    @MethodSource("setTestArgs")
    public void customArrayListSetTest(int index, int value, int removedValue, Integer[] expected) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(100);
        list.add(200);
        list.add(300);
        int oldValue = list.set(index, value);

        assertAll(
                () -> assertEquals(3, list.size()),
                () -> assertEquals(oldValue, removedValue),
                () -> assertArrayEquals(expected, list)
        );
    }

    @Test
    public void customArrayListIsEmptyTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        assertAll(
                () -> assertEquals(0, list.size()),
                () -> assertTrue(list.isEmpty())
        );

        list.add(1);

        assertAll(
                () -> assertEquals(1, list.size()),
                () -> assertFalse(list.isEmpty())
        );
    }

    @Test
    public void customArrayListSizeTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertEquals(1, list.size());
    }

    @ParameterizedTest
    @MethodSource("mergeSortTestArgs")
    public void mergeSortTest(Integer[] input, Integer[] expected) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        for (Integer integer : input) {
            list.add(integer);
        }

        list.mergeSort();

        assertArrayEquals(expected, list);
    }

    private static Stream<Arguments> addAtIndexTestArgs() {
        return Stream.of(
                Arguments.of(0, 300, new Integer[]{300, 100, 100, 100, 100, 100}),
                Arguments.of(2, 200, new Integer[]{100, 100, 200, 100, 100, 100}),
                Arguments.of(4, 400, new Integer[]{100, 100, 100, 100, 400, 100})
        );
    }

    private static Stream<Arguments> addFirstTestArgs() {
        return Stream.of(
                Arguments.of(100, new Integer[]{100, 400, 300, 200, 100}),
                Arguments.of(5000, new Integer[]{5000, 400, 300, 200, 100}),
                Arguments.of(345, new Integer[]{345, 400, 300, 200, 100})
        );
    }

    private static Stream<Arguments> removeTestArgs() {
        return Stream.of(
                Arguments.of(0, new Integer[]{200, 300, 400, 500}),
                Arguments.of(2, new Integer[]{100, 200, 400, 500}),
                Arguments.of(4, new Integer[]{100, 200, 300, 400})
        );
    }

    private static Stream<Arguments> setTestArgs() {
        return Stream.of(
                Arguments.of(0, 123, 100, new Integer[]{123, 200, 300}),
                Arguments.of(1, 456, 200, new Integer[]{100, 456, 300}),
                Arguments.of(2, 789, 300, new Integer[]{100, 200, 789})
        );
    }

    private static Stream<Arguments> mergeSortTestArgs() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{6, 11, 3, 4, 5, 10, 6, 7, 1, 9, 8, 5, 11, 2},
                        new Integer[]{1, 2, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10, 11, 11}
                )
        );
    }

    private static <T extends Comparable<T>> void assertArrayEquals(T[] array, CustomArrayList<T> list) {
        if (array.length != list.size()) {
            throw new IllegalStateException("Length should be EQUALS! - Array size: " + array.length + " List size: " + list.size());
        }

        for (int i = 0; i < array.length; i++) {
            T arrayVal = array[i];
            T listVal = list.get(i);
            assertEquals(arrayVal, listVal);
        }
    }
}
