package com.liyanfei.pages;

import com.liyanfei.utils.FindElement;
import com.liyanfei.utils.MyActions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public static Logger logger = Logger.getLogger(LoginPage.class.getName());
    public static WebDriver chromeDriver;
    public static WebElement element;

    public LoginPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public void switchLoginWay() throws Exception{
        //  切换iframe
        element = FindElement.findElementByType(chromeDriver, "xpath",
                "//*[@id='j-yx-mobileLoginWrap']/iframe");
        chromeDriver.switchTo().frame(element);
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "a.tab0");
        element.click();
    }

    public void inputCount(String telephone, String password) {
        element = FindElement.findElementByType(chromeDriver, "id", "phoneipt");
        element.clear();
        element.sendKeys(telephone);
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "input[autocomplete=new-password]");
        element.clear();
        element.sendKeys(password);
    }

    public void clickLogin() {
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "a.u-loginbtn.btncolor.tabfocus");
        element.click();
        // 切换为初始frame
        chromeDriver.switchTo().defaultContent();
    }
}
