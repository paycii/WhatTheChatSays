package com.IZICAP.sadiqui.mappers;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswer {
    @SerializedName("Question")
    private String Question;
    @SerializedName("Answer")
    private String Answer;
}
