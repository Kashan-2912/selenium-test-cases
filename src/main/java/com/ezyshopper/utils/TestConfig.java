package com.ezyshopper.utils;

/**
 * Configuration class for storing test constants and URLs
 */
public class TestConfig {
    
    // Base URLs
    public static final String BASE_URL = "http://localhost:5173";
    public static final String BACKEND_URL = "http://localhost:3000";
    
    // Test User Credentials
    public static final String TEST_USER_EMAIL = "testuser@example.com";
    public static final String TEST_USER_PASSWORD = "Test@123456";
    public static final String TEST_USER_NAME = "Test User";
    
    // Admin Credentials
    public static final String ADMIN_EMAIL = "admin@example.com";
    public static final String ADMIN_PASSWORD = "Admin@123456";
    
    // Timeouts
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 15;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    
    // Browser Configuration
    public static final String BROWSER = "chrome"; // chrome, firefox, edge
    public static final boolean HEADLESS_MODE = false;
    
    // Screenshot Directory
    public static final String SCREENSHOT_DIR = "test-output/screenshots/";
    
    // Report Directory
    public static final String REPORT_DIR = "test-output/reports/";
}
