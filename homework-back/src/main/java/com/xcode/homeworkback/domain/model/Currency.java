package com.xcode.homeworkback.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Currency {

    private String currencyCode;
    private Double value;
}
