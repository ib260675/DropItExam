package org.dropitexam;

import com.microsoft.playwright.*;
import org.dropitexam.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseGuiTestRunner {

    protected Page page;
    protected BrowserContext browserContext;
    protected Browser browser;
    protected static Playwright playwright;

    //pages
    protected LoginPage loginPage;
    protected UpperPanelPage upperPanelPage;
    protected ProductPage productPage;
    protected YourCartPage yourCartPage;
    protected CartCheckoutPage cartCheckoutPage;

    @BeforeAll
    public static void init(){
        playwright = Playwright.create();
    }

    @BeforeEach
    public void setUp(){
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page =browserContext.newPage();

        //instantinate pages
        loginPage = new LoginPage(page);
        upperPanelPage = new UpperPanelPage(page);
        productPage = new ProductPage(page);
        yourCartPage = new YourCartPage(page);
        cartCheckoutPage =new CartCheckoutPage(page);
    }

    @AfterEach
    public void tearDown(){
        browserContext.close();
        browser.close();
    }
}
