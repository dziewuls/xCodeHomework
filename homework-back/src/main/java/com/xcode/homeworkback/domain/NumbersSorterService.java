package com.xcode.homeworkback.domain;

import com.xcode.homeworkback.domain.model.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NumbersSorterService {
    public List<Integer> sort(List<Integer> numbers, Order order) {
        List<Integer> result = new ArrayList<>(numbers);

        if (order.equals(Order.ASC)) {
            result.sort(null);
        } else {
            result.sort(Comparator.reverseOrder());
        }

        return result;
    }
}
