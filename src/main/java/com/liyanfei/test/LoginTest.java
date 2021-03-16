package com.liyanfei.test;

import com.liyanfei.base.SeleniumBase;
import com.liyanfei.base.Settings;
import com.liyanfei.pages.HomePage;
import com.liyanfei.pages.LoginPage;
import com.liyanfei.utils.DataFromExcel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;


public class LoginTest extends SeleniumBase {
    public static Logger logger = Logger.getLogger(LoginTest.class.getName());
    public HomePage homePage;
    public LoginPage loginPage;


    /**
     * 不论是正确的用户名登陆，还是错误的用户名登陆都需要进行的登陆操作
     * @param telephone 登陆手机号
     * @param password  密码
     * @throws Exception
     */
    public void login(String telephone, String password) throws Exception {
        logger.info("初始化主页面");
        homePage = new HomePage(getDriver());
        logger.info("唤出登陆窗口");
        loginPage = homePage.goLoginPage();
        logger.info("切换为 使用密码验证登陆");
        loginPage.switchLoginWay();
        logger.info("输入手机:" + telephone + ", 和密码:" + password);
        loginPage.inputCount(telephone, password);
        logger.info("确认登陆");
    }


    /**
     * 正确的用户名和密码，成功登陆的测试
     * @param telephone 登陆手机号
     * @param password 登陆密码
     * @param username 登陆用户名
     * @throws Exception
     */
    @Test(dataProvider = "correctData")
    public void successLoginTest(String telephone, String password, String username) throws Exception {
        login(telephone, password);
        loginPage.clickSuccessLogin();
        Assert.assertTrue(homePage.verifyLogin(username));
    }

    /**
     * 错误的用户名和密码，失败的登陆的测试
     * @param data 数据集合，包含手机号、密码、错误提示信息
     * @throws Exception
     */
    @Test(dataProvider = "failData")
    public void failLoginTest(HashMap<String, String> data) throws Exception {
        login(data.get("telephone"), data.get("password"));
        loginPage.clickLogin();
        logger.info("验证错误提示信息");
        Assert.assertTrue(loginPage.verifyInfo(data.get("expected")));
        // 等待5s，避免频繁操作
        Thread.sleep(5000);
    }


    /**
     * 正确的用户信息
     * @return
     * @throws Exception
     */
    @DataProvider(name = "correctData")
    public Object[][] correctData() throws Exception {
        return new Object[][] {
                new Object[] {"158xxxxx", "xxx", "李飞飞"},
        };
    }


    /**
     * 错误的用户信息
     * @return
     * @throws Exception
     */
    @DataProvider(name = "failData")
    public Object[][] failData() throws Exception {
        ArrayList<HashMap<String, String>> datas = DataFromExcel.getAllDataByMaps(Settings.loginData.dir,
                Settings.loginData.file, Settings.loginData.sheetName);
        Object[][] datasNew = new Object[datas.size()][1];
        for (int i = 0; i < datas.size(); i++) {
            datasNew[i][0] = datas.get(i);
        }
        return datasNew;
    }
}

