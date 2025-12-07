package com.ezyshopper.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ezyshopper.utils.SeleniumUtils;
import com.ezyshopper.utils.TestConfig;

/**
 * Page Object Model for Navigation Bar
 */
public class NavigationBar {
    
    private WebDriver driver;
    
    // Locators
    private By logo = By.xpath("//a[contains(text(), 'EzyShopper')]");
    private By homeLink = By.xpath("//a[@href='/']");
    private By cartLink = By.xpath("//a[@href='/cart']");
    private By cartCount = By.xpath("//a[@href='/cart']//span[contains(@class, 'bg-emerald-500')]");
    private By dashboardLink = By.xpath("//a[@href='/secret-dashboard']");
    private By signUpButton = By.xpath("//a[@href='/signup']");
    private By loginButton = By.xpath("//a[@href='/login']");
    private By logoutButton = By.xpath("//button[contains(., 'Log Out')]");
    
    // Constructor
    public NavigationBar(WebDriver driver) {
        this.driver = driver;
    }
    
    // Actions
    public void clickLogo() {
        SeleniumUtils.safeClick(driver, logo);
    }
    
    public void clickHome() {
        SeleniumUtils.safeClick(driver, homeLink);
    }
    
    public void clickCart() {
        SeleniumUtils.safeClick(driver, cartLink);
    }
    
    public void clickDashboard() {
        SeleniumUtils.safeClick(driver, dashboardLink);
    }
    
    public void clickSignUp() {
        SeleniumUtils.safeClick(driver, signUpButton);
    }
    
    public void clickLogin() {
        SeleniumUtils.safeClick(driver, loginButton);
    }
    
    public void clickLogout() {
        SeleniumUtils.safeClick(driver, logoutButton);
    }
    
    public boolean isCartLinkVisible() {
        return SeleniumUtils.isElementPresent(driver, cartLink);
    }
    
    public boolean isDashboardLinkVisible() {
        return SeleniumUtils.isElementPresent(driver, dashboardLink);
    }
    
    public boolean isLogoutButtonVisible() {
        return SeleniumUtils.isElementPresent(driver, logoutButton);
    }
    
    public boolean isLoginButtonVisible() {
        return SeleniumUtils.isElementPresent(driver, loginButton);
    }
    
    public boolean isSignUpButtonVisible() {
        return SeleniumUtils.isElementPresent(driver, signUpButton);
    }
    
    public String getCartCount() {
        return SeleniumUtils.getTextSafely(driver, cartCount);
    }
    
    public boolean isCartCountDisplayed() {
        return SeleniumUtils.isElementPresent(driver, cartCount);
    }
}
