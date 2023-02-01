package com.IZICAP.sadiqui.services;

import com.IZICAP.sadiqui.mappers.*;
import com.IZICAP.sadiqui.mappers.Response;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
// this is the class that holds the service responsible for consuming openAi API

@Service
@Data
public class OpenAiService {

    // RestTemplate to generate HTTP Requests
    private RestTemplate restTemplate;
    @Value("${token}")
    private String token;
    public OpenAiService(){
        restTemplate = new RestTemplate();
    }
    // method that 'll be responsible for calling the APi
    public List<String> getAnswer(String input) throws Exception {
        // setting up the headears correctly
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // the key
        if( token == null){ throw new Exception("Token not set"); }
        headers.set("Authorization", "Bearer "+token);

        // generating a payload to send
        Payload payload = new Payload("text-davinci-003", input, 4000, 1.0);
        HttpEntity<Payload> request = new HttpEntity<>(payload,headers);

        try {
            //send the post to the Webservice
            ResponseEntity<Response> response = restTemplate.postForEntity("https://api.openai.com/v1/completions", request, Response.class);
            //Get the response
            Response POJOresponse = response.getBody();
            // this is for handling multiple choices
            List<Choice> choices = POJOresponse.getChoices();
            List<String> textChoices = choices.stream().map(choice -> choice.getText()).collect(Collectors.toList());

            //since we have only one chance :)
            List<QuestionAnswer> questionAnswers= new ArrayList<>();
            questionAnswers.add(new QuestionAnswer(input,textChoices.get(0)));
            //write to CSV
            CsvWriter.writeDataToCsv(questionAnswers);
            //write to Json
            JsonWriter.writeDataToJson(new QuestionAnswer(input.replaceAll("\"",""),textChoices.get(0)));
            return textChoices;
        } catch (HttpClientErrorException e) {
            // handle client error
            e.printStackTrace();
        } catch (HttpServerErrorException e) {
            // handle server error
            e.printStackTrace();
        }
      return new ArrayList<>();
    }
}
