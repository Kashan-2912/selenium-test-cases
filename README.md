# EzyShopper - Selenium Test Suite

## Overview
This directory contains comprehensive Selenium test automation for the EzyShopper e-commerce application. The test suite includes 10 test cases covering critical user flows and functionality.

## Test Cases

### 1. **Homepage Load Verification**
- Verifies the homepage loads correctly
- Checks page title and navigation elements
- Validates all category sections are visible

### 2. **User Registration Flow**
- Tests complete user registration process
- Validates form submission
- Verifies successful account creation

### 3. **User Login Flow**
- Tests user authentication with valid credentials
- Verifies login success indicators
- Validates redirect to homepage

### 4. **User Logout Flow**
- Tests logout functionality
- Verifies session is cleared
- Validates navigation elements update correctly

### 5. **Category Navigation**
- Tests navigation to different product categories
- Validates URL routing
- Verifies category page loads with correct content

### 6. **Product Browsing**
- Tests product display in category pages
- Validates product information (name, price, image)
- Verifies "Add to Cart" buttons are present

### 7. **Add Product to Cart**
- Tests adding products to shopping cart
- Validates cart count updates
- Verifies success notifications

### 8. **View and Manage Cart**
- Tests cart page functionality
- Validates cart item display
- Tests update quantity and remove item operations

### 9. **Admin Dashboard Access**
- Tests admin authentication and authorization
- Validates admin dashboard access
- Tests navigation between admin tabs (Create Product, Products, Analytics)

### 10. **End-to-End Shopping Flow**
- Complete user journey from browse to checkout
- Tests product selection, cart operations
- Validates checkout process initiation

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

### Run All Tests
```bash
mvn test
```

### Run Specific Test
```bash
mvn test -Dtest=EzyShopperTests#testHomepageLoads
```

### Run with TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run in Headless Mode
Update `TestConfig.HEADLESS_MODE = true` in TestConfig.java

### Run with Different Browser
Update `TestConfig.BROWSER = "firefox"` in TestConfig.java

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
5. **Screenshot Capture**: Captures screenshots on failure
6. **Descriptive Test Names**: Clear test method names and descriptions
7. **Test Priorities**: Tests run in logical order
8. **Independent Tests**: Each test can run independently

## Troubleshooting

### Common Issues:

1. **Application not running**
   - Ensure frontend and backend are running
   - Check URLs in TestConfig match your setup

2. **WebDriver issues**
   - WebDriverManager should auto-download drivers
   - Check internet connection
   - Update WebDriverManager version if needed

3. **Element not found**
   - Check if application UI has changed
   - Update locators in page objects
   - Increase wait times if needed

4. **Test credentials**
   - Create test user in application first
   - Update credentials in TestConfig
   - Or modify tests to create users dynamically

## Continuous Integration

To integrate with Jenkins or other CI/CD tools:

```bash
# Jenkins pipeline example
mvn clean test -DsuiteXmlFile=testng.xml
```

## Future Enhancements

- [ ] Add screenshot capture on test failure
- [ ] Integrate ExtentReports for better reporting
- [ ] Add data-driven testing with external data sources
- [ ] Implement parallel test execution
- [ ] Add API testing for backend validation
- [ ] Integrate with BrowserStack/Sauce Labs for cross-browser testing
- [ ] Add performance testing metrics
- [ ] Implement CI/CD pipeline integration

## Contributing

When adding new test cases:
1. Follow the existing Page Object Model structure
2. Add page objects to `pages` package
3. Add test methods to appropriate test class
4. Update this README with new test case description
5. Ensure tests are independent and can run in any order

## Notes

- Tests require the application to be running locally
- Some tests may require specific data (products, users) to exist
- Admin tests require admin user credentials
- Cart and checkout tests require products to be available
- Consider using test data setup scripts for consistent test execution

## Contact

For questions or issues with the test suite, please contact the development team.
