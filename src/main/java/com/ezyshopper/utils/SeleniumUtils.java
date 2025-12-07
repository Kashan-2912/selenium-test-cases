package com.ezyshopper.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * Utility class for common Selenium operations
 */
public class SeleniumUtils {
    
    /**
     * Initialize WebDriver based on browser type
     */
    public static WebDriver initializeDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (TestConfig.HEADLESS_MODE) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--single-process");
                chromeOptions.addArguments("--remote-allow-origins=*");

                // unique profile for each session
                String unique = "/tmp/chrome-profile-" + System.nanoTime();
                chromeOptions.addArguments("--user-data-dir=" + unique);

                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestConfig.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestConfig.PAGE_LOAD_TIMEOUT));
        driver.manage().window().maximize();

        return driver;
    }

    
    /**
     * Wait for element to be clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be present
     */
    public static WebElement waitForElementToBePresent(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Safe click with explicit wait
     */
    public static void safeClick(WebDriver driver, By locator) {
        WebElement element = waitForElementToBeClickable(driver, locator, TestConfig.EXPLICIT_WAIT);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(200);
            element.click();
        } catch (Exception e) {
            // fallback JS click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
    
    /**
     * Safe send keys with explicit wait
     */
    public static void safeSendKeys(WebDriver driver, By locator, String text) {
        WebElement element = waitForElementToBeVisible(driver, locator, TestConfig.EXPLICIT_WAIT);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Scroll to element
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Take screenshot
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = TestConfig.SCREENSHOT_DIR + fileName;
        
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(scrFile, destFile);
            System.out.println("Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Check if element is present
     */
    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    /**
     * Wait for page to load
     */
    public static void waitForPageLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.PAGE_LOAD_TIMEOUT));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
    
    /**
     * Get element text safely
     */
    public static String getTextSafely(WebDriver driver, By locator) {
        try {
            WebElement element = waitForElementToBeVisible(driver, locator, TestConfig.EXPLICIT_WAIT);
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
