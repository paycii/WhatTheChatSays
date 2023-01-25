package com.IZICAP.sadiqui.controllers;


import com.IZICAP.sadiqui.mappers.QuestionAnswer;
import com.IZICAP.sadiqui.services.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// this is the main controller, including a post method "getAnswer" to submit the input for the user
// and returning the input to the "openAiService.getAnswer" method

@RestController
@RequestMapping("/WhatTheChatSays")
public class ChatController {
    @Autowired
    private OpenAiService openAiService;


    @PostMapping(path = "/answer")
    public List<String> getAnswer(@RequestBody String input) throws Exception {
        return this.openAiService.getAnswer(input);
    }

}
