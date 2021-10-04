package com.slotegrator.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

public class DriverFactory {

    private WebDriver webDriver;
    private final DriverType selectedDriverType;
    private String currentTestName;
    private final Properties properties;

    public DriverFactory() throws IOException {
        DriverType driverType = DriverType.CHROME;

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + ".properties";

        properties = new Properties();
        try {
            properties.load(new FileInputStream(appConfigPath));
        } catch (FileNotFoundException e) {
            System.out.println(appConfigPath + " not found, using defaults.");
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("default.properties");
            properties.load(inputStream);
        }

        Properties systemProperties = System.getProperties();

        // override with system properties
        properties.putAll(systemProperties);

        String browser = properties.getProperty("browser", driverType.toString()).toUpperCase();

        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    public void setTestName(String testname) {
        currentTestName = testname;
    }

    public WebDriver getDriver() throws MalformedURLException {
        if (null == webDriver) {
            instantiateWebDriver(selectedDriverType);
        }

        return webDriver;
    }

    public void quitDriver() {
        if (null != webDriver) {
            webDriver.quit();
            webDriver = null;
        }
    }

    private void instantiateWebDriver(DriverType driverType) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        String headlessPropertyValue = properties.getOrDefault("headless", "false").toString();
        boolean headless = headlessPropertyValue.equalsIgnoreCase("true");
        desiredCapabilities.setCapability("headless", headless);
        System.out.println("Selected Browser: " + selectedDriverType + ", headless mode? " + headless);
        webDriver = driverType.getWebDriverObject(desiredCapabilities);
    }
}