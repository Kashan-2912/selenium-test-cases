# 📋 EzyShopper - Detailed Test Case Documentation

## 🎯 Test Case Specifications

---

## Test Case 1: Homepage Load Verification

**Test ID:** TC_001  
**Test Name:** testHomepageLoads  
**Priority:** High  
**Category:** Smoke Test  
**Estimated Time:** 5 seconds

### Objective
Verify that the EzyShopper homepage loads successfully with all required elements displayed correctly.

### Preconditions
- Application is running at http://localhost:5173
- Browser is supported (Chrome/Firefox/Edge)

### Test Data
- URL: http://localhost:5173

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Navigate to base URL | Homepage loads successfully |
| 2 | Verify page title | Title contains "Explore Our Categories" |
| 3 | Check navigation bar | Sign Up and Login buttons are visible |
| 4 | Verify category sections | All 7 categories are displayed |

### Expected Results
✅ Homepage loads without errors  
✅ Page title is displayed correctly  
✅ Navigation bar contains all required elements  
✅ Category sections are visible  

### Actual Results
*(To be filled during test execution)*

---

## Test Case 2: User Registration Flow

**Test ID:** TC_002  
**Test Name:** testUserRegistration  
**Priority:** High  
**Category:** Functional Test  
**Estimated Time:** 8 seconds

### Objective
Verify that users can successfully register for a new account and are automatically logged in.

### Preconditions
- Homepage is accessible
- Database is running and accessible

### Test Data
- Name: "Test User"
- Email: "testuser{timestamp}@example.com" (dynamic)
- Password: "Test@123456"
- Confirm Password: "Test@123456"

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click "Sign Up" button in navigation | Redirected to /signup page |
| 2 | Verify signup page loads | Page title shows "Create your account" |
| 3 | Enter full name in Name field | Name is entered |
| 4 | Enter email in Email field | Email is entered |
| 5 | Enter password in Password field | Password is masked |
| 6 | Enter password in Confirm Password field | Password is masked |
| 7 | Click "Sign Up" button | Form is submitted |
| 8 | Wait for registration to complete | User is redirected to homepage |
| 9 | Verify logout button is visible | User is logged in |

### Expected Results
✅ User account is created successfully  
✅ User is automatically logged in  
✅ Redirected to homepage after registration  
✅ Logout button is visible in navigation  

### Validation Points
- Email must be unique
- Password must meet security requirements (6+ characters)
- Passwords must match

---

## Test Case 3: User Login Flow

**Test ID:** TC_003  
**Test Name:** testUserLogin  
**Priority:** High  
**Category:** Functional Test  
**Estimated Time:** 6 seconds

### Objective
Verify that registered users can successfully login with valid credentials.

### Preconditions
- User account exists in database
- Homepage is accessible

### Test Data
- Email: "testuser@example.com"
- Password: "Test@123456"

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click "Login" button in navigation | Redirected to /login page |
| 2 | Verify login page loads | Page title shows "Login to your account" |
| 3 | Enter email in Email field | Email is entered |
| 4 | Enter password in Password field | Password is masked |
| 5 | Click "Login" button | Form is submitted |
| 6 | Wait for authentication | JWT token is received |
| 7 | Verify redirect to homepage | URL is "/" |
| 8 | Verify cart link is visible | Cart link appears |
| 9 | Verify logout button is visible | Logout button appears |

### Expected Results
✅ User is authenticated successfully  
✅ JWT token is stored  
✅ Redirected to homepage  
✅ Cart link is visible  
✅ Logout button is visible  

### Error Scenarios
- Invalid email → Error message displayed
- Wrong password → Error message displayed
- Empty fields → Validation error

---

## Test Case 4: User Logout Flow

**Test ID:** TC_004  
**Test Name:** testUserLogout  
**Priority:** Medium  
**Category:** Functional Test  
**Estimated Time:** 7 seconds

### Objective
Verify that logged-in users can successfully logout and session is cleared.

### Preconditions
- User is logged in
- On any page of the application

### Test Data
- Logged-in user session

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Verify user is logged in | Logout button is visible |
| 2 | Click "Logout" button | Logout request is sent |
| 3 | Wait for logout to complete | Session is cleared |
| 4 | Verify login button appears | Login button is visible |
| 5 | Verify signup button appears | Signup button is visible |
| 6 | Verify cart link is removed | Cart link is not visible |

### Expected Results
✅ User session is terminated  
✅ JWT token is cleared  
✅ Login/Signup buttons reappear  
✅ Cart link is removed  
✅ Protected routes are inaccessible  

---

## Test Case 5: Category Navigation

**Test ID:** TC_005  
**Test Name:** testCategoryNavigation  
**Priority:** High  
**Category:** Navigation Test  
**Estimated Time:** 10 seconds

### Objective
Verify that users can navigate to different product categories from the homepage.

### Preconditions
- Homepage is loaded
- Categories have products (or handle empty state)

### Test Data
- Categories to test: Jeans, T-shirts, Shoes

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | From homepage, click "Jeans" category | Navigate to /jeans |
| 2 | Verify URL | URL contains "/jeans" |
| 3 | Verify category page loads | Page title shows "Jeans" |
| 4 | Navigate back to home | Click Home link |
| 5 | Click "T-shirts" category | Navigate to /t-shirts |
| 6 | Verify URL | URL contains "/t-shirts" |
| 7 | Navigate back to home | Click Home link |
| 8 | Click "Shoes" category | Navigate to /shoes |
| 9 | Verify URL | URL contains "/shoes" |

### Expected Results
✅ All category links are functional  
✅ URL routing works correctly  
✅ Category pages load properly  
✅ Category titles match selected category  
✅ Navigation between categories works  

### Categories Tested
- ✅ Jeans
- ✅ T-shirts
- ✅ Shoes
- ⏸️ Glasses (optional)
- ⏸️ Jackets (optional)
- ⏸️ Suits (optional)
- ⏸️ Bags (optional)

---

## Test Case 6: Product Browsing

**Test ID:** TC_006  
**Test Name:** testProductBrowsing  
**Priority:** High  
**Category:** Functional Test  
**Estimated Time:** 6 seconds

### Objective
Verify that products are displayed correctly in category pages with all required information.

### Preconditions
- Category page is accessible
- Products exist in the category (or handle empty state)

### Test Data
- Category: Jeans

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Navigate to Jeans category | Category page loads |
| 2 | Count number of products | Product count >= 0 |
| 3 | If products exist, verify first product | Product card is displayed |
| 4 | Verify product name | Name is not empty |
| 5 | Verify product price | Price is displayed (format: $XX.XX) |
| 6 | Verify product image | Image is loaded |
| 7 | Verify "Add to Cart" button | Button is present |
| 8 | If no products, verify message | "No products found" is shown |

### Expected Results
✅ Products are displayed in grid layout  
✅ Each product card shows: name, price, image  
✅ "Add to Cart" button is present on each card  
✅ Product information is complete  
✅ Empty state is handled gracefully  

### Product Card Elements
- Product image (top)
- Product name (text-xl font-semibold)
- Product price ($XX.XX format)
- Add to Cart button (emerald color)

---

## Test Case 7: Add Product to Cart

**Test ID:** TC_007  
**Test Name:** testAddToCart  
**Priority:** High  
**Category:** Functional Test  
**Estimated Time:** 10 seconds

### Objective
Verify that logged-in users can successfully add products to their shopping cart.

### Preconditions
- User is logged in
- Products exist in a category
- User has access to cart

### Test Data
- Logged-in user
- Category: Jeans
- Product: First available product

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Login as test user | User is authenticated |
| 2 | Navigate to homepage | Homepage loads |
| 3 | Click on Jeans category | Category page loads |
| 4 | Verify products are available | Product count > 0 |
| 5 | Click "Add to Cart" on first product | Button is clicked |
| 6 | Wait for cart to update | API request completes |
| 7 | Verify cart count badge appears | Badge is visible in navbar |
| 8 | Verify cart count increases | Count shows "1" or incremented |
| 9 | Check for success notification | Toast message appears (optional) |

### Expected Results
✅ Product is added to cart successfully  
✅ Cart count badge appears in navigation  
✅ Cart count reflects correct number  
✅ Success notification is shown  
✅ Product remains in cart after page refresh  

### Error Scenarios
- User not logged in → Redirect to login or show error
- Product out of stock → Error message
- Network error → Error handling

---

## Test Case 8: View and Manage Cart

**Test ID:** TC_008  
**Test Name:** testCartManagement  
**Priority:** High  
**Category:** Functional Test  
**Estimated Time:** 8 seconds

### Objective
Verify that users can view their cart and perform operations like update quantity and remove items.

### Preconditions
- User is logged in
- User has items in cart (or can add items)

### Test Data
- Logged-in user with cart items

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Login as test user | User is authenticated |
| 2 | Click on Cart link in navigation | Navigate to /cart |
| 3 | Verify cart page loads | URL is "/cart" |
| 4 | Check if cart has items | Count cart items |
| 5 | If cart has items, verify display | Items are shown with details |
| 6 | Verify subtotal | Subtotal amount is calculated |
| 7 | Verify total | Total amount is displayed |
| 8 | Test remove item (if items exist) | Click remove button |
| 9 | Wait for cart to update | Item is removed |
| 10 | If cart is empty, verify message | "Cart is empty" is shown |

### Expected Results
✅ Cart page loads successfully  
✅ Cart items are displayed with details  
✅ Subtotal is calculated correctly  
✅ Total includes all charges  
✅ Remove item works correctly  
✅ Update quantity works correctly  
✅ Empty cart state is handled  

### Cart Item Details
- Product image
- Product name
- Product price
- Quantity controls (+/-)
- Remove button
- Item subtotal

### Cart Summary
- Subtotal
- Discount (if coupon applied)
- Total
- Proceed to Checkout button

---

## Test Case 9: Admin Dashboard Access

**Test ID:** TC_009  
**Test Name:** testAdminDashboard  
**Priority:** Medium  
**Category:** Security & Functional Test  
**Estimated Time:** 9 seconds

### Objective
Verify that admin users can access the admin dashboard and regular users cannot.

### Preconditions
- Admin user exists in database
- Admin user has role="admin"

### Test Data
- Admin Email: "admin@example.com"
- Admin Password: "Admin@123456"

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Login with admin credentials | Admin is authenticated |
| 2 | Verify dashboard link is visible | Dashboard link appears in navbar |
| 3 | Click Dashboard link | Navigate to /secret-dashboard |
| 4 | Verify URL | URL is "/secret-dashboard" |
| 5 | Verify admin page loads | Page title shows "Admin Dashboard" |
| 6 | Verify tabs are visible | Create Product, Products, Analytics tabs |
| 7 | Click Products tab | Products list is displayed |
| 8 | Click Analytics tab | Analytics data is shown |
| 9 | Click Create Product tab | Product creation form appears |

### Expected Results
✅ Admin user can access dashboard  
✅ Dashboard link is visible only for admin  
✅ All admin tabs are functional  
✅ Role-based access control works  
✅ Regular users cannot access /secret-dashboard  

### Admin Dashboard Tabs
1. **Create Product**: Form to add new products
2. **Products**: List of all products with edit/delete
3. **Analytics**: Sales data, user stats, product stats

### Security Check
- Non-admin user redirected to login if accessing /secret-dashboard

---

## Test Case 10: End-to-End Shopping Flow

**Test ID:** TC_010  
**Test Name:** testEndToEndShopping  
**Priority:** Critical  
**Category:** Integration Test  
**Estimated Time:** 15 seconds

### Objective
Verify complete shopping journey from browsing products to cart checkout.

### Preconditions
- Application is fully functional
- Products exist in database
- Payment gateway is configured

### Test Data
- Test user credentials
- Category: Shoes
- Product: First available product

### Test Steps
| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Navigate to homepage | Homepage loads successfully |
| 2 | Verify homepage elements | Title and categories visible |
| 3 | Click Login button | Navigate to login page |
| 4 | Login with test credentials | User is authenticated |
| 5 | Verify login success | Redirected to homepage |
| 6 | Click on Shoes category | Navigate to category page |
| 7 | Verify category page loads | Products are displayed |
| 8 | Get first product name | Product name stored |
| 9 | Click "Add to Cart" on product | Product added to cart |
| 10 | Verify cart count updates | Badge shows "1" |
| 11 | Click Cart link | Navigate to cart page |
| 12 | Verify cart page loads | URL is "/cart" |
| 13 | Verify product in cart | Added product is displayed |
| 14 | Verify cart has items | Cart count > 0 |
| 15 | Verify checkout button | "Proceed to Checkout" is visible |

### Expected Results
✅ Complete flow works end-to-end  
✅ User can browse and select products  
✅ Add to cart functionality works  
✅ Cart displays correct items  
✅ User can proceed to checkout  
✅ No errors in the flow  

### Complete User Journey
```
Homepage → Login → Category → Product Selection → 
Add to Cart → View Cart → Checkout
```

### Success Criteria
- All pages load correctly
- Navigation works smoothly
- Data persists across pages
- Cart maintains state
- User remains logged in throughout

---

## 📊 Test Execution Summary

### Test Statistics
- **Total Test Cases:** 10
- **Priority Distribution:**
  - High: 7 tests
  - Medium: 2 tests
  - Critical: 1 test

### Test Categories
- Smoke Tests: 1
- Functional Tests: 7
- Navigation Tests: 1
- Integration Tests: 1
- Security Tests: 1

### Estimated Execution Time
- Individual test: 5-15 seconds
- Complete suite: ~90 seconds
- With setup/teardown: ~2 minutes

---

## 🎯 Test Coverage Matrix

| Feature | TC_001 | TC_002 | TC_003 | TC_004 | TC_005 | TC_006 | TC_007 | TC_008 | TC_009 | TC_010 |
|---------|--------|--------|--------|--------|--------|--------|--------|--------|--------|--------|
| Homepage | ✅ | | | | | | | | | ✅ |
| Registration | | ✅ | | | | | | | | |
| Login | | | ✅ | ✅ | | | ✅ | ✅ | ✅ | ✅ |
| Logout | | | | ✅ | | | | | | |
| Navigation | ✅ | | | | ✅ | | | | | ✅ |
| Categories | | | | | ✅ | ✅ | ✅ | | | ✅ |
| Products | | | | | | ✅ | ✅ | | | ✅ |
| Cart | | | | | | | ✅ | ✅ | | ✅ |
| Admin | | | | | | | | | ✅ | |
| Checkout | | | | | | | | | | ✅ |

---

## 📝 Test Execution Notes

### Environment Requirements
- Browser: Chrome 120+ / Firefox 120+ / Edge 120+
- Java: 11 or higher
- Maven: 3.6+
- Node.js: 16+ (for running application)
- MongoDB: 6.0+ (for database)

### Before Running Tests
1. Ensure backend is running (port 3000)
2. Ensure frontend is running (port 5173)
3. Database is accessible
4. Create test users if needed
5. Verify test data exists

### After Test Execution
1. Review test reports in `test-output/`
2. Check for any failed assertions
3. Review screenshots if enabled
4. Validate test coverage
5. Update test cases if needed

---

**Document Version:** 1.0  
**Last Updated:** December 7, 2025  
**Author:** Test Automation Team  
**Status:** Active
