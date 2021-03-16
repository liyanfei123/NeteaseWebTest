package com.liyanfei.utils;

import com.liyanfei.base.Settings;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyActions {
    public static Logger logger = Logger.getLogger(MyActions.class.getName());
    public static WebDriver driver;

    public MyActions(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * 实现元素的单击操作
     * @param driver 浏览器驱动
     * @param element 待单击的元素
     * @param data 无实际意义
     * @throws ActionExpection
     */
    public static void click(WebDriver driver, WebElement element, String data) throws ActionExpection {
        try {
            element.click();
//            logger.info("单击成功！");
        } catch (Exception e) {
            // 单击操作出现异常，等待重试
            try {
                Thread.sleep(Settings.elementControl.elementInspectInterval);
            } catch (InterruptedException ex) {
                throw new ActionExpection("点击操作失败！");
            }
            element.click();
        }
    }

    /**
     * 根据传入的数据判断单选框是否需要选择
     * @param element 单选框
     * @param data 是否选中
     * @throws ActionExpection
     */
    public static void radioClick(WebDriver driver, WebElement element, String data) throws ActionExpection {
        try {
            if (data.toLowerCase().equals("yes")) {
                if (!element.isSelected()) {
                    click(driver, element, null);
                }
            } else if (data.toLowerCase().equals("no")) {
                if (element.isSelected()) {
                    click(driver, element, null);
                }
            }
        } catch (Exception e) {
            throw new ActionExpection("单选操作失败！");
        }
    }

    /**
     * 向指定的元素中输入文本
     * @param element 文本输入框
     * @param data 待写入的文本
     * @throws ActionExpection
     */
    public static void input(WebDriver driver, WebElement element, String data) throws ActionExpection {
        try {
            element.clear();
            element.sendKeys(data);
        } catch (Exception e) {
            throw new ActionExpection("文本输入失败");
        }
    }

    /**
     * 切换窗口frame
     * @param driver 浏览器驱动
     * @param element 目标iframe
     * @param data 无实际意义
     * @throws ActionExpection
     */
    public static void switchFrame(WebDriver driver, WebElement element, String data) throws ActionExpection{
        try {
            System.out.println(driver.getWindowHandles());
            System.out.println(driver.getWindowHandle());
            driver.switchTo().frame(element);
        } catch (Exception e) {
            throw new ActionExpection("切换Frame失败");
        }
    }

    /**
     * 切换到默认的frame
     * @param driver  浏览器驱动
     * @param element 无实际意义
     * @param data 无实际意义
     * @throws ActionExpection
     */
    public static void switchDefaultFrame(WebDriver driver, WebElement element, String data) throws ActionExpection {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            throw new ActionExpection("切换为默认Frame失败");
        }
    }

    /**
     * 切换浏览器窗口
     * @param driver 浏览器驱动
     * @param element 无实际意义
     * @param data 需切换到的目标窗口索引
     * @throws ActionExpection
     */
    public static void switchWindow(WebDriver driver, WebElement element, String data) throws ActionExpection {
        try {
            String win = driver.getWindowHandle();
            Set<String> Windows = driver.getWindowHandles();
            List<String> allWindows = new ArrayList<String>(Windows);
            driver.switchTo().window(allWindows.get(Integer.parseInt(data)));
        } catch (Exception e) {
            throw new ActionExpection("切换窗口失败");
        }
    }

    /**
     * 验证某个元素是否存在
     * @param driver 浏览器驱动
     * @param element 待验证的元素
     * @param data 无实际意义
     * @throws ActionExpection
     */
    public static void isExisted(WebDriver driver, WebElement element, String data) throws ActionExpection{
        try {
            if (element == null) {
                throw new ActionExpection("是否选中验证失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionExpection("验证是否选中错误");
        }
    }


    /**
     * 文本验证，验证元素文本是否与期望的相同
     * @param driver 浏览器驱动
     * @param element 待判断元素
     * @param data 待判断文本，即期望文本
     * @throws ActionExpection
     */
    public static void textVerify(WebDriver driver, WebElement element, String data) throws ActionExpection {
        try {
            if (!element.getText().trim().equals(data)) {
                throw new ActionExpection("验证失败");
            }
        } catch (Exception e) {
            throw new ActionExpection("验证失败");
        }
    }

    /**
     * 睡眠操作，即暂停
     * @param driver 浏览器驱动
     * @param element 此处无实际意义
     * @param data 此处无实际意义
     * @throws ActionExpection
     */
    public static void sleep(WebDriver driver, WebElement element, String data) throws ActionExpection {
        try {
            Thread.sleep(3000);
        }catch (Exception e) {
            throw new ActionExpection("等待出错！");
        }
    }
}
