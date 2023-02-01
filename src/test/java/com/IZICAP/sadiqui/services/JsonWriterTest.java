package com.IZICAP.sadiqui.services;

import com.IZICAP.sadiqui.mappers.QuestionAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JsonWriterTest {

    private static final String FILE_NAME = "localstorage/questions_answers.json";

    @Test
    void writeDataToJsonTest() {
        QuestionAnswer questionAnswer = new QuestionAnswer("What is your name?", "My name is ChatGPT.");
        JsonWriter.writeDataToJson(questionAnswer);

        File file = new File(FILE_NAME);
        assertTrue(file.exists());

        try (FileReader reader = new FileReader(FILE_NAME)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<QuestionAnswer>>() {}.getType();
            List<QuestionAnswer> questionAnswers = gson.fromJson(reader, listType);

            assertEquals(1, questionAnswers.size());
            assertEquals(questionAnswer, questionAnswers.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}