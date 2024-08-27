package com.heonningg.chatbot_api_basic.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTMessage {
    private String role;
    private String content;
}
