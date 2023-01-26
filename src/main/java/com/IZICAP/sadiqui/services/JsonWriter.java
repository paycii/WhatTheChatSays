package com.IZICAP.sadiqui.services;

import com.IZICAP.sadiqui.mappers.QuestionAnswer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter {

//    private static final String FILE_NAME = "questions_answers.json";
//
//    public static void writeDataToJson(List<QuestionAnswer> questionAnswers) {
//        try (FileWriter writer = new FileWriter(FILE_NAME,true)) {
//            Gson gson = new Gson();
//            gson.toJson(questionAnswers, writer);
//            System.out.println("Data written to json file successfully.");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    private static final String FILE_NAME = "localstorage/questions_answers.json";

    public static void writeDataToJson(QuestionAnswer questionAnswer) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<QuestionAnswer>>() {
            }.getType();
            List<QuestionAnswer> questionAnswers = gson.fromJson(new FileReader(FILE_NAME), listType);

            if (questionAnswers == null) {
                questionAnswers = new ArrayList<>();
            }

            questionAnswers.add(questionAnswer);

            try (FileWriter writer = new FileWriter(FILE_NAME)) {
                gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(questionAnswers, writer);
                System.out.println("Data written to json file successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
