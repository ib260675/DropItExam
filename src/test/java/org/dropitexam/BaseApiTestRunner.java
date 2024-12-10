package org.dropitexam;

import com.microsoft.playwright.*;
import org.dropitexam.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

public class BaseApiTestRunner {

    protected static final String BASE_URL = "https://petstore.swagger.io/";
    private static Map<String, String> headers;

    protected APIRequestContext apiRequestContext;
    protected static Playwright playwright;

    @BeforeAll
    public static void init(){
        playwright = Playwright.create();
        headers = new HashMap<>();
        headers.put("Content-Type", "Application/Json");
    }

    @BeforeEach
    public void setUp(){
        apiRequestContext = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(BASE_URL)
                .setExtraHTTPHeaders(headers)
        );
    }

    @AfterEach
    public void tearDown(){
        apiRequestContext.dispose();
        playwright.close();
    }
}
