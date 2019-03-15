package com.xcode.homeworkback.controller;

import com.xcode.homeworkback.domain.NumbersSorterService;
import com.xcode.homeworkback.domain.dto.NumbersContainerDto;
import com.xcode.homeworkback.domain.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/numbers")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class NumbersController {

    private NumbersSorterService numbersSorterService;

    @PostMapping("/sort-command")
    public ResponseEntity getSortedNumbers(@RequestBody NumbersContainerDto numbersContainer) {
        try {
            return new ResponseEntity<>(this.sortNumbers(numbersContainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private NumbersContainerDto sortNumbers(NumbersContainerDto numbersContainer) {
        if (numbersContainer.getOrder() == null)
            throw new IllegalArgumentException("Order can not be empty.");

        List<Integer> sortedNumbers = this.numbersSorterService.sort(
                numbersContainer.getNumbers(),
                Order.valueOf(numbersContainer.getOrder())
        );

        return new NumbersContainerDto(sortedNumbers, null);
    }
}
