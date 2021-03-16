package com.liyanfei.pages;

import com.liyanfei.utils.FindElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CommodityPage {

    public static Logger logger = Logger.getLogger(CommodityPage.class.getName());
    public static WebDriver chromeDriver;
    public static WebElement element;

    public CommodityPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    /**
     * 商品搜索结果页: 寻找第一个匹配的商品
     * @param commodityName 待搜索的商品名
     * @return 第一个匹配的商品的元素控件
     */
    public WebElement searchFirstMatchCommodity(String commodityName) {
        List<WebElement> elements = FindElement.findElementsByType(chromeDriver, "cssSelector",
                "h4.name > a");
        // 使用for循环遍历list中的每个元素
        for (WebElement element: elements) {
            if (element.getAttribute("title").contains(commodityName)) {
                return element;
            }
        }
        return null;
    }

    /**
     * 商品搜索结果页:  选择第一件与我们输入的文本相匹配的商品，点击元素
     * @param element 待点击的元素
     * @return
     */
    public CommodityDetailPage selectFirstMatchCommodity(WebElement element) {
        element.click();
        return new CommodityDetailPage(chromeDriver);
    }

}
