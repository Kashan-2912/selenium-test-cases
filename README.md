# EzyShopper - Selenium Test Suite

## Overview
This directory contains comprehensive Selenium test automation for the EzyShopper e-commerce application. The test suite includes 10 sequential test cases that cover the complete user journey from registration to shopping cart management.

**Test Execution Strategy:**
- Test 1 (User Registration): Opens and closes its own browser session
- Tests 2-10: Run in a single continuous browser session with a logged-in user
- Tests must run in order due to dependencies (e.g., login is required for cart operations)
- Each test builds upon the state created by previous tests

## Test Cases

The test suite contains 10 comprehensive test cases that run sequentially to validate the complete e-commerce functionality:

### 1. **User Registration Flow**
- Opens browser and navigates to sign-up page
- Fills registration form with unique email
- Submits form and verifies successful registration
- Validates user is logged in after registration
- Browser closes after this test completes

### 2. **User Login Flow**
- Opens new browser session (stays open for remaining tests)
- Creates a test user account via signup
- Logs out and then tests login functionality
- Validates login with the created credentials
- Verifies cart link and logout button are visible
- User remains logged in for all subsequent tests

### 3. **Homepage Verification**
- Verifies homepage loads correctly for logged-in user
- Checks that user session persists
- Validates product categories are visible
- Confirms navigation elements are present

### 4. **Category Navigation**
- Tests navigation to multiple product categories (Jeans, T-shirts, Shoes)
- Validates URL routing for each category
- Verifies each category page loads correctly
- Tests navigation back to homepage between categories

### 5. **Product Browsing**
- Navigates to Jeans category
- Verifies products are displayed
- Validates product information (name, price)
- Counts and displays number of available products
- Handles cases where no products are available

### 6. **Verify Multiple Category Pages**
- Tests navigation to Glasses category
- Tests navigation to Bags category
- Validates URL contains correct category path for each
- Ensures category pages load properly

### 7. **Add Product to Cart**
- Navigates to Jeans category
- Adds first available product to cart
- Verifies cart count badge appears in navigation
- Validates cart count increases after adding product

### 8. **Add Another Product to Cart**
- Navigates to T-shirts category
- Adds another product to cart
- Verifies cart count updates with multiple items
- Tests cart can handle multiple products from different categories

### 9. **View Cart with Multiple Items**
- Navigates to cart page
- Verifies cart page loads correctly
- Validates cart displays multiple items from previous tests
- Confirms at least 2 items are present in cart

### 10. **End-to-End Shopping Flow**
- Validates complete shopping journey
- Navigates through homepage → category → cart
- Verifies user remains logged in throughout
- Tests browsing Shoes category
- Views cart to verify items from previous tests
- Confirms end-to-end flow works seamlessly

## Project Structure

```
selenium-tests/
├── pom.xml                                 # Maven configuration
├── testng.xml                             # TestNG suite configuration
├── README.md                              # This file
└── src/
    └── main/
        └── java/
            └── com/
                └── ezyshopper/
                    ├── tests/
                    │   └── EzyShopperTests.java      # Main test class
                    ├── pages/
                    │   ├── HomePage.java             # Homepage page object
                    │   ├── NavigationBar.java        # Navigation bar page object
                    │   ├── SignUpPage.java           # Sign up page object
                    │   ├── LoginPage.java            # Login page object
                    │   ├── CategoryPage.java         # Category page object
                    │   ├── CartPage.java             # Cart page object
                    │   └── AdminPage.java            # Admin dashboard page object
                    └── utils/
                        ├── TestConfig.java           # Test configuration
                        └── SeleniumUtils.java        # Utility methods
```

## Technologies Used

- **Java 11+**: Programming language
- **Selenium WebDriver 4.16.1**: Browser automation
- **TestNG 7.8.0**: Testing framework
- **Maven**: Build and dependency management
- **WebDriverManager 5.6.3**: Automatic driver management
- **Page Object Model (POM)**: Design pattern for maintainable tests

## Prerequisites

1. **Java Development Kit (JDK) 11 or higher**
   ```bash
   java -version
   ```

2. **Maven**
   ```bash
   mvn -version
   ```

3. **Chrome Browser** (or Firefox)
   - ChromeDriver will be automatically managed by WebDriverManager

4. **EzyShopper Application Running**
   - Frontend: http://localhost:5173
   - Backend: http://localhost:3000
   - Ensure the application is running before executing tests

## Installation

1. Navigate to the selenium-tests directory:
   ```bash
   cd selenium-tests
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

## Configuration

Update test configuration in `src/main/java/com/ezyshopper/utils/TestConfig.java`:

```java
// Application URLs
public static final String BASE_URL = "http://localhost:5173";
public static final String BACKEND_URL = "http://localhost:3000";

// Test credentials
public static final String TEST_USER_EMAIL = "testuser@example.com";
public static final String TEST_USER_PASSWORD = "Test@123456";
public static final String ADMIN_EMAIL = "admin@example.com";
public static final String ADMIN_PASSWORD = "Admin@123456";

// Browser configuration
public static final String BROWSER = "chrome"; // chrome, firefox, edge
public static final boolean HEADLESS_MODE = false;
```

## Running Tests

### Run All Tests (Recommended)
```bash
mvn test
```
**Note:** It's highly recommended to run all tests together in sequence as they have dependencies.

### Run Specific Test (Advanced)
```bash
# Run individual test (may fail if dependencies not met)
mvn test -Dtest=EzyShopperTests#testUserRegistration
mvn test -Dtest=EzyShopperTests#testUserLogin
```
**Warning:** Running individual tests 3-10 will likely fail as they depend on the logged-in state from Test 2.

### Run with TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run in Headless Mode
Update `TestConfig.HEADLESS_MODE = true` in TestConfig.java before running

### Run with Different Browser
Update `TestConfig.BROWSER` in TestConfig.java:
- Supported values: `"chrome"`, `"firefox"`, `"edge"`

## Test Execution Notes

1. **Sequential Execution**: Tests are designed to run in order (priority 1-10)
2. **Browser Sessions**:
   - Test 1: Opens and closes its own browser
   - Tests 2-10: Share a single browser session
3. **State Management**:
   - Test 2 creates a user and logs in
   - Tests 7-8 add products to cart
   - Tests 9-10 verify the accumulated cart state
4. **Execution Time**: Full suite typically takes 2-3 minutes to complete

## Test Reports

After test execution, reports are generated in:
- **Surefire Reports**: `target/surefire-reports/`
- **TestNG Reports**: `test-output/`

To view TestNG HTML report:
```bash
# Open in browser
test-output/index.html
```

## Page Object Model (POM)

The test suite uses Page Object Model design pattern for better maintainability:

- **Page Classes**: Located in `com.ezyshopper.pages` package
- **Test Classes**: Located in `com.ezyshopper.tests` package
- **Utilities**: Located in `com.ezyshopper.utils` package

### Example Page Object:
```java
public class HomePage {
    private WebDriver driver;
    private By categoryJeans = By.xpath("//a[@href='/jeans']");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickCategoryJeans() {
        SeleniumUtils.safeClick(driver, categoryJeans);
    }
}
```

## Best Practices Implemented

1. **Page Object Model**: Separates page structure from test logic
2. **Explicit Waits**: Uses WebDriverWait for better synchronization
3. **Utility Methods**: Reusable methods for common operations
4. **Configuration Management**: Centralized configuration in TestConfig
5. **Descriptive Test Names**: Clear test method names and descriptions with @Test annotations
6. **Test Priorities**: Tests run in logical order (@Test priority attribute)
7. **Sequential Test Flow**: Tests 2-10 maintain browser session for realistic user journey
8. **Unique Test Data**: Generates unique emails using timestamps to avoid conflicts
9. **Defensive Testing**: Handles cases where products may not be available
10. **Detailed Console Logging**: Provides step-by-step execution feedback

## Test Flow and Dependencies

**Important:** Tests are designed to run sequentially due to the following dependencies:

- **Test 1** (Registration): Independent - runs in its own browser session
- **Test 2** (Login): Creates user account and establishes logged-in session for all remaining tests
- **Tests 3-6**: Verify navigation and browsing (require logged-in state from Test 2)
- **Tests 7-8**: Add products to cart (require logged-in state and build cart state)
- **Tests 9-10**: Validate cart and end-to-end flow (require cart items from Tests 7-8)

Running tests out of order or individually may cause failures due to missing prerequisites.

## Troubleshooting

### Common Issues:

1. **Application not running**
   - Ensure frontend is running at http://localhost:5173
   - Ensure backend is running at http://localhost:3000
   - Check URLs in TestConfig match your setup

2. **Tests 3-10 fail but Test 1-2 pass**
   - This is normal if running tests individually
   - Tests 3-10 require logged-in state from Test 2
   - Solution: Run full test suite with `mvn test`

3. **WebDriver issues**
   - WebDriverManager should auto-download drivers
   - Check internet connection for first-time driver download
   - Update WebDriverManager version if needed
   - Ensure Chrome/Firefox browser is installed

4. **Element not found errors**
   - Application may still be loading - tests include sleep statements
   - Check if application UI has changed
   - Update locators in page objects if needed
   - Verify application is running on correct ports

5. **"No products available" warnings**
   - Tests handle this gracefully and won't fail
   - Ensure database has products in categories (Jeans, T-shirts, Shoes, Glasses, Bags)
   - Check backend is properly seeded with test data

6. **Cart tests fail (Tests 7-9)**
   - Ensure products exist in Jeans and T-shirts categories
   - Verify user can add products to cart manually in the application
   - Check browser console for JavaScript errors

7. **Test timeout errors**
   - Increase wait times in test methods if application is slow
   - Check system performance and available memory
   - Reduce number of browser tabs/applications running

## Continuous Integration

To integrate with Jenkins or other CI/CD tools:

```bash
# Jenkins pipeline example
mvn clean test -DsuiteXmlFile=testng.xml
```

## Future Enhancements

- [ ] Add screenshot capture on test failure
- [ ] Implement truly independent tests (remove dependencies)
- [ ] Add checkout and payment flow tests
- [ ] Add admin functionality tests (product creation, management)
- [ ] Integrate ExtentReports for better HTML reporting
- [ ] Add data-driven testing with external data sources (Excel/CSV)
- [ ] Implement parallel test execution for faster runs
- [ ] Add API testing for backend validation
- [ ] Integrate with BrowserStack/Sauce Labs for cross-browser testing
- [ ] Add performance testing metrics
- [ ] Implement CI/CD pipeline integration (GitHub Actions/Jenkins)
- [ ] Add test data setup/teardown scripts
- [ ] Implement soft assertions for better error reporting
- [ ] Add mobile responsive testing
- [ ] Create test data factory for consistent test scenarios

## Contributing

When adding new test cases:
1. Follow the existing Page Object Model structure
2. Add page objects to `pages` package
3. Add test methods to appropriate test class
4. Update this README with new test case description
5. Ensure tests are independent and can run in any order

## Notes

- Tests require the application to be running locally (frontend on port 5173, backend on port 3000)
- **Tests must run in sequential order** - they are NOT independent
- Test 1 runs in its own browser session, Tests 2-10 share a browser session
- Tests 2-10 depend on logged-in user state created in Test 2
- Tests 7-8 add products to cart, Tests 9-10 verify those cart items
- Products must exist in database for tests to fully pass (Jeans, T-shirts, Shoes, Glasses, Bags categories)
- Unique email addresses are generated using timestamps to avoid conflicts
- Tests include defensive checks and will log warnings instead of failing when products are unavailable
- Some tests use Thread.sleep() for synchronization - not ideal but works for demo purposes
- Full test suite execution takes approximately 2-3 minutes

## Contact

For questions or issues with the test suite, please contact the development team.
