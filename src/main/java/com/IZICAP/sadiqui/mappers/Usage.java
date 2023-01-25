package com.IZICAP.sadiqui.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public  class Usage{
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}