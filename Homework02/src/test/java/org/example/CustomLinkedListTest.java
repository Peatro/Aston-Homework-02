package org.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {

    @Test
    public void constructorTest() {
        assertDoesNotThrow(() -> {
            CustomLinkedList<Integer> customLinkedListInt = new CustomLinkedList<>();
            CustomLinkedList<Long> customLinkedListLOng = new CustomLinkedList<>();
            CustomLinkedList<Byte> customLinkedListByte = new CustomLinkedList<>();
            CustomLinkedList<Float> customLinkedListFloat = new CustomLinkedList<>();
            CustomLinkedList<Double> customLinkedListDouble = new CustomLinkedList<>();
            CustomLinkedList<Boolean> customLinkedListBool = new CustomLinkedList<>();
            CustomLinkedList<String> customLinkedListStr = new CustomLinkedList<>();
        });
    }

    @ParameterizedTest
    @MethodSource("customLinkedListAddLastTestArgs")
    public void customLinkedListAddLastTest(Integer[] input, Integer[] expected) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        fillList(input, list);

        assertArrayEquals(expected, list);
    }

    @ParameterizedTest
    @MethodSource("customLinkedListAddFirstTestArgs")
    public void customLinkedListAddFirstTest(Integer[] input, Integer[] expected) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        for (Integer integer : input) {
            list.addFirst(integer);
        }

        assertArrayEquals(expected, list);
    }

    @ParameterizedTest
    @MethodSource("customArrayListAddByIndexTestArgs")
    public void customLinkedListAddByIndexTest(int index, Integer[] input, Integer[] expected) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        fillList(input, list);

        list.addByIndex(index, 100);
        assertArrayEquals(expected, list);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 100})
    public void customLinkedListAddByIndexThrowsExceptionTest(int index) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.addLast(100);
        list.addLast(100);

        assertThrows(IndexOutOfBoundsException.class, () -> list.addByIndex(index, 1));
    }

    @ParameterizedTest
    @MethodSource("customArrayListRemoveTestArgs")
    public void customLinkedListRemoveTest(int index, int expectedRemovedValue, Integer[] input, Integer[] expected) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        fillList(input, list);

        int removedValue = list.remove(index);
        assertEquals(expectedRemovedValue, removedValue);

        assertArrayEquals(expected, list);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 100})
    public void customLinkedListRemoveThrowsExceptionTest(int index) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.addLast(100);
        list.addLast(100);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(index));
    }

    @Test
    public void customLinkedListRemoveFromListSizedOne() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.addLast(1);
        int removedValue = list.remove(0);

        assertAll(
                () -> assertEquals(1, removedValue),
                () -> assertTrue(list.isEmpty()),
                () -> assertEquals(0, list.size())
        );
    }

    @ParameterizedTest
    @MethodSource("customLinkedListRemoveLastTestArg")
    public void customLinkedListRemoveLastTest(int expectedRemovedValue, Integer[] input, Integer[] expected) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        fillList(input, list);
        int removedValue = list.removeLast();
        assertAll(
                () -> assertEquals(4, list.size()),
                () -> assertEquals(expectedRemovedValue, removedValue),
                () -> assertArrayEquals(expected, list)
        );
    }

    @Test
    public void customLinkedListRemoveLastFromListSizedOneTest() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.addLast(1);
        int removedValue = list.removeLast();

        assertAll(
                () -> assertEquals(1, removedValue),
                () -> assertTrue(list.isEmpty()),
                () -> assertEquals(0, list.size())
        );
    }

    @Test
    public void customLinkedListRemoveLastOnEmptyListThrowsExceptionTest() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        assertThrows(NoSuchElementException.class, list::removeLast);
    }

    @ParameterizedTest
    @MethodSource("customLinkedListRemoveFirstTestArg")
    public void customLinkedListRemoveFirstTest(int expectedRemovedValue, Integer[] input, Integer[] expected) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        fillList(input, list);
        int removedValue = list.removeFirst();
        assertAll(
                () -> assertEquals(4, list.size()),
                () -> assertEquals(expectedRemovedValue, removedValue),
                () -> assertArrayEquals(expected, list)
        );
    }

    @Test
    public void customLinkedListRemoveFirstFromListSizedOneTest() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.addLast(1);
        int removedValue = list.removeFirst();

        assertAll(
                () -> assertEquals(1, removedValue),
                () -> assertTrue(list.isEmpty()),
                () -> assertEquals(0, list.size())
        );
    }

    @Test
    public void customLinkedListRemoveFirstOnEmptyListThrowsExceptionTest() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    public void customLinkedListIsEmptyTest(){
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        assertTrue(list.isEmpty());
        list.addLast(1);
        assertFalse(list.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("quickSortTestArgs")
    public void quickSortTest(Integer[] input, Integer[] expected) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        fillList(input, list);

        list.quicksort();

        assertArrayEquals(expected, list);
    }

    private static Stream<Arguments> customLinkedListAddLastTestArgs() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{1, 2, 3, 4},
                        new Integer[]{1, 2, 3, 4}
                )
        );
    }

    private static Stream<Arguments> customLinkedListAddFirstTestArgs() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{1, 2, 3, 4},
                        new Integer[]{4, 3, 2, 1}
                )
        );
    }

    private static Stream<Arguments> customArrayListAddByIndexTestArgs() {
        return Stream.of(
                Arguments.of(0, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{100, 1, 2, 3, 4, 5}),
                Arguments.of(2, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 100, 3, 4, 5}),
                Arguments.of(4, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4, 100, 5}),
                Arguments.of(5, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4, 5, 100})
        );
    }

    private static Stream<Arguments> customArrayListRemoveTestArgs() {
        return Stream.of(
                Arguments.of(0, 1, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{2, 3, 4, 5}),
                Arguments.of(2, 3, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 4, 5}),
                Arguments.of(4, 5, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4})
        );
    }

    private static Stream<Arguments> customLinkedListRemoveLastTestArg() {
        return Stream.of(
                Arguments.of(5, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4})
        );
    }

    private static Stream<Arguments> customLinkedListRemoveFirstTestArg() {
        return Stream.of(
                Arguments.of(1, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{2, 3, 4, 5})
        );
    }

    private static Stream<Arguments> quickSortTestArgs() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{6, 11, 3, 4, 5, 10, 6, 7, 1, 9, 8, 5, 11, 2},
                        new Integer[]{1, 2, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10, 11, 11}
                )
        );
    }

    private void fillList(Integer[] input, CustomLinkedList<Integer> list) {
        for (Integer integer : input) {
            list.addLast(integer);
        }
    }

    private static <T extends Comparable<T>> void assertArrayEquals(T[] array, CustomLinkedList<T> list) {
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
