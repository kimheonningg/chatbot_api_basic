package com.heonningg.chatbot_api_basic.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Answer {
    
    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }
}
