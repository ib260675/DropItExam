package org.dropitexam.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    private final Page loginPage;

    public LoginPage(Page page){
        this.loginPage = page;
    }

    public void loginWithPassword(String password){
        assertThat(loginPage.locator("label[for='password']")).isVisible();

        //fill password and click enter
        loginPage.locator("input#password").fill("giclao");
        loginPage.locator("button[type='submit']").click();

        assertThat(loginPage.locator("div#shopify-section-announcement-bar p")).containsText(
                "Welcome to our store", new LocatorAssertions.ContainsTextOptions().setIgnoreCase(true));
    }
}
