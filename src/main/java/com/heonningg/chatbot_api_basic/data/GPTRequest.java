package com.heonningg.chatbot_api_basic.data;

import com.google.gson.Gson;
import java.util.List;
import lombok.Data;

@Data
public class GPTRequest{
    private String model;
    //3 models- system, user, assistant
    private List<GPTMessage> messages;

    //Serialize the GPTRequest object
    public String serializeGPTRequest() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    //Deserialize the GPTRequest object
    public GPTRequest deserializeGPTRequest(String serializedGPTRequest) {
        if(serializedGPTRequest == null || serializedGPTRequest.isEmpty())
            return null;
        if(!serializedGPTRequest.contains("model") || !serializedGPTRequest.contains("messages"))
            return null;
        
        Gson gson = new Gson();
        GPTRequest deserialized = gson.fromJson(serializedGPTRequest, GPTRequest.class);
        return deserialized;
    }
}
