package com.lucas.yugiohcards.integrations.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeignBadResponseWrapper extends RuntimeException {

    private int status;
    private String body;
}
