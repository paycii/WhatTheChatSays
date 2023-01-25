package com.IZICAP.sadiqui.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public  class Choice{
    private String text;
    private int index;
    private Object logprobs;
    private String finish_reason;
}