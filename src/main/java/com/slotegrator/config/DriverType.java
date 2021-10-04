package com.slotegrator.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;

public enum DriverType implements DriverSetup {


    FIREFOX {
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions options = new FirefoxOptions();
            options.merge(capabilities);
            if (capabilities.getCapability("headless").equals(true)) {
                options.setHeadless(true);
            }

            return new FirefoxDriver(options);
        }
    },
    CHROME {
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            WebDriverManager.chromedriver().setup();

            HashMap<String, Object> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", false);

            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            if (capabilities.getCapability("headless").equals(true)) {
                options.setHeadless(true);
            }
            options.addArguments("--no-default-browser-check");
            options.addArguments("start-maximized");
            options.setExperimentalOption("prefs", chromePreferences);

            return new ChromeDriver(options);
        }
    },
    IE {
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            WebDriverManager.iedriver().setup();

            InternetExplorerOptions options = new InternetExplorerOptions();
            options.merge(capabilities);
            options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

            return new InternetExplorerDriver(options);
        }
    },
    EDGE {
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            WebDriverManager.edgedriver().setup();

            EdgeOptions options = new EdgeOptions();
            options.merge(capabilities);
            return new EdgeDriver(options);
        }
    },
    SAFARI {
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            SafariOptions options = new SafariOptions();
            options.merge(capabilities);
            return new SafariDriver(options);
        }
    },
    OPERA {
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            OperaOptions options = new OperaOptions();
            options.merge(capabilities);
            return new OperaDriver(options);
        }
    }
}