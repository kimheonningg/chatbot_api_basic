package com.heonningg.chatbot_api_basic.data;

import io.micrometer.common.lang.Nullable;

public class GPTUsage {
    private int prompt_tokens;
    @Nullable
    private int completion_tokens;
    private int total_tokens;
}
