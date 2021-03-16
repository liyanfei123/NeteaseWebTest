package com.liyanfei.pages;

import com.liyanfei.utils.FindElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    public static Logger logger = Logger.getLogger(HomePage.class.getName());
    public static WebDriver chromeDriver;
    public static WebElement element;

    public HomePage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     * 主页: 进入登陆页
     * @return
     */
    public LoginPage goLoginPage() {
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "div.yx-cp-siteNavItemHd > div.j-yx-cp-topLogin");
        element.click();
        return new LoginPage(chromeDriver);
    }


    /**
     * 主页: 验证是否登陆成功
     * @param username 用户名
     */
    public boolean verifyLogin(String username) {
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "span.yx-cp-userName");
        if (element.getText().trim().equals(username)) {
            return true;
        }
        return false;
    }

    /**
     * 主页: 给定商品名，进行商品搜索
     * @param commodityName 商品名
     */
    public CommodityPage searchCommodity(String commodityName) {
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "input.yx-cp-searchInput");
        element.clear();
        element.sendKeys(commodityName);
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "span.yx-cp-searchButtonName");
        element.click();
        return new CommodityPage(chromeDriver);
    }


}
