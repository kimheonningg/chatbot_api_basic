package com.heonningg.chatbot_api_basic.data;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private String question;
    @Nullable
    private String aiType;
    @Nullable
    private String systemMessage;

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, String aiType) {
        this.question = question;
        this.aiType = aiType;
    }
}
