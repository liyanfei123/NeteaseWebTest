package com.liyanfei.utils;

import com.liyanfei.base.Settings;
import org.apache.log4j.Logger;
import org.checkerframework.checker.units.qual.A;
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
     * @param element 待单击的元素
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
     * @param element 元素
     * @param data 写入文本
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

    public static void switchFrame(WebDriver driver, WebElement element, String data) throws ActionExpection{
        try {
            System.out.println(driver.getWindowHandles());
            System.out.println(driver.getWindowHandle());
            driver.switchTo().frame(element);
        } catch (Exception e) {
            throw new ActionExpection("切换Frame失败");
        }
    }

    public static void switchDefaultFrame(WebDriver driver, WebElement element, String data) throws ActionExpection {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            throw new ActionExpection("切换为默认Frame失败");
        }
    }

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
     * @param driver
     * @param element
     * @param data
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

    public static void sleep(WebDriver driver, WebElement element, String data) throws ActionExpection {
        try {
            Thread.sleep(3000);
        }catch (Exception e) {
            throw new ActionExpection("等待出错！");
        }
    }

    /**
     * 根据给定的文本验证当前元素是否为所需要的元素
     * @param element 待判断元素
     * @param data 判断文本
     */
//    public static void testVerfiy(AndroidElement element, String data) throws ActionExpection{
//        try {
//            String actual = element.getAttribute("text");
//            if (!actual.equals(data)) {
//                logger.info("验证数据失败");
//                throw new ActionExpection("验证数据失败");
//            }
////            return true;
//        } catch (Exception e) {
//            throw new ActionExpection("验证数据发生异常");
//        }
//    }

//    public static void selectedVerfity(AndroidElement element, String data) throws ActionExpection{
//        try {
//            boolean flag = Boolean.valueOf(data).booleanValue();
//            if (element.isSelected() != flag) {
//                throw new ActionExpection("是否选中验证失败");
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//            throw new ActionExpection("验证是否选中发生异常");
//        }
//    }





}
