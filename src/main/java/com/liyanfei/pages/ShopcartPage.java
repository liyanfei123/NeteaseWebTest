package com.liyanfei.pages;

import com.liyanfei.utils.FindElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ShopcartPage {

    public static Logger logger = Logger.getLogger(ShopcartPage.class.getName());
    public static WebDriver chromeDriver;
    public static WebElement element;

    public ShopcartPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     * 购物车页面: 验证购物车中的第一件商品是否是刚刚添加的商品
     * @param expected 期望的商品名称
     * @return
     */
    public boolean verifyCommodity(String expected) {
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "div.nameCon > a");
        if (element.getText().trim().equals(expected)) {
            return true;
        }
        return false;
    }

}
