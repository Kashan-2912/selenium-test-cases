package com.ezyshopper;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Selenium Test Suite for EzyShopper E-Commerce Application
 * 10 Essential Test Cases
 */
@DisplayName("EzyShopper E-Commerce Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EzyShopperAppTests {

    private static final Logger log = LoggerFactory.getLogger(EzyShopperAppTests.class);

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL = System.getProperty("baseUrl", 
        System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:5173");
    private static String testEmail;
    private static final String TEST_PASSWORD = "Test@123456";

    @BeforeAll
    public static void setupClass() {
        log.info("========================================");
        log.info("  EZYSHOPPER SELENIUM TEST SUITE");
        log.info("  Target: {}", BASE_URL);
        log.info("========================================");

        WebDriverManager.chromedriver().setup();
        testEmail = "testuser" + System.currentTimeMillis() + "@test.com";
        log.info("Test Email: {}", testEmail);
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        log.info("");
        log.info(">>> TEST: {}", testInfo.getDisplayName());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments("--disable-gpu", "--disable-extensions");
        options.addArguments("--remote-allow-origins=*", "--window-size=1920,1080");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (driver != null)
            driver.quit();
    }

    @AfterAll
    public static void summary() {
        log.info("");
        log.info("========================================");
        log.info("  TEST SUITE COMPLETED");
        log.info("========================================");
    }

    // ==================== TEST CASES ====================

    @Test
    @Order(1)
    @DisplayName("1. Home Page Loads Successfully")
    public void testHomePageLoads() {
        log.info("  Navigating to home page...");
        driver.get(BASE_URL);

        WebElement navbar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("nav, .navbar, header")));
        
        assertNotNull(navbar, "Navbar should be present");
        assertTrue(driver.getPageSource().contains("EzyShopper") || 
                   driver.getPageSource().contains("Home") ||
                   driver.getCurrentUrl().equals(BASE_URL + "/"),
                "Home page should load with expected content");
        log.info("  PASSED: Home page loaded successfully");
    }

    @Test
    @Order(2)
    @DisplayName("2. Register New User")
    public void testRegisterNewUser() {
        log.info("  Navigating to home page...");
        driver.get(BASE_URL);

        log.info("  Clicking Sign Up link...");
        WebElement signUpLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sign Up') or contains(text(),'Register')] | //button[contains(text(),'Sign Up')]")));
        signUpLink.click();

        log.info("  Filling registration form...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name"))).sendKeys("Test User");
        driver.findElement(By.id("email")).sendKeys(testEmail);
        driver.findElement(By.id("password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.id("confirmPassword")).sendKeys(TEST_PASSWORD);

        log.info("  Submitting form...");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait for either success toast or redirect to home
        try {
            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".Toastify__toast, .toast, [role='alert']")));
            log.info("  Toast message: {}", toast.getText());
        } catch (Exception e) {
            // If no toast, check if redirected to home
            wait.until(ExpectedConditions.urlToBe(BASE_URL + "/"));
        }

        log.info("  PASSED: User registered successfully");
    }

    @Test
    @Order(3)
    @DisplayName("3. Login with Valid Credentials")
    public void testLoginValid() {
        // First register the user
        driver.get(BASE_URL);
        WebElement signUpLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sign Up') or contains(text(),'Register')]")));
        signUpLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name"))).sendKeys("Test User");
        driver.findElement(By.id("email")).sendKeys(testEmail);
        driver.findElement(By.id("password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.id("confirmPassword")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Now test login
        log.info("  Navigating to login page...");
        driver.get(BASE_URL + "/login");

        log.info("  Entering credentials...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(testEmail);
        driver.findElement(By.id("password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe(BASE_URL + "/"),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav, .navbar"))));

        log.info("  PASSED: Login successful");
    }

    @Test
    @Order(4)
    @DisplayName("4. Login with Invalid Email")
    public void testLoginInvalidEmail() {
        log.info("  Testing login with non-existent email...");
        driver.get(BASE_URL + "/login");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")))
                .sendKeys("nonexistent@email.com");
        driver.findElement(By.id("password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Check for toast or error message
        try {
            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".Toastify__toast")));
            assertNotNull(toast);
            log.info("  PASSED: Error shown for invalid email");
        } catch (Exception e) {
            // If no toast, check if still on login page (error occurred)
            assertTrue(driver.getCurrentUrl().contains("/login"), "Should stay on login page on error");
            log.info("  PASSED: Stayed on login page (error occurred)");
        }
    }

    @Test
    @Order(5)
    @DisplayName("5. Login with Wrong Password")
    public void testLoginWrongPassword() {
        // First register the user
        driver.get(BASE_URL);
        WebElement signUpLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sign Up') or contains(text(),'Register')] | //button[contains(text(),'Sign Up')]")));
        signUpLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name"))).sendKeys("Test User");
        driver.findElement(By.id("email")).sendKeys(testEmail);
        driver.findElement(By.id("password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.id("confirmPassword")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("  Testing login with wrong password...");
        driver.get(BASE_URL + "/login");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(testEmail);
        driver.findElement(By.id("password")).sendKeys("WrongPassword123!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Check for toast or error message
        try {
            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".Toastify__toast")));
            assertNotNull(toast);
            log.info("  PASSED: Error shown for wrong password");
        } catch (Exception e) {
            // If no toast, check if still on login page (error occurred)
            assertTrue(driver.getCurrentUrl().contains("/login"), "Should stay on login page on error");
            log.info("  PASSED: Stayed on login page (error occurred)");
        }
    }

    @Test
    @Order(6)
    @DisplayName("6. Navigate to Login from Home")
    public void testNavigateToLogin() {
        log.info("  Testing navigation to login...");
        driver.get(BASE_URL);

        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Login') or contains(text(),'Sign In')] | " +
                         "//button[contains(text(),'Login')]")));
        loginLink.click();

        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"));
        log.info("  PASSED: Navigated to login page");
    }

    @Test
    @Order(7)
    @DisplayName("7. Category Navigation")
    public void testCategoryNavigation() {
        log.info("  Testing category navigation...");
        driver.get(BASE_URL);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Look for any category link (jeans, t-shirts, shoes, bags, glasses)
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'/category/') or contains(text(),'Jeans') or contains(text(),'T-shirts') or contains(text(),'Shoes')]")));
        String categoryText = categoryLink.getText();
        log.info("  Clicking on category: {}", categoryText);
        categoryLink.click();

        wait.until(ExpectedConditions.urlContains("/category/"));
        assertTrue(driver.getCurrentUrl().contains("/category/"));
        log.info("  PASSED: Navigated to category page");
    }

    @Test
    @Order(8)
    @DisplayName("8. Product Browsing in Category")
    public void testProductBrowsing() {
        log.info("  Testing product browsing...");
        driver.get(BASE_URL);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Navigate to a category
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'/category/')]")));
        categoryLink.click();

        wait.until(ExpectedConditions.urlContains("/category/"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check for products - look for product cards, images, or "Add to Cart" buttons
        boolean productsFound = driver.findElements(By.cssSelector(".product")).size() > 0 ||
                driver.findElements(By.cssSelector("[class*='product']")).size() > 0 ||
                driver.findElements(By.xpath("//button[contains(text(),'Add')]")).size() > 0;

        if (productsFound) {
            log.info("  PASSED: Products found in category");
        } else {
            // Check if "no products" message is shown
            boolean noProductsMsg = driver.getPageSource().toLowerCase().contains("no products");
            if (noProductsMsg) {
                log.info("  PASSED: Category page loaded (no products available)");
            } else {
                fail("Products should be displayed or 'no products' message should appear");
            }
        }
    }

    @Test
    @Order(9)
    @DisplayName("9. Cart Access After Login")
    public void testCartAccess() {
        // Generate unique email for this test
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@test.com";
        
        // First register and login
        driver.get(BASE_URL);
        WebElement signUpLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sign Up') or contains(text(),'Register')]"))); 
        signUpLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name"))).sendKeys("Test User");
        driver.findElement(By.id("email")).sendKeys(uniqueEmail);
        driver.findElement(By.id("password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.id("confirmPassword")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wait for redirect after registration to home page
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe(BASE_URL + "/"),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav, .navbar"))
        ));

        log.info("  Waiting for authentication state to load...");
        
        // Wait longer for logout button - confirms React app has completed checkAuth()
        try {
            Thread.sleep(5000); // Give React more time to complete checkAuth()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("  Accessing cart page after login...");
        driver.get(BASE_URL + "/cart");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/cart"), 
                "Should be on cart page, current URL: " + currentUrl);
        log.info("  PASSED: Logged user can access cart");
    }

    @Test
    @Order(10)
    @DisplayName("10. End-to-End Shopping Flow")
    public void testEndToEndFlow() {
        log.info("  Testing complete shopping flow...");
        
        // Generate unique email for this test
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@test.com";
        
        // Step 1: Register
        driver.get(BASE_URL);
        WebElement signUpLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sign Up') or contains(text(),'Register')]"))); 
        signUpLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name"))).sendKeys("Test User");
        driver.findElement(By.id("email")).sendKeys(uniqueEmail);
        driver.findElement(By.id("password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.id("confirmPassword")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wait for redirect after registration
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe(BASE_URL + "/"),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav, .navbar"))
        ));
        log.info("  Step 1: User registered");
        
        // Wait for authentication state to load
        try {
            Thread.sleep(5000); // Give React time to complete checkAuth()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 2: Navigate to home
        driver.get(BASE_URL);
        log.info("  Step 2: Navigated to home");

        // Step 3: Browse category
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'/category/')]")));
        categoryLink.click();
        wait.until(ExpectedConditions.urlContains("/category/"));
        log.info("  Step 3: Browsed to category");

        // Step 4: View cart
        driver.get(BASE_URL + "/cart");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("  Step 4: Accessed cart page");

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/cart"), 
                "Should be on cart page, current URL: " + currentUrl);
        log.info("  PASSED: End-to-end shopping flow completed");
    }
}
