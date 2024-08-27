package com.heonningg.chatbot_api_basic.service;

import org.springframework.stereotype.Service;

import com.heonningg.chatbot_api_basic.data.Answer;
import com.heonningg.chatbot_api_basic.data.Question;

@Service
public interface ChatService {
    public Answer getAnswer(Question question);
    public void saveQandA(Question question, Answer answer);
}
