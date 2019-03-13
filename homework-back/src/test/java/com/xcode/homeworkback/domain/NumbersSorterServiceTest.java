package com.xcode.homeworkback.domain;

import com.xcode.homeworkback.domain.model.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NumbersSorterServiceTest {

    private NumbersSorterService numbersSorterService;

    @Before
    public void initialNumbersSorterService() {
        this.numbersSorterService = new NumbersSorterService();
    }

    @Test
    public void shouldReturnSortedNumbersWithAscendingOrder() {
        //given
        List<Integer> numbersToSort = Arrays.asList(5, 2, 4, 1, 3);
        List<Integer> expectedNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Order givenOrder = Order.ASC;
        //when
        List<Integer> result = this.numbersSorterService.sort(numbersToSort, givenOrder);
        //then
        assertThat(result).isEqualTo(expectedNumbers);
    }

    @Test
    public void shouldReturnSortedNumberWithDescendingOrder() {
        //given
        List<Integer> numbersToSort = Arrays.asList(5, 2, 4, 1, 3);
        List<Integer> expectedNumbers = Arrays.asList(5, 4, 3, 2, 1);
        Order givenOrder = Order.DESC;
        //when
        List<Integer> result = this.numbersSorterService.sort(numbersToSort, givenOrder);
        //then
        assertThat(result).isEqualTo(expectedNumbers);
    }

    @Test
    public void shouldReturnEmptyListWhenGivenNumbersListIsEmpty() {
        //given
        List<Integer> numbersToSort = Collections.emptyList();
        List<Integer> expectedNumbers = Collections.emptyList();
        Order givenOrder = Order.ASC;
        //when
        List<Integer> result = this.numbersSorterService.sort(numbersToSort, givenOrder);
        //then
        assertThat(result).isEqualTo(expectedNumbers);
    }

    @Test
    public void shouldReturnEmptyListWhenGivenNumbersListIsNull() {
        //given
        List<Integer> expectedNumbers = Collections.emptyList();
        Order givenOrder = Order.ASC;
        //when
        List<Integer> result = this.numbersSorterService.sort(null, givenOrder);
        //then
        assertThat(result).isEqualTo(expectedNumbers);
    }
}