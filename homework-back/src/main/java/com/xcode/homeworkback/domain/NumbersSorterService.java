package com.xcode.homeworkback.domain;

import com.xcode.homeworkback.domain.model.Order;

import java.util.*;

public class NumbersSorterService {
    public List<Integer> sort(List<Integer> numbers, Order order) {
        if (numbers == null || numbers.isEmpty()) return Collections.emptyList();
        numbers.removeIf(Objects::isNull);

        List<Integer> result = new ArrayList<>(numbers);

        if (order.equals(Order.ASC)) {
            result.sort(Comparator.naturalOrder());
        } else {
            result.sort(Comparator.reverseOrder());
        }

        return result;
    }
}
