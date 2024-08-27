package com.heonningg.chatbot_api_basic.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heonningg.chatbot_api_basic.data.ChatbotVersion;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/test")
@CrossOrigin()
@Validated
// For Test
public class TestController {

    // Simple controller to check if this program is running
    @GetMapping("/version")
    public ChatbotVersion getCurrentVersion() { 
        ChatbotVersion version = new ChatbotVersion("1.0.0");
        return version;
    }
}
