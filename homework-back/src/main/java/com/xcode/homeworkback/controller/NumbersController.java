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
        List<Integer> sortedNumbers;

        try {
            sortedNumbers = this.numbersSorterService.sort(
                    numbersContainer.getNumbers(),
                    Order.valueOf(numbersContainer.getOrder())
            );

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        NumbersContainerDto result = new NumbersContainerDto(sortedNumbers, null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
