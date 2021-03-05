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

    public LoginPage goLoginPage() {
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "div.yx-cp-siteNavItemHd > div.j-yx-cp-topLogin");
        element.click();
        return new LoginPage(chromeDriver);
    }

    /**
     * 验证是否登陆成功
     * @param username
     */
    public boolean verifyLogin(String username) {
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "span.yx-cp-userName");
        if (element.getText().trim().equals(username)) {
            return true;
        }
        return false;
    }
}
