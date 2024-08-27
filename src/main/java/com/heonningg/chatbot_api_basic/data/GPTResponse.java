package com.heonningg.chatbot_api_basic.data;

import lombok.Data;

import com.google.gson.Gson;

@Data
public class GPTResponse {
    private String id;
    private String object;
    private String created;
    private String model;
    private GPTChoice[] choices;
    private GPTUsage usage;
    private String system_fingerprint;

    //Serialize the GPTResponse object
    public String serializeGPTResponse() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    //Deserialize the GPTResponse object
    public GPTResponse deserializeGPTResponse(String serializedGPTResponse) {
        if(serializedGPTResponse == null || serializedGPTResponse.isEmpty())
            return null;
        
        Gson gson = new Gson();
        GPTResponse deserialized = gson.fromJson(serializedGPTResponse, GPTResponse.class);
        return deserialized;
    }
}
