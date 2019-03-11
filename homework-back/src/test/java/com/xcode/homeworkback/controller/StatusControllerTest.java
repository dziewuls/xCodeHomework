package com.xcode.homeworkback.controller;

import com.xcode.homeworkback.domain.StatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatusController.class)
public class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusService statusService;

    @Test
    public void shouldReturnHttpStatusOkAndExpectedStringValue() throws Exception {
        String expectedString = "pong";
        when(this.statusService.getStatus()).thenReturn(expectedString);
        this.mockMvc
                .perform(get("/status/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedString));
    }
}