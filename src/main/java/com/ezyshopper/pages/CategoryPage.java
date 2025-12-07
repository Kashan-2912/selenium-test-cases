package com.ezyshopper.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ezyshopper.utils.SeleniumUtils;

import java.util.List;

/**
 * Page Object Model for Category Page
 */
public class CategoryPage {
    
    private WebDriver driver;
    
    // Locators
    private By pageTitle = By.xpath("//h1[contains(@class, 'text-emerald-400')]");
    private By productCards = By.xpath("//div[contains(@class, 'flex w-full relative flex-col')]");
    private By noProductsMessage = By.xpath("//h2[contains(text(), 'No products found')]");
    private By addToCartButtons = By.xpath("//button[contains(., 'Add to cart')]");
    private By productNames = By.xpath("//h5[contains(@class, 'text-xl font-semibold')]");
    private By productPrices = By.xpath("//span[contains(@class, 'text-3xl font-bold text-emerald-400')]");
    
    // Constructor
    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Actions
    public boolean isCategoryPageLoaded() {
        return SeleniumUtils.isElementPresent(driver, pageTitle);
    }
    
    public String getCategoryTitle() {
        return SeleniumUtils.getTextSafely(driver, pageTitle);
    }
    
    public int getProductCount() {
        List<WebElement> products = driver.findElements(productCards);
        return products.size();
    }
    
    public boolean isNoProductsMessageDisplayed() {
        return SeleniumUtils.isElementPresent(driver, noProductsMessage);
    }
    
    public void clickAddToCartForFirstProduct() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }
    
    public void clickAddToCartForProduct(int index) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (index < buttons.size()) {
            buttons.get(index).click();
        }
    }
    
    public String getFirstProductName() {
        List<WebElement> names = driver.findElements(productNames);
        if (!names.isEmpty()) {
            return names.get(0).getText();
        }
        return "";
    }
    
    public String getFirstProductPrice() {
        List<WebElement> prices = driver.findElements(productPrices);
        if (!prices.isEmpty()) {
            return prices.get(0).getText();
        }
        return "";
    }
}
