package com.github.jactor.rises.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;

public final class SpringBootActuatorUtil {
    private SpringBootActuatorUtil() {
        // util class
    }

    public static boolean isServerRunning(String baseUrl) throws IOException {
        return isServerRunning(new URL(baseUrl + "/actuator/health"));
    }

    private static boolean isServerRunning(URL url) throws IOException {
        boolean serverRunning = false;

        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                serverRunning = reader.lines().anyMatch(line -> line.contains("\"status\":\"UP\""));
            }
        } catch (ConnectException e) {
            // server is not running...
        }

        return serverRunning;
    }
}
