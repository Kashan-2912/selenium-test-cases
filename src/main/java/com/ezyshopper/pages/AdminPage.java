package com.ezyshopper.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ezyshopper.utils.SeleniumUtils;

/**
 * Page Object Model for Admin Dashboard Page
 */
public class AdminPage {
    
    private WebDriver driver;
    
    // Locators
    private By pageTitle = By.xpath("//h1[contains(text(), 'Admin Dashboard')]");
    private By createProductTab = By.xpath("//button[contains(., 'Create Product')]");
    private By productsTab = By.xpath("//button[contains(., 'Products')]");
    private By analyticsTab = By.xpath("//button[contains(., 'Analytics')]");
    
    // Create Product Form Locators
    private By productNameInput = By.id("name");
    private By productDescriptionInput = By.id("description");
    private By productPriceInput = By.id("price");
    private By productCategorySelect = By.id("category");
    private By productImageInput = By.id("image");
    private By createProductButton = By.xpath("//button[contains(., 'Create Product')][@type='submit']");
    
    // Products List Locators
    private By productsList = By.xpath("//div[contains(@class, 'product-list')]");
    private By deleteProductButtons = By.xpath("//button[contains(., 'Delete')]");
    private By toggleFeaturedButtons = By.xpath("//button[contains(., 'Featured')]");
    
    // Analytics Locators
    private By totalSalesCard = By.xpath("//div[contains(text(), 'Total Sales')]");
    private By totalUsersCard = By.xpath("//div[contains(text(), 'Total Users')]");
    private By totalProductsCard = By.xpath("//div[contains(text(), 'Total Products')]");
    private By totalOrdersCard = By.xpath("//div[contains(text(), 'Total Orders')]");
    
    // Constructor
    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Actions
    public boolean isAdminPageLoaded() {
        return SeleniumUtils.isElementPresent(driver, pageTitle);
    }
    
    public void clickCreateProductTab() {
        SeleniumUtils.safeClick(driver, createProductTab);
    }
    
    public void clickProductsTab() {
        SeleniumUtils.safeClick(driver, productsTab);
    }
    
    public void clickAnalyticsTab() {
        SeleniumUtils.safeClick(driver, analyticsTab);
    }
    
    // Create Product Actions
    public void enterProductName(String name) {
        SeleniumUtils.safeSendKeys(driver, productNameInput, name);
    }
    
    public void enterProductDescription(String description) {
        SeleniumUtils.safeSendKeys(driver, productDescriptionInput, description);
    }
    
    public void enterProductPrice(String price) {
        SeleniumUtils.safeSendKeys(driver, productPriceInput, price);
    }
    
    public void selectProductCategory(String category) {
        SeleniumUtils.safeClick(driver, productCategorySelect);
        By categoryOption = By.xpath("//option[@value='" + category + "']");
        SeleniumUtils.safeClick(driver, categoryOption);
    }
    
    public void uploadProductImage(String imagePath) {
        driver.findElement(productImageInput).sendKeys(imagePath);
    }
    
    public void clickCreateProductButton() {
        SeleniumUtils.safeClick(driver, createProductButton);
    }
    
    public void createProduct(String name, String description, String price, String category) {
        enterProductName(name);
        enterProductDescription(description);
        enterProductPrice(price);
        selectProductCategory(category);
        clickCreateProductButton();
    }
    
    // Products List Actions
    public boolean isProductsListDisplayed() {
        return SeleniumUtils.isElementPresent(driver, productsList);
    }
    
    public void clickDeleteFirstProduct() {
        SeleniumUtils.safeClick(driver, deleteProductButtons);
    }
    
    // Analytics Actions
    public boolean isTotalSalesCardDisplayed() {
        return SeleniumUtils.isElementPresent(driver, totalSalesCard);
    }
    
    public boolean isTotalUsersCardDisplayed() {
        return SeleniumUtils.isElementPresent(driver, totalUsersCard);
    }
    
    public boolean isTotalProductsCardDisplayed() {
        return SeleniumUtils.isElementPresent(driver, totalProductsCard);
    }
    
    public boolean isTotalOrdersCardDisplayed() {
        return SeleniumUtils.isElementPresent(driver, totalOrdersCard);
    }
}
