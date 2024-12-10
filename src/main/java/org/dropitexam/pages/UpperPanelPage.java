package org.dropitexam.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UpperPanelPage {

    private final Page upperPanelPage;
    private static final String CATALOG_BUTTON = "//*[@id=\"shopify-section-header\"]//span[contains(text(), 'Catalog')]";
    private static final String SEARCH_BUTTON = "details-modal[class='header__search']";

    private static final String BAG_BUTTON = "a#cart-icon-bubble";

    private static final String SEARCH_TEXTBOX = "input#Search-In-Modal";
    private static final String CART_COUNT_VERIFICATION = "//div[@class=\"cart-count-bubble\"]//span[contains(text(), '6 items')]";

    public UpperPanelPage(Page page){
        this.upperPanelPage = page;
    }

    public void clickCatalog(){
        assertThat(upperPanelPage.locator(SEARCH_BUTTON)).isVisible();
        upperPanelPage.locator(CATALOG_BUTTON).click();
//        assertThat(page.locator("//h2[contains(text(), 'Filter:')]")).isEnabled();
    }

    public void searchProduct(String productName){
        //click search
        //page.locator("div[class='header__icons'] summary[aria-label='Search']").click();
        upperPanelPage.locator(SEARCH_BUTTON).click();
        //enter text in search box
        upperPanelPage.locator(SEARCH_TEXTBOX).fill(productName);
        //verify search result
        assertThat(upperPanelPage.locator("//h3[contains(text(), '" + productName + "')]")).isVisible();
        //click the desired search result
        upperPanelPage.locator("//h3[contains(text(), '" + productName + "')]").click();
    }

    public void clickBagIcon(){
        assertThat(upperPanelPage.locator(BAG_BUTTON)).isVisible();
        assertThat(upperPanelPage.locator(CART_COUNT_VERIFICATION)).isVisible();
        upperPanelPage.locator(BAG_BUTTON).click();
    }

}
