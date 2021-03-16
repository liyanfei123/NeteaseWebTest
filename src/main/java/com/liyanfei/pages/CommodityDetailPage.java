package com.liyanfei.pages;

import com.liyanfei.utils.FindElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CommodityDetailPage {
    public static Logger logger = Logger.getLogger(CommodityDetailPage.class.getName());
    public static WebDriver chromeDriver;
    public static WebElement element;

    public CommodityDetailPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     * 切换浏览器窗口
     * 切换到商品详情页窗口
     */
    public void switchToCommodityPage() {
        String[] windows = new String[chromeDriver.getWindowHandles().size()];  // 获取所有浏览器窗口数量
        chromeDriver.getWindowHandles().toArray(windows);
        chromeDriver.switchTo().window(windows[1]);  // 切换到第二个窗口
    }

    /**
     * 商品详情页: 点击 加入购物车
     */
    public void addCart() {
        element = FindElement.findElementByType(chromeDriver,
                "cssSelector", "a.j-button-addCart");
        element.click();
    }


    /**
     * 商品详情页: 进入购物车
     * @return
     */
    public ShopcartPage goShopCart() {
        element = FindElement.findElementByType(chromeDriver,
                "cssSelector", "div.yx-cp-m-cartEnter");
        element.click();
        return new ShopcartPage(chromeDriver);
    }
}
