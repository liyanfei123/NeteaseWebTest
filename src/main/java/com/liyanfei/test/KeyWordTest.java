package com.liyanfei.test;

import com.liyanfei.base.SeleniumBase;
import com.liyanfei.base.Settings;
import com.liyanfei.utils.DataFromExcel;
import com.liyanfei.utils.FindElement;
import com.liyanfei.utils.MyActions;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class KeyWordTest extends SeleniumBase {
    public static Logger logger = Logger.getLogger(KeyWordTest.class.getName());

    public static String locatedType;
    public static String inspector;
    public static String actionStep;
    public static String data;
    public static boolean testResult; // 单个测试用例执行结果

    public static WebElement element;
    public static MyActions myActions;
    public static Method method[];


    /**
     * 根据测试用例文件中给定的测试用例及步骤进行测试
     * @throws Exception
     */
    @Test
    public void keyWordTest() throws Exception {
        try {
            // 测试用例数据文件路径
            DataFromExcel.readExcel(Settings.testCaseFile.dir, Settings.testCaseFile.file);
            String allTestCaseSheet = Settings.testCaseSchedule.sheetName;
            // 获取测试集合中总的测试用例数
            int testCaseAllNum = DataFromExcel.getAllRowNum(allTestCaseSheet);
            logger.info("文件 " + Settings.testCaseFile.file + " 中，总的测试用例数：" + testCaseAllNum);
            // 遍历每个测试用例
            for (int testCaseNum = 1; testCaseNum <= testCaseAllNum; testCaseNum++) {
                // 获取当前测试用例名，其与Sheet名相同
                String testCaseName = DataFromExcel.getCellData(allTestCaseSheet,
                        testCaseNum, Settings.testCaseSchedule.testCaseName);
                // 获取当前测试用例的详细信息
                String testCaseDetail = DataFromExcel.getCellData(allTestCaseSheet,
                        testCaseNum, Settings.testCaseSchedule.testCaseDetail);
                // 判断当前测试用例是否执行
                String isRun = DataFromExcel.getCellData(allTestCaseSheet,
                        testCaseNum, Settings.testCaseSchedule.isRun);

                if (isRun.toLowerCase().equals("ok")) {
                    try {
                        logger.info("运行测试用例: " + testCaseName + "; 测试用例的详细信息: " + testCaseDetail);
                        testResult = true;
                        int testCaseStepAllNum = DataFromExcel.getAllRowNum(testCaseName);
                        logger.info("测试用例: " + testCaseName + ", 总测试步骤数为: " + testCaseStepAllNum);

                        runEachTestCase(testCaseName, testCaseStepAllNum);

                        if (testResult == true) {
                            logger.info("测试用例: " + testCaseName + " 执行成功！");
                        }
                    } catch (Exception e) {
                        logger.info("测试用例: " + testCaseName + " 执行失败！");
                    } finally {
                        DataFromExcel.setCellData(Settings.testCaseFile.dir, Settings.testCaseFile.file,
                                Settings.testCaseSchedule.sheetName, testCaseNum,
                                Settings.testCaseSchedule.result, testResult);
                    }
                } else {
                    logger.info("测试用例: " + testCaseName + " 暂无需运行");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭文件
            DataFromExcel.closeExcel();
        }
    }


    /**
     * 运行文件中的单个测试用例
     * @param testCaseName 测试用例名称
     * @param testCaseStepAllNum 测试用例中的总步骤数
     * @throws Exception
     */
    public void runEachTestCase(String testCaseName, int testCaseStepAllNum) throws Exception {
        // 逐个运行测试步骤
        // 识别元素
        element = null;
        for (int testCaseStep = 1; testCaseStep <= testCaseStepAllNum; testCaseStep++) {
            // 当前测试步骤的详细信息
            String testStepDetail = DataFromExcel.getCellData(testCaseName, testCaseStep,
                    Settings.testCase.testStepDetail);
            logger.info("当前测试步骤: " + testStepDetail);
            // 元素识别方式
            locatedType = DataFromExcel.getCellData(testCaseName, testCaseStep,
                    Settings.testCase.locatedType);
            // 元素识别信息
            inspector = DataFromExcel.getCellData(testCaseName, testCaseStep,
                    Settings.testCase.inspector);
            // 元素操作方式
            actionStep = DataFromExcel.getCellData(testCaseName, testCaseStep,
                    Settings.testCase.actionStep);
            // 元素数据
            data = DataFromExcel.getCellData(testCaseName, testCaseStep,
                    Settings.testCase.data);
            logger.info("执行测试步骤  识别方式: " + inspector + "; 操作: " + actionStep
                    + "; 测试数据: " + data + "\n属于测试用例: " + testCaseName);
            if (!inspector.toLowerCase().equals("null")) {  // 有定位信息时，表示有控件需要定位, 更新element
                logger.info("依据 " + locatedType + " 查找控件，定位信息: " + inspector);
                try {
                    element = FindElement.findElementByType(getDriver(), locatedType, inspector);
                } catch (NoSuchElementException e) {  // 未找到需要的控件
                    System.out.println("控件未找到");
                    element = null;
//                    e.printStackTrace();
                    testResult = false;
                    // 测试步骤Sheet
                    DataFromExcel.setCellData(Settings.testCaseFile.dir, Settings.testCaseFile.file, testCaseName,
                            testCaseStep, Settings.testCase.result, testResult);
                }
            }

            // 若查找元素便失败，则无需执行方法
            if (testResult) {
                try {
                    executeAction(testCaseName, testCaseStep);
                    logger.info("测试步骤: " + testStepDetail  + " 成功！");
                } catch (Exception e) {
//                    e.printStackTrace();
                    testResult = false;
                    logger.info("测试步骤: " + testStepDetail  + " 失败！");
                }
            }
//             测试步骤失败/成功，退出后续测试步骤
            if (testResult == false) {
                logger.info("测试步骤: " + testStepDetail + " 失败，导致整个测试用例失败，停止测试！");
//                Assert.fail("测试步骤失败！导致整个测试用例失败！");
                break;
            }
        }
    }

    /**
     * 找到当前测试步骤需要执行的方法进行执行
     * @param testCaseName 测试用例名称
     * @param testCaseStep 测试用例步骤
     * @throws Exception
     */
    public void executeAction(String testCaseName, int testCaseStep) throws Exception {
        myActions = new MyActions(getDriver());
        method = myActions.getClass().getMethods();
        for (int i = 0; i < method.length; i++) {
            // 找到需要执行的方法，执行
            if (method[i].getName().toLowerCase().equals(actionStep.toLowerCase())) {
                System.out.println(actionStep);
                try {
                        method[i].invoke(myActions, getDriver(), element, data);
                } catch (Exception e) {
                    e.printStackTrace();
                    testResult = false;
                    throw new Exception("测试步骤执行失败");
                }
                finally {
                    DataFromExcel.setCellData(Settings.testCaseFile.dir, Settings.testCaseFile.file, testCaseName,
                            testCaseStep, Settings.testCase.result, testResult);
                    break;
                }
            }
        }
    }
}
