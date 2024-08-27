package com.heonningg.chatbot_api_basic.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.heonningg.chatbot_api_basic.data.Answer;
import com.heonningg.chatbot_api_basic.data.GPTChoice;
import com.heonningg.chatbot_api_basic.data.GPTMessage;
import com.heonningg.chatbot_api_basic.data.GPTRequest;
import com.heonningg.chatbot_api_basic.data.GPTResponse;
import com.heonningg.chatbot_api_basic.data.Question;

import io.micrometer.common.lang.Nullable;

@Service
public class ChatGPTService implements ChatService{

    @Value("${chatbot.gpt-api-key}")
    private String GPT_API_KEY;

    public Answer getAnswer(Question question) {
        Answer chatGPTAnswer = new Answer();
        chatGPTAnswer.setAnswer(askChatGPT(question.getQuestion(), question.getAiType(), question.getSystemMessage()));
        return chatGPTAnswer;
    }
    public void saveQandA(Question question, Answer answer) {

    }

    private String askChatGPT(String prompt, String gptModel, @Nullable String systemMessage){
        String url = "https://api.openai.com/v1/chat/completions"; //TODO: seperate this into another file
        
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + GPT_API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");

            String body; // The request body
            
            GPTRequest gptRequest = new GPTRequest();
            gptRequest = makeGptRequestInstance(prompt, gptModel, systemMessage);
            body = gptRequest.serializeGPTRequest();

            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            return extractMessageFromJSONResponse(response.toString());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GPTRequest makeGptRequestInstance (String prompt, String gptModel, @Nullable String systemMessage) {
        GPTRequest gptRequest = new GPTRequest();
        gptRequest.setModel(gptModel);

        List<GPTMessage> gptMessages = new ArrayList<>();

        if(systemMessage != null) { //system message exists
            GPTMessage system = new GPTMessage("system", systemMessage);
            gptMessages.add(system);  
        }
        GPTMessage user = new GPTMessage("user", prompt);
        gptMessages.add(user);

        gptRequest.setMessages(gptMessages);
       
        return gptRequest;
    }

    //parse GPT's answer from response
    public static String extractMessageFromJSONResponse(String response) {
        GPTResponse gptResponse = new GPTResponse();
        gptResponse = gptResponse.deserializeGPTResponse(response.toString());
        GPTChoice[] gptChoices = gptResponse.getChoices();
        String serializedAnswer = gptChoices[gptChoices.length-1].getMessage().getContent();
        return serializedAnswer;
    }
}
