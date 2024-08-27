package com.heonningg.chatbot_api_basic.data;

import java.util.Map;
import lombok.Data;

@Data
public class GPTChoice {
    private int index;
    private GPTMessage message;
    private Map<String, double[]> logprobs;
    private String finish_reason;
}
