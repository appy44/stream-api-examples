package com.appy44.stream_api_examples.entity.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class MockData {
    // Change this to your actual endpoint
    private static final String EMPLOYEE_JSON_URL = "your url";
    // Classpath fallback (put employees.json in src/main/resources/)
    private static final String CLASSPATH_RESOURCE = "/employees.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    /**
     * Returns a list of sample Employee objects.
     * Tries remote fetch first, then falls back to classpath JSON.
     */
    public static List<Employee> getSampleEmployees() {
        // 1) Try remote
//        try {
//            HttpRequest req = HttpRequest.newBuilder()
//                    .uri(URI.create(EMPLOYEE_JSON_URL))
//                    .timeout(Duration.ofSeconds(5))
//                    .GET()
//                    .build();
//
//            HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
//            if (resp.statusCode() == 200) {
//                return MAPPER.readValue(resp.body(), new TypeReference<List<Employee>>() {
//                });
//            } else {
//                System.err.println("Warning: remote fetch returned HTTP " + resp.statusCode());
//            }
//        } catch (Exception e) {
//            System.err.println("Warning: could not fetch remote employees.json → " + e.getMessage());
//        }
// 2) Fallback to classpath resource, reading it fully as a String
        try {
            // Locate the resource URL
            URL resourceUrl = MockData.class.getResource(CLASSPATH_RESOURCE);
            if (resourceUrl != null) {
                // Read the file all at once
                String json = Files.readString(
                        Paths.get(resourceUrl.toURI()),
                        StandardCharsets.UTF_8
                );
                // Parse from String
                return MAPPER.readValue(json, new TypeReference<List<Employee>>() {
                });
            } else {
                System.err.println("Warning: " + CLASSPATH_RESOURCE + " not found on classpath");
            }
        } catch (Exception e) {
            System.err.println("Warning: failed to read classpath employees.json → " + e.getMessage());
        }


        // 3) As last resort, return empty list
        return Collections.emptyList();
    }
}
