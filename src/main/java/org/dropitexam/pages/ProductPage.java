package org.dropitexam.pages;

import com.microsoft.playwright.Page;
import org.apache.commons.text.StringEscapeUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductPage {

    private final Page productPage;
    private static final String ADD_TO_CART_BUTTON = "//span[contains(text(), 'Add to cart')]";
    private static final String CONTINUE_SHOPPING_BUTTON = "//button[contains(text(), 'Continue shopping')]";

    public ProductPage(Page page){
        this.productPage = page;
    }

    public void addToCart(String size, Integer quantity){

        productPage.locator("//label[contains(text(), '" + StringEscapeUtils.escapeHtml3(size) + "')]").click();
        //set the quantity
        productPage.locator("quantity-input[class='quantity'] input").fill(quantity.toString());

        //add to cart
        productPage.locator(ADD_TO_CART_BUTTON).click();
        //continue shopping
        productPage.locator(CONTINUE_SHOPPING_BUTTON).click();
    }
}
