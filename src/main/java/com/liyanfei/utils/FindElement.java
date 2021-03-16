package com.liyanfei.utils;

import com.liyanfei.base.Settings;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class FindElement {
    public static Logger logger = Logger.getLogger(FindElement.class.getName());

    /**
     * 根据给定的查找方式查找元素控件（仅查找一个时），同时使用显式等待
     * @param driver 驱动
     * @param locatedType 定位类型
     * @param locatedInfo 定位信息
     * @return 定位的一个元素
     * @throws NoSuchElementException 元素未找到
     */
    public static WebElement findElementByType(WebDriver driver,
                                               String locatedType, String locatedInfo) throws NoSuchElementException {
        WebElement element;
        locatedType = locatedType.toLowerCase();
        try {
            // 根据传入的定位方式，选择调用不同的定位方法
            switch (locatedType) {
                case "id":
                    element = WaitMostSeconds(driver, By.id(locatedInfo));
                    break;
                case "classname":
                    element = WaitMostSeconds(driver, By.className(locatedInfo));
                    break;
                case "tagname":
                    element = WaitMostSeconds(driver, By.tagName(locatedInfo));
                    break;
                case "xpath":
                    element = WaitMostSeconds(driver, By.xpath(locatedInfo));
                    break;
                case "cssselector":
                    element = WaitMostSeconds(driver, By.cssSelector(locatedInfo));
                    break;
                default:
                    throw new NoSuchElementException("Unexpected type: " + locatedType);
            }
            return element;
        } catch (NoSuchElementException e) {
            logger.info("控件未出现");
        }
        throw new NoSuchElementException("控件获取失败");
    }

    /**
     * 根据给定的查找方式查找元素控件（查找多个时），同时使用显式等待
     * @param driver 驱动
     * @param locatedType 定位类型
     * @param locatedInfo 定位信息
     * @return 定位的多个元素
     * @throws NoSuchElementException 元素未找到
     */
    public static List<WebElement> findElementsByType(WebDriver driver,
                                 String locatedType, String locatedInfo) throws NoSuchElementException {
        try {
            // 判断是否有该元素出现
            findElementByType(driver, locatedType, locatedInfo);
            List<WebElement> elements = new ArrayList<>();
            locatedType = locatedType.toLowerCase();

            switch (locatedType) {
                case "id":
                    elements = driver.findElements(By.id(locatedInfo));
                    break;
                case "classname":
                    elements = driver.findElements(By.className(locatedInfo));
                    break;
                case "tagname":
                    elements = driver.findElements(By.tagName(locatedInfo));
                    break;
                case "xpath":
                    elements = driver.findElements(By.xpath(locatedInfo));
                    break;
                case "cssselector":
                    elements = driver.findElements(By.cssSelector(locatedInfo));
                    break;
                default:
                    throw new IllegalStateException("Unexpected type: " + locatedType);
            }
            return elements;
        } catch (NoSuchElementException e) {
            logger.info("控件未出现");
        } catch (Exception e) {
            logger.info("获取多个控件元素失败");
        }
        throw new NoSuchElementException("控件获取失败");
    }

    /**
     * Selenium方法等待元素出现
     * @param driver 驱动
     * @param by 元素定位方式
     * @return 元素控件
     */
    public static WebElement WaitMostSeconds(WebDriver driver, By by) {
        try {
            WebDriverWait AppiumDriverWait = new WebDriverWait(driver, Settings.elementControl.elementWaitTime);
            return (WebElement) AppiumDriverWait.until(ExpectedConditions
                    .presenceOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NoSuchElementException("元素控件未出现");
    }


}
