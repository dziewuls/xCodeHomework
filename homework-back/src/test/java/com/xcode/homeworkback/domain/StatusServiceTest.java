package com.xcode.homeworkback.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusServiceTest {

    @Test
    public void shouldReturnExpectedString() {
        //given
        StatusService statusService = new StatusService();
        String expected = "pong";
        //when
        String result = statusService.getStatus();
        //then
        assertThat(expected).isEqualTo(result);
    }
}