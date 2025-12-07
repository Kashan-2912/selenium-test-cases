# 🚀 Quick Start Guide - EzyShopper Selenium Tests

## ⚡ Quick Setup & Run

### Step 1: Verify Prerequisites
```powershell
# Check Java
java -version
# Should show Java 11 or higher

# Check Maven
mvn -version
# Should show Maven 3.6 or higher
```

### Step 2: Start the Application
```powershell
# Terminal 1 - Start Backend
cd backend
npm start

# Terminal 2 - Start Frontend
cd frontend
npm run dev
```

Application should be running at:
- Frontend: http://localhost:5173
- Backend: http://localhost:3000

### Step 3: Run Selenium Tests
```powershell
# Open new terminal
cd selenium-tests

# Install dependencies (first time only)
mvn clean install -DskipTests

# Run all tests
mvn test
```

---

## 📊 Test Execution Commands

### Run All 10 Tests
```powershell
mvn test
```

### Run Specific Test
```powershell
# Homepage test
mvn test -Dtest=EzyShopperTests#testHomepageLoads

# Registration test
mvn test -Dtest=EzyShopperTests#testUserRegistration

# Login test
mvn test -Dtest=EzyShopperTests#testUserLogin

# Category navigation test
mvn test -Dtest=EzyShopperTests#testCategoryNavigation

# Add to cart test
mvn test -Dtest=EzyShopperTests#testAddToCart

# Cart management test
mvn test -Dtest=EzyShopperTests#testCartManagement

# Admin dashboard test
mvn test -Dtest=EzyShopperTests#testAdminDashboard

# End-to-end test
mvn test -Dtest=EzyShopperTests#testEndToEndShopping
```

### Run in Headless Mode
1. Open `src/main/java/com/ezyshopper/utils/TestConfig.java`
2. Change `HEADLESS_MODE = false` to `HEADLESS_MODE = true`
3. Run tests: `mvn test`

---

## 🎯 10 Test Cases Overview

| # | Test Name | Description | Priority |
|---|-----------|-------------|----------|
| 1 | testHomepageLoads | Verifies homepage loads with all elements | High |
| 2 | testUserRegistration | Tests user registration flow | High |
| 3 | testUserLogin | Tests user login authentication | High |
| 4 | testUserLogout | Tests user logout functionality | Medium |
| 5 | testCategoryNavigation | Tests navigation to categories | High |
| 6 | testProductBrowsing | Tests product display in categories | High |
| 7 | testAddToCart | Tests adding products to cart | High |
| 8 | testCartManagement | Tests cart operations (update, remove) | High |
| 9 | testAdminDashboard | Tests admin dashboard access | Medium |
| 10 | testEndToEndShopping | Tests complete shopping flow | Critical |

---

## 📝 Configuration

### Update Test Credentials
Edit: `src/main/java/com/ezyshopper/utils/TestConfig.java`

```java
// Test User (create this user in your application first)
public static final String TEST_USER_EMAIL = "testuser@example.com";
public static final String TEST_USER_PASSWORD = "Test@123456";

// Admin User (create this user with admin role)
public static final String ADMIN_EMAIL = "admin@example.com";
public static final String ADMIN_PASSWORD = "Admin@123456";
```

### Change Browser
```java
// In TestConfig.java
public static final String BROWSER = "chrome"; // or "firefox"
```

### Adjust Wait Times
```java
// In TestConfig.java
public static final int IMPLICIT_WAIT = 10;  // seconds
public static final int EXPLICIT_WAIT = 15;  // seconds
```

---

## 📊 View Test Reports

### TestNG HTML Report
```powershell
# After test execution, open:
test-output/index.html
```

### Maven Surefire Reports
```powershell
# View XML reports:
target/surefire-reports/
```

### Console Output
- Real-time test execution logs are shown in console
- Each test prints detailed step information

---

## ✅ Expected Output

### Successful Test Run:
```
=================================================
Starting EzyShopper Test Suite
=================================================

--- Test 1: Homepage Load Verification ---
✓ Homepage loaded successfully
✓ Page title: Explore Our Categories
✓ Navigation bar is present

--- Test 2: User Registration ---
✓ Registration form submitted
✓ Test user email: testuser1701234567890@example.com
✓ User logged in: true

--- Test 3: User Login ---
✓ Login form submitted
✓ Cart link visible: true
✓ Logout button visible: true

[... more test output ...]

=================================================
EzyShopper Test Suite Completed
=================================================

Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
```

---

## 🐛 Troubleshooting

### Problem: "Connection refused" error
**Solution**: Ensure frontend and backend are running
```powershell
# Check if apps are running:
# Frontend: http://localhost:5173
# Backend: http://localhost:3000
```

### Problem: "Element not found" error
**Solution**: Increase wait times in TestConfig.java or check if UI has changed

### Problem: ChromeDriver issues
**Solution**: WebDriverManager handles this automatically. If issues persist:
```powershell
# Clear WebDriverManager cache
rd /s /q %USERPROFILE%\.m2\repository\webdriver
mvn clean install -DskipTests
```

### Problem: "User not found" error
**Solution**: Create test users in application:
1. Start the application
2. Manually register a user with test credentials
3. Or modify tests to use dynamic user creation

### Problem: Tests timeout
**Solution**: 
- Ensure application is fully loaded before running tests
- Increase timeout values in TestConfig.java
- Check browser performance

---

## 🔧 IDE Setup (Optional)

### IntelliJ IDEA
1. Open `selenium-tests` folder as project
2. Maven will auto-import dependencies
3. Right-click on `EzyShopperTests.java` → Run

### Eclipse
1. Import as Maven project
2. Right-click on project → Maven → Update Project
3. Right-click on `EzyShopperTests.java` → Run As → TestNG Test

### VS Code
1. Install "Extension Pack for Java"
2. Install "Test Runner for Java"
3. Open `selenium-tests` folder
4. Click on "Run Test" above test methods

---

## 📁 Project Files

```
selenium-tests/
├── pom.xml                    # Maven configuration
├── testng.xml                 # TestNG suite
├── README.md                  # Detailed documentation
├── QUICK_START.md            # This file
└── src/main/java/com/ezyshopper/
    ├── tests/
    │   └── EzyShopperTests.java    # All 10 test cases
    ├── pages/                       # Page Object Model
    │   ├── HomePage.java
    │   ├── NavigationBar.java
    │   ├── SignUpPage.java
    │   ├── LoginPage.java
    │   ├── CategoryPage.java
    │   ├── CartPage.java
    │   └── AdminPage.java
    └── utils/
        ├── TestConfig.java          # Configuration
        └── SeleniumUtils.java       # Utilities
```

---

## 🎓 Key Points

✅ **Tests use Page Object Model** - Easy to maintain
✅ **Automatic driver management** - No manual setup needed
✅ **Explicit waits** - Proper synchronization
✅ **Independent tests** - Can run in any order
✅ **Detailed logging** - Easy debugging
✅ **Cross-browser support** - Chrome, Firefox, Edge

---

## 🚀 Next Steps

1. ✅ Run all tests to ensure setup is correct
2. ✅ Review test reports in `test-output/index.html`
3. ✅ Customize test data in TestConfig.java
4. ✅ Add more test cases as needed
5. ✅ Integrate with CI/CD pipeline

---

## 📞 Need Help?

- Check `README.md` for detailed documentation
- Review `PROJECT_ANALYSIS_AND_TESTS.md` for project details
- Check console output for error messages
- Verify application is running and accessible

---

**Happy Testing! 🎉**
