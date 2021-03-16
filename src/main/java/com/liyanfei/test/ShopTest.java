package com.liyanfei.test;

import com.liyanfei.base.SeleniumBase;
import com.liyanfei.pages.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ShopTest extends SeleniumBase {

    public static Logger logger = Logger.getLogger(ShopTest.class.getName());
    public HomePage homePage;
    public CommodityPage commodityPage;
    public CommodityDetailPage commodityDetailPage;
    public LoginPage loginPage;
    public ShopcartPage shopcartPage;

    /**
     * 在未登陆的情况下进行购物车加购测试
     * @param commodityName 商品名称
     * @param telephone 登陆手机号码
     * @param password 登陆密码
     * @param userName 登陆用户名
     * @throws Exception
     */
    @Test(dataProvider = "data")
    public void testShop(String commodityName, String telephone
    , String password, String userName) throws Exception {
        logger.info("初始化主页面");
        homePage = new HomePage(getDriver());
        logger.info("搜索商品:" + commodityName);
        commodityPage = homePage.searchCommodity(commodityName);
        logger.info("寻找第一个匹配的商品");
        WebElement element = commodityPage.searchFirstMatchCommodity(commodityName);
        // 获取商品名
        String expected = element.getAttribute("title");
        System.out.println(expected);
        logger.info("进入第一个商品详情页");
        commodityDetailPage = commodityPage.selectFirstMatchCommodity(element);
        logger.info("切换到商品详情页");
        commodityDetailPage.switchToCommodityPage();
        logger.info("点击 加入购物车");
        commodityDetailPage.addCart();
        logger.info("跳转到登陆页面");
        loginPage = new LoginPage(getDriver());
        logger.info("切换为 使用密码验证登陆");
        loginPage.switchLoginWay();
        logger.info("输入手机:" + telephone + ", 和密码:" + password);
        loginPage.inputCount(telephone, password);
        logger.info("确认登陆");
        loginPage.clickSuccessLogin();
        logger.info("验证用户是否登陆");
        Assert.assertTrue(homePage.verifyLogin(userName));
        logger.info("重新点击 加入购物车");
        commodityDetailPage.addCart();
        logger.info("点击购物车");
        shopcartPage = commodityDetailPage.goShopCart();
        logger.info("判断是否成功加购");
        Assert.assertTrue(shopcartPage.verifyCommodity(expected));
    }

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][] {
                new Object[] {"黑猪肉小笼包", "15806284945", "710662952lyf", "李飞飞"}
        };
    }
}
