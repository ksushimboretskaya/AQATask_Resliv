package configuration;

import webdriver.DriverFactory;

public class BrowserConfig {
    private static final DriverFactory.BrowserType type = DriverFactory.BrowserType.CHROME;

    public static DriverFactory.BrowserType getType() {
        return type;
    }
}
