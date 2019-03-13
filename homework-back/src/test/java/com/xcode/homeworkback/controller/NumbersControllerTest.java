package com.xcode.homeworkback.controller;

import com.xcode.homeworkback.domain.NumbersSorterService;
import com.xcode.homeworkback.domain.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NumbersController.class)
public class NumbersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NumbersSorterService numbersSorterService;

    @Test
    public void shouldReturnSortedNumbersWithDescendingOrderAndHttpStatusOk() throws Exception {
        List<Integer> givenArray = Arrays.asList(2, 5, 1, 3, 4);
        Order givenOrder = Order.DESC;
        List<Integer> expectedArray = Arrays.asList(5, 4, 3, 2, 1);

        when(this.numbersSorterService.sort(givenArray, givenOrder)).thenReturn(expectedArray);

        mockMvc.perform(post("/numbers/sort-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numbers\":[2, 5, 1, 3, 4],\"order\":\"DSC\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"numbers\":[5, 4, 3, 2, 1]}"));
    }

    @Test
    public void shouldReturnSortedNumbersWithAscendingOrderAndHttpStatusOk() throws Exception {
        List<Integer> givenArray = Arrays.asList(2, 5, 1, 3, 4);
        Order givenOrder = Order.ASC;
        List<Integer> expectedArray = Arrays.asList(1, 2, 3, 4, 5);

        when(this.numbersSorterService.sort(givenArray, givenOrder)).thenReturn(expectedArray);

        mockMvc.perform(post("/numbers/sort-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numbers\":[2, 5, 1, 3, 4],\"order\":\"ASC\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"numbers\":[1, 2, 3, 4, 5]}"));
    }

    @Test
    public void shouldReturnEmptyNumbersArrayAndHttpStatusOkWhenNumbersArrayIsEmpty() throws Exception {
        List<Integer> givenArray = Collections.emptyList();
        Order givenOrder = Order.ASC;
        List<Integer> expectedArray = Collections.emptyList();

        when(this.numbersSorterService.sort(givenArray, givenOrder)).thenReturn(expectedArray);

        mockMvc.perform(post("/numbers/sort-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numbers\":[],\"order\":\"ASC\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"numbers\":[]}"));
    }

    @Test
    public void shouldReturnEmptyNumbersArrayAndHttpStatusOkWhenNumbersArrayIsNull() throws Exception {
        mockMvc.perform(post("/numbers/sort-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"order\":\"ASC\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"numbers\":[]}"));
    }

    @Test
    public void shouldReturnHttpStatusBadRequestWhenNumbersArrayHasWrongFormat() throws Exception {
        mockMvc.perform(post("/numbers/sort-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numbers\":[\"b\", \"c\", \"d\", \"s\", \"e\"],\"order\":\"ASC\"}"))
                .andExpect(status().isBadRequest());
    }
}