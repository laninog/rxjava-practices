package org.training.utils;

import org.springframework.web.client.RestTemplate;

public class Utils {

    public static String invoke(String URI) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URI, String.class);
        return result;
    }

    public static void hold(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
