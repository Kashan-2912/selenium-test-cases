package com.ezyshopper.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ezyshopper.utils.SeleniumUtils;

/**
 * Page Object Model for Login Page
 */
public class LoginPage {
    
    private WebDriver driver;
    
    // Locators
    private By pageTitle = By.xpath("//h2[contains(text(), 'Login to your account')]");
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By signUpLink = By.xpath("//a[@href='/signup']");
    private By errorMessage = By.xpath("//div[contains(@class, 'error')]");
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Actions
    public boolean isLoginPageLoaded() {
        return SeleniumUtils.isElementPresent(driver, pageTitle);
    }
    
    public void enterEmail(String email) {
        SeleniumUtils.safeSendKeys(driver, emailInput, email);
    }
    
    public void enterPassword(String password) {
        SeleniumUtils.safeSendKeys(driver, passwordInput, password);
    }
    
    public void clickLoginButton() {
        SeleniumUtils.safeClick(driver, loginButton);
    }
    
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
    
    public void clickSignUpLink() {
        SeleniumUtils.safeClick(driver, signUpLink);
    }
    
    public boolean isErrorMessageDisplayed() {
        return SeleniumUtils.isElementPresent(driver, errorMessage);
    }
}
