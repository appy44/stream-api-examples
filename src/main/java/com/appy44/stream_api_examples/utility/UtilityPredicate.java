package com.appy44.stream_api_examples.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class UtilityPredicate {
    public static final String LINE_BREAK = "---------------------------------------------------------";

    public static <I> String printJSON(I obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println(e + "");
        }
        return null;
    }

    public static Object testJSON(String strMessage) {
        JSONObject obj = new JSONObject(strMessage);
        return obj.opt("name");
    }
}
