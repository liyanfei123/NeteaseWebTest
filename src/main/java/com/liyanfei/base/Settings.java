package com.liyanfei.base;

import java.io.File;

public class Settings {

    public static String testUrl = "http://you.163.com/";

    public static class elementControl {
        public static final int elementWaitTime = 10;
        public static final int elementInspectCount = 3;   // 控件识别次数
        public static final int elementInspectInterval = 200;  //  控件识别间隔时间
    }


    public static class userInfo {
        public static String username = "15806284945";
        public static String password = "710662952lyf";
    }



    public static class testCaseFile {
        public static String dir = System.getProperty("user.dir") + File.separator +"data";
        public static String file = "AllTestCase.xlsx";
    }

    /**
     * 所有测试用例的调度Sheet中的每个元素的序号
     */
    public static class testCaseSchedule {
        public static final String sheetName = "Main";
        public static final int testCaseName = 0;
        public static final int testCaseDetail = 1;
        public static final int isRun = 2;
        public static final int result = 3;
    }

    /**
     * 单个测试用例Sheet中的每个元素的序号
     */
    public static class testCase {
        public static final int testCaseId = 0;
        public static final int testStepId = 1;
        public static final int testStepDetail = 2;
        public static final int objectName = 3;
        public static final int locatedType = 4;
        public static final int inspector = 5;
        public static final int actionStep = 6;
        public static final int data = 7;
        public static final int result = 8;
    }


}
