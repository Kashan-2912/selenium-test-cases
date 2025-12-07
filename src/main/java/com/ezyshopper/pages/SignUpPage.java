package com.ezyshopper.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ezyshopper.utils.SeleniumUtils;

/**
 * Page Object Model for Sign Up Page
 */
public class SignUpPage {
    
    private WebDriver driver;
    
    // Locators
    private By pageTitle = By.xpath("//h2[contains(text(), 'Create your account')]");
    private By nameInput = By.id("name");
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By confirmPasswordInput = By.id("confirmPassword");
    private By signUpButton = By.xpath("//button[@type='submit']");
    private By loginLink = By.xpath("//a[@href='/login']");
    private By errorMessage = By.xpath("//div[contains(@class, 'error')]");
    
    // Constructor
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Actions
    public boolean isSignUpPageLoaded() {
        return SeleniumUtils.isElementPresent(driver, pageTitle);
    }
    
    public void enterName(String name) {
        SeleniumUtils.safeSendKeys(driver, nameInput, name);
    }
    
    public void enterEmail(String email) {
        SeleniumUtils.safeSendKeys(driver, emailInput, email);
    }
    
    public void enterPassword(String password) {
        SeleniumUtils.safeSendKeys(driver, passwordInput, password);
    }
    
    public void enterConfirmPassword(String confirmPassword) {
        SeleniumUtils.safeSendKeys(driver, confirmPasswordInput, confirmPassword);
    }
    
    public void clickSignUpButton() {
        SeleniumUtils.safeClick(driver, signUpButton);
    }
    
    public void signUp(String name, String email, String password, String confirmPassword) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickSignUpButton();
    }
    
    public void clickLoginLink() {
        SeleniumUtils.safeClick(driver, loginLink);
    }
    
    public boolean isErrorMessageDisplayed() {
        return SeleniumUtils.isElementPresent(driver, errorMessage);
    }
}
