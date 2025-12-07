package com.ezyshopper.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ezyshopper.utils.SeleniumUtils;

/**
 * Page Object Model for Home Page
 */
public class HomePage {
    
    private WebDriver driver;
    
    // Locators
    private By pageTitle = By.xpath("//h1[contains(text(), 'Explore Our Categories')]");
    private By categoryJeans = By.xpath("//a[@href='/category/jeans']");
    private By categoryTshirts = By.xpath("//a[@href='/category/t-shirts']");
    private By categoryShoes = By.xpath("//a[@href='/category/shoes']");
    private By categoryGlasses = By.xpath("//a[@href='/category/glasses']");
    private By categoryJackets = By.xpath("//a[@href='/category/jackets']");
    private By categorySuits = By.xpath("//a[@href='/category/suits']");
    private By categoryBags = By.xpath("//a[@href='/category/bags']");
    private By featuredProductsSection = By.xpath("//section[contains(@class, 'featured')]");
    
    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Actions
    public boolean isHomePageLoaded() {
        return SeleniumUtils.isElementPresent(driver, pageTitle);
    }
    
    public String getPageTitle() {
        return SeleniumUtils.getTextSafely(driver, pageTitle);
    }
    
    public void clickCategoryJeans() {
        SeleniumUtils.safeClick(driver, categoryJeans);
    }
    
    public void clickCategoryTshirts() {
        SeleniumUtils.safeClick(driver, categoryTshirts);
    }
    
    public void clickCategoryShoes() {
        SeleniumUtils.safeClick(driver, categoryShoes);
    }
    
    public void clickCategoryGlasses() {
        SeleniumUtils.safeClick(driver, categoryGlasses);
    }
    
    public void clickCategoryJackets() {
        SeleniumUtils.safeClick(driver, categoryJackets);
    }
    
    public void clickCategorySuits() {
        SeleniumUtils.safeClick(driver, categorySuits);
    }
    
    public void clickCategoryBags() {
        SeleniumUtils.safeClick(driver, categoryBags);
    }
    
    public boolean isFeaturedProductsSectionDisplayed() {
        return SeleniumUtils.isElementPresent(driver, featuredProductsSection);
    }
}
