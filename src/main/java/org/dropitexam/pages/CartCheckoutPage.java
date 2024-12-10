package org.dropitexam.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartCheckoutPage {

    private final Page cartCheckoutPage;
    private static final String EMAIL = "input[name='email'][aria-required='true']";
    private static final String EMAIL_ME_NEWS = "input#marketing_opt_in";
    private static final String COUNTRY = "select#Select0";
    private static final String FIRST_NAME = "input#TextField0";
    private static final String LAST_NAME = "input[name='lastName'][aria-required='true']";
    private static final String ADDRESS = "input[name='address1'][aria-required='true']";
    private static final String CITY = "input[name='city'][aria-required='true']";

    private static final String CARD_NUMBER = "input[placeholder='Card number'][id='number']";
    private static final String EXP_DATE = "input[data-current-field='expiry'][id='expiry']";
    private static final String SEC_CODE = "input[data-current-field='verification_value'][id='verification_value']";
    private static final String NAME_ON_CARD = "input[data-current-field='name'][id='name']";
    private static final String PAY_NOW_BUTTON = "#checkout-pay-button";

    private static final String ORDER_CONFIRMATION_LABEL = "//h2[contains(text(), 'Your order is confirmed')]";
    private static final String INVALID_EMAIL_MESSAGE = "//p[contains(text(), 'Enter a valid email')]";
    private static final String INVALID_CREDIT_CARD_NUMBER_MESSAGE = "//p[contains(text(), 'Enter a valid card number')]";

    public CartCheckoutPage(Page page){
        this.cartCheckoutPage = page;
    }

    public void verifyTotalPrice(String expectedTotalPrice){
        assertThat(cartCheckoutPage.locator("//*[@id=\"app\"]//strong[contains(text(), '" + expectedTotalPrice + "')]")).isVisible();
    }

    public void verifyTotalPriceWithoutShipping(String expectedTotalPrice){
        assertThat(cartCheckoutPage.locator("//*[@id=\"app\"]//span[contains(text(), '" + expectedTotalPrice + "')]")).isVisible();
    }

    public void fillContactDetailsWithValidDummyValues(){
        cartCheckoutPage.locator(EMAIL).fill("myValidMail@valid.com");
//        cartCheckoutPage.locator(EMAIL_ME_NEWS).check();
//        cartCheckoutPage.locator(COUNTRY).selectOption("Israel");
//        cartCheckoutPage.locator(FIRST_NAME).fill("FirstName");
        cartCheckoutPage.locator(LAST_NAME).fill("LastName");
        cartCheckoutPage.locator(ADDRESS).fill("some address");
        cartCheckoutPage.locator(CITY).fill("MyCity");

        System.out.println();
    }

    public void fillContactDetailsWithNonValidDummyValues(){
        cartCheckoutPage.locator(EMAIL).fill("NonValid");
        assertThat(cartCheckoutPage.locator(INVALID_EMAIL_MESSAGE)).isVisible();
        String theColor =  cartCheckoutPage.locator(INVALID_EMAIL_MESSAGE).evaluate("e => window.getComputedStyle(e).color").toString();
        assertEquals("rgb(221, 29, 29)", theColor);
//        cartCheckoutPage.locator(EMAIL_ME_NEWS).check();
//        cartCheckoutPage.locator(COUNTRY).selectOption("Israel");
//        cartCheckoutPage.locator(FIRST_NAME).fill("FirstName");
        cartCheckoutPage.locator(LAST_NAME).fill("LastName");
        cartCheckoutPage.locator(ADDRESS).fill("some address");
        cartCheckoutPage.locator(CITY).fill("MyCity");

        System.out.println();
    }

    public void fillValidPaymentDetails(){
        cartCheckoutPage.locator(CARD_NUMBER).focus();
        cartCheckoutPage.locator(CARD_NUMBER).fill("1");
        cartCheckoutPage.locator(EXP_DATE).fill("12/26");
        cartCheckoutPage.locator(SEC_CODE).fill("777");
        cartCheckoutPage.locator(NAME_ON_CARD).fill("Bogus Gateway");
    }

    public void fillNonValidPaymentDetails(){
        cartCheckoutPage.locator(CARD_NUMBER).fill("NonValid");
        cartCheckoutPage.locator(EXP_DATE).fill("12/26");
        cartCheckoutPage.locator(SEC_CODE).fill("777");
        cartCheckoutPage.locator(NAME_ON_CARD).fill("Bogus Gateway");
    }

    public void clickPayNow(){
        cartCheckoutPage.locator(PAY_NOW_BUTTON).click();
    }

    public void verifyPaymentConfirmation(){
        assertThat(cartCheckoutPage.locator(ORDER_CONFIRMATION_LABEL)).isVisible();
    }

    public void verifyPaymentDeny(){
        assertThat(cartCheckoutPage.locator(INVALID_CREDIT_CARD_NUMBER_MESSAGE)).isVisible();
    }
}
