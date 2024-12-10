package org.dropitexam;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GuiTests extends BaseGuiTestRunner{

    @Test
    public void positiveTestScenario(){
        page.navigate("https://drpt-external-dev.myshopify.com/password");

        loginPage.loginWithPassword("giclao");
        upperPanelPage.clickCatalog();

        upperPanelPage.searchProduct("Dropit Hamburger (QA Automation)");
        productPage.addToCart("Medium", 2);
        productPage.addToCart("So large you can", 1);

        upperPanelPage.searchProduct("Dropit Chips (QA Automation)");
        productPage.addToCart("Large", 2);
        productPage.addToCart("Too much for you to handle", 1);

        upperPanelPage.clickBagIcon();
        yourCartPage.clickCheckout();
        cartCheckoutPage.verifyTotalPrice("£56.99");
        cartCheckoutPage.verifyTotalPriceWithoutShipping("£33.00");
        cartCheckoutPage.fillContactDetailsWithValidDummyValues();
        cartCheckoutPage.fillValidPaymentDetails();
        cartCheckoutPage.clickPayNow();
        cartCheckoutPage.verifyPaymentConfirmation();

        System.out.println();
    }

    @Test
    public void negativeTestScenario(){
        page.navigate("https://drpt-external-dev.myshopify.com/password");

        loginPage.loginWithPassword("giclao");
        upperPanelPage.clickCatalog();

        upperPanelPage.searchProduct("Dropit Hamburger (QA Automation)");
        productPage.addToCart("Medium", 2);
        productPage.addToCart("So large you can", 1);

        upperPanelPage.searchProduct("Dropit Chips (QA Automation)");
        productPage.addToCart("Large", 2);
        productPage.addToCart("Too much for you to handle", 1);

        upperPanelPage.clickBagIcon();
        yourCartPage.clickCheckout();
        cartCheckoutPage.verifyTotalPrice("£56.99");
        cartCheckoutPage.fillContactDetailsWithNonValidDummyValues();
        cartCheckoutPage.fillNonValidPaymentDetails();
        cartCheckoutPage.clickPayNow();
        cartCheckoutPage.verifyPaymentDeny();

        System.out.println();
    }
}
