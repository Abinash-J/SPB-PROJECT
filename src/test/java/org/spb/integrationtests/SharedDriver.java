package org.spb.integrationtests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SharedDriver {
    
    public SharedDriver() {
        if (DriverFactory.getDriver() == null) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            DriverFactory.addDriver(driver);
        }
    }
}
