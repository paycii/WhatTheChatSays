package com.IZICAP.sadiqui.services;

import com.IZICAP.sadiqui.mappers.Choice;
import com.IZICAP.sadiqui.mappers.Payload;
import com.IZICAP.sadiqui.mappers.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OpenAiServiceTest {

     @Test
   public void getAnswerTest() throws Exception {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        OpenAiService openAiService = Mockito.mock(OpenAiService.class);
        openAiService.setRestTemplate(restTemplate);
        openAiService.setToken("token");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer TOKEN");
        Payload payload = new Payload("text-davinci-003", "How are you doing", 4000, 1.0);
        HttpEntity<Payload> request = new HttpEntity<>(payload,headers);
        Response response = new Response();
        Choice choice = new Choice(); choice.setText("Im fine");
        List<Choice> choices = new ArrayList<>();choices.add(choice);
        response.setChoices(choices);
        ResponseEntity<Response> responseEntity = new ResponseEntity<>(response,HttpStatus.OK);

        when(restTemplate.postForEntity("https://api.openai.com/v1/completions", request, Response.class)).thenReturn(responseEntity);

        ResponseEntity<Response> responseEntity1 = restTemplate.postForEntity("https://api.openai.com/v1/completions", request, Response.class);

        when(openAiService.getAnswer("How are you doing")).thenReturn(responseEntity1.getBody().getChoices().stream().map(choice1 -> choice1.getText()).collect(Collectors.toList()));

        List<String> answers =openAiService.getAnswer("How are you doing");

        assertEquals(1,answers.size());
        assertEquals(answers.get(0),"Im fine");


    }
}