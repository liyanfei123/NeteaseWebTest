package com.liyanfei.base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class SeleniumBase {
    public static Logger logger = Logger.getLogger(SeleniumBase.class.getName());
    public static WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        PropertyConfigurator.configure("config/log4j.properties");
        logger.info("----------测试用例开始执行----------");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(Settings.testUrl);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
