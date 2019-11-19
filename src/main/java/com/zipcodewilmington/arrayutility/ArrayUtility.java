package com.zipcodewilmington.arrayutility;

import com.sun.tools.javac.util.ArrayUtils;
import sun.awt.SunHints;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {

    T T;
    T[] inputArray;

    public ArrayUtility(T[] inputArray) {
        this.inputArray = inputArray;


    }

    public Integer getNumberOfOccurrences(T val) {

        return Math.toIntExact(Arrays.stream(inputArray).filter(value -> value.equals(val)).count());
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        Stream<T> strm1 = Stream.of(inputArray);
        Stream<T> strm2 = Stream.of(arrayToMerge);
        Stream<T> resultStr = Stream.concat(strm1, strm2);

        return Math.toIntExact(resultStr.filter(value -> value.equals(valueToEvaluate)).count());
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {

        int b = inputArray.length;
        int c = arrayToMerge.length;


        T[] d = (T[]) Array.newInstance(inputArray.getClass().getComponentType(), b + c);
        System.arraycopy(inputArray, 0, d, 0, b);
        System.arraycopy(arrayToMerge, 0, d, b, c);


        List<T> newArr = new ArrayList<>();
        newArr.addAll(Arrays.asList(d));

        for (int i = 0; i < d.length; i++) {
            d[i] = (T) d[i];
        }
        Arrays.sort(d);

        // find the max frequency using linear
        // traversal
        int maxCount = 1;
        T res = d[0];
        int currCount = 1;

        for (int i = 1; i < newArr.size(); i++) {
            if (d[i] == d[i - 1])
                currCount++;
            else {
                if (currCount > maxCount) {
                    maxCount = currCount;
                    res = d[i - 1];
                }
                currCount = 1;
            }
        }

        // If last element is most frequent
        if (currCount > maxCount) {
            currCount = currCount;
            res = d[newArr.size() - 1];
        }
        return res;

    }

    public T[] removeValue(T valueToRemove) {

        List<T> array = Arrays.stream(inputArray).filter(i -> i != valueToRemove).collect(Collectors.toList());

        T[] result = Arrays.copyOf(inputArray, array.size());

        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i);
        }

        return result;
    }
}
