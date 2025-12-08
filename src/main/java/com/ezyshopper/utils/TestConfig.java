package com.ezyshopper.utils;

/**
 * Configuration class for storing test constants and URLs
 */
public class TestConfig {
    
    // Base URLs - can be overridden by system properties or environment variables
    public static final String BASE_URL = System.getProperty("baseUrl", 
        System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://13.234.238.153:5174");
    public static final String BACKEND_URL = System.getProperty("backendUrl", 
        System.getenv("BACKEND_URL") != null ? System.getenv("BACKEND_URL") : "http://13.234.238.153:3001");
    
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
