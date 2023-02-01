package com.IZICAP.sadiqui.services;

import com.IZICAP.sadiqui.mappers.QuestionAnswer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CsvWriterTest {

    private static final String FILE_NAME = "localstorage/questions_answers.csv";

    @Test
    public void writeDataToCsvTest() {
        List<QuestionAnswer> questionAnswers = Arrays.asList(
                new QuestionAnswer("Question 1", "Answer 1"),
                new QuestionAnswer("Question 2", "Answer 2")
        );
        CsvWriter.writeDataToCsv(questionAnswers);

        File file = new File(FILE_NAME);
        try (Scanner scanner = new Scanner(file)) {
            int lineCount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                assertEquals("Question: " + questionAnswers.get(lineCount).getQuestion(), parts[0].replace("\"",""));
                assertEquals("Answer: " + questionAnswers.get(lineCount).getAnswer().replace("\n", ""), parts[1].replace("\"",""));
                lineCount++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        file.delete();
    }
}