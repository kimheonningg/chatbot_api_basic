package com.heonningg.chatbot_api_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heonningg.chatbot_api_basic.data.Answer;
import com.heonningg.chatbot_api_basic.data.Question;
import com.heonningg.chatbot_api_basic.service.ChatGPTService;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin()
@Validated
public class ChatGPTController {
    @Autowired
    ChatGPTService chatGPTService;

    @PostMapping("/chat_gpt")
    public Answer chatGPTQuestion(@RequestBody Question question) {
        return chatGPTService.getAnswer(question);
    }
}
