package project20280.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    public static <T extends Comparable<? super T>> void mergeSort(final T[] data,
                                                                   final Comparator<T> comparator) {
        if (data == null || data.length <= 1) {
            return;
        }

        T[] temp = Arrays.copyOf(data, data.length);
        mergeSortArray(data, temp, 0, data.length - 1, comparator);
    }

    private static <T extends Comparable<? super T>> void mergeSortArray(final T[] data,
                                                                         final T[] temp,
                                                                         final int left,
                                                                         final int right,
                                                                         final Comparator<T> comparator) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSortArray(data, temp, left, mid, comparator);
        mergeSortArray(data, temp, mid + 1, right, comparator);
        mergeArray(data, temp, left, mid, right, comparator);
    }

    private static <T extends Comparable<? super T>> void mergeArray(final T[] data,
                                                                     final T[] temp,
                                                                     final int left,
                                                                     final int mid,
                                                                     final int right,
                                                                     final Comparator<T> comparator) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (comparator.compare(data[i], data[j]) <= 0) {
                temp[k] = data[i];
                i++;
            } else {
                temp[k] = data[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = data[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = data[j];
            j++;
            k++;
        }

        for (int x = left; x <= right; x++) {
            data[x] = temp[x];
        }
    }

    public static <T extends Comparable<? super T>> void mergeSort(final List<T> data,
                                                                   final Comparator<T> comparator) {
        if (data == null || data.size() <= 1) {
            return;
        }

        int mid = data.size() / 2;

        List<T> left = new ArrayList<>(data.subList(0, mid));
        List<T> right = new ArrayList<>(data.subList(mid, data.size()));

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        mergeList(data, left, right, comparator);
    }

    private static <T extends Comparable<? super T>> void mergeList(final List<T> data,
                                                                    final List<T> left,
                                                                    final List<T> right,
                                                                    final Comparator<T> comparator) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                data.set(k, left.get(i));
                i++;
            } else {
                data.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < left.size()) {
            data.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < right.size()) {
            data.set(k, right.get(j));
            j++;
            k++;
        }
    }
}