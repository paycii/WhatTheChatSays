package com.IZICAP.sadiqui.services;

import com.IZICAP.sadiqui.mappers.QuestionAnswer;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvWriter {

    private static final String FILE_NAME = "localstorage/questions_answers.csv";

    public static void writeDataToCsv(List<QuestionAnswer> questionAnswers) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_NAME,true))) {

            for (QuestionAnswer questionAnswer : questionAnswers) {
                String[] data = {"Question: "+questionAnswer.getQuestion(),"Answer: "+ questionAnswer.getAnswer().replace("\n","")};
                writer.writeNext(data);
            }

            System.out.println("Data written to CSV file successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
