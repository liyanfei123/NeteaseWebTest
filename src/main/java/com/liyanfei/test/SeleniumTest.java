package com.liyanfei.test;

import com.liyanfei.pages.HomePage;
import com.liyanfei.pages.LoginPage;
import com.liyanfei.utils.FindElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// 购物车的测试
public class SeleniumTest {
    public static void main2(String[] args) throws Exception{
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("http://you.163.com/");
        HomePage homePage = new HomePage(chromeDriver);
        LoginPage loginPage = homePage.goLoginPage();
        loginPage.switchLoginWay();
        loginPage.inputCount("15806284945", "710662952lyf");
        loginPage.clickLogin();
        if (homePage.verifyLogin("李飞飞")) {
            System.out.println("123");
        } else {
            System.out.println("456");
        }
    }

    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.get("http://you.163.com/item/detail?id=1505048&_stat_area=1&_stat_referer=search&_stat_query=%E9%BB%91%E7%8C%AA%E8%82%89%E9%A6%99%E8%82%A0&_stat_count=78&_stat_searchversion=dcn_model-2.4-1.1");
        WebElement element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "div.w-collectZone");
        element.click();
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "div.w-collectZone");
        if (!element.isSelected()) {
            System.out.println("123456");
        } else {
            System.out.println("567");
        }
//        System.out.println(chromeDriver.getWindowHandle());
//        WebElement element = FindElement.findElementByType(chromeDriver, "cssSelector",
//                "div.m-product > div.bd > h4.name > a");
//        element.click();
//
////获取当前窗口句柄
//        String win = chromeDriver.getWindowHandle();
////获取所有窗口句柄
//        Set<String> Windows = chromeDriver.getWindowHandles();
////把获取到的窗口句柄放到list中
//        List<String> allWindows = new ArrayList<String>(Windows);
//        System.out.println(allWindows);
//        chromeDriver.switchTo().window(allWindows.get(1));
//        element = FindElement.findElementByType(chromeDriver, "cssSelector",
//                "a.w-button-ghost");
//        element.click();
    }
}
