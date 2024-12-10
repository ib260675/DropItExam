package org.dropitexam.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class YourCartPage {

    private final Page yourCartPage;
    private static final String CHECKOUT_BUTTON = "button#checkout";
    private static final String YOUR_CART_LABEL = "//h1[@class=\"title title--primary\"][contains(text(), 'Your cart')]";

    public YourCartPage(Page page){
        this.yourCartPage = page;
    }

    public void clickCheckout(){
        assertThat(yourCartPage.locator(YOUR_CART_LABEL)).isVisible();
        assertThat(yourCartPage.locator(CHECKOUT_BUTTON)).isVisible();
        yourCartPage.locator(CHECKOUT_BUTTON).click();
    }
}
