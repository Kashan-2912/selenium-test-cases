package com.ezyshopper.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ezyshopper.utils.SeleniumUtils;

import java.util.List;

/**
 * Page Object Model for Cart Page
 */
public class CartPage {
    
    private WebDriver driver;
    
    // Locators
    private By emptyCartMessage = By.xpath("//div[contains(text(), 'cart is empty')]");
    private By cartItems = By.xpath("//div[contains(@class, 'space-y-6')]/div");
    private By removeButtons = By.xpath("//button[contains(., 'Remove')]");
    private By quantityInputs = By.xpath("//input[@type='number']");
    private By subtotalAmount = By.xpath("//span[contains(text(), 'Subtotal')]");
    private By totalAmount = By.xpath("//span[contains(text(), 'Total')]");
    private By checkoutButton = By.xpath("//button[contains(., 'Proceed to Checkout')]");
    private By couponInput = By.xpath("//input[@placeholder='Enter coupon code']");
    private By applyCouponButton = By.xpath("//button[contains(., 'Apply Coupon')]");
    
    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Actions
    public boolean isCartPageLoaded() {
        return driver.getCurrentUrl().contains("/cart");
    }
    
    public boolean isCartEmpty() {
        return SeleniumUtils.isElementPresent(driver, emptyCartMessage);
    }
    
    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }
    
    public void clickRemoveFirstItem() {
        List<WebElement> buttons = driver.findElements(removeButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }
    
    public void updateQuantityForFirstItem(String quantity) {
        List<WebElement> inputs = driver.findElements(quantityInputs);
        if (!inputs.isEmpty()) {
            inputs.get(0).clear();
            inputs.get(0).sendKeys(quantity);
        }
    }
    
    public String getSubtotal() {
        return SeleniumUtils.getTextSafely(driver, subtotalAmount);
    }
    
    public String getTotal() {
        return SeleniumUtils.getTextSafely(driver, totalAmount);
    }
    
    public void clickCheckout() {
        SeleniumUtils.safeClick(driver, checkoutButton);
    }
    
    public void enterCouponCode(String couponCode) {
        SeleniumUtils.safeSendKeys(driver, couponInput, couponCode);
    }
    
    public void clickApplyCoupon() {
        SeleniumUtils.safeClick(driver, applyCouponButton);
    }
    
    public void applyCoupon(String couponCode) {
        enterCouponCode(couponCode);
        clickApplyCoupon();
    }
}
