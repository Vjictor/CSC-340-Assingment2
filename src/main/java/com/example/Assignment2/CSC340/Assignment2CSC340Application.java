package com.example.Assignment2.CSC340;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Assignment2CSC340Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment2CSC340Application.class, args);

        // Call the method to fetch and print jokes
        theJokes();

    }

    public static void theJokes() {
        try {
            String url = "https://v2.jokeapi.dev/joke/Programming?type=single";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonResponse);

            String category = root.findValue("category").asText();
            String type = root.findValue("type").asText();
            String joke = root.findValue("joke").asText();

            System.out.println("**********JOKES FOR YOU********");
            System.out.println("Category: " + category);
            System.out.println("Type: " + type);
            System.out.println("Joke: " + joke);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(Assignment2CSC340Application.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
            System.out.println("error finding you a JOKE");

        }
    }

}
