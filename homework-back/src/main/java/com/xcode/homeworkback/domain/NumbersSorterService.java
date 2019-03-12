package com.xcode.homeworkback.domain;

import com.xcode.homeworkback.domain.model.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NumbersSorterService {
    public List<Integer> sort(List<Integer> numbers, Order order) {
        if(numbers == null || numbers.isEmpty()) return Collections.emptyList();

        List<Integer> result = new ArrayList<>(numbers);

        if (order.equals(Order.ASC)) {
            result.sort(Comparator.naturalOrder());
        } else {
            result.sort(Comparator.reverseOrder());
        }

        return result;
    }
}
