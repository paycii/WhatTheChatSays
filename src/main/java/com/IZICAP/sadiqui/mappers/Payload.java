package com.IZICAP.sadiqui.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payload {
    private String model;
    private String prompt;
    private int max_tokens;
    private double temperature;
}
