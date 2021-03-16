package com.liyanfei.pages;

import com.liyanfei.utils.FindElement;
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


    /**
     * 登陆页面: 改变登陆方式
     * @throws Exception
     */
    public void switchLoginWay() throws Exception{
        //  切换iframe
        element = FindElement.findElementByType(chromeDriver, "xpath",
                "//*[@id='j-yx-mobileLoginWrap']/iframe");
        chromeDriver.switchTo().frame(element);
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "a.tab0");
        element.click();
    }

    /**
     * 登陆页面: 输入手机号和密码
     * @param telephone 手机号
     * @param password 密码
     */
    public void inputCount(String telephone, String password) {
        element = FindElement.findElementByType(chromeDriver, "id", "phoneipt");
        element.clear();
        element.sendKeys(telephone);
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "input[autocomplete=new-password]");
        element.clear();
        element.sendKeys(password);
    }


    /**
     * 登陆页面: 正确的手机号和密码时的确认点击，因为后续需要验证用户名，所以需要切换为默认的iframe
     */
    public void clickSuccessLogin() {
        clickLogin();
        // 切换为初始frame
        chromeDriver.switchTo().defaultContent();
    }

    /**
     * 登陆页面: 错误的手机号和密码时的确认点击，不能切换iframe，因为需要验证错误提示信息
     */
    public void clickLogin() {
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "a.u-loginbtn.btncolor.tabfocus");
        element.click();
    }


    /**
     * 登陆页面: 验证提示信息是否正确
     * @param expected 期望的错误信息
     * @return
     */
    public boolean verifyInfo(String expected) {
        element = FindElement.findElementByType(chromeDriver, "cssSelector",
                "div.ferrorhead");
        if (element.getText().trim().equals(expected)) {
            return true;
        }
        return false;
    }
}
