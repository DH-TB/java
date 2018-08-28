package com.cultivation.javaBasic.showYourIntelligence;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start

        StackTraceElement[] exceptionInfo = new Exception().getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();

        for (StackTraceElement stackTraceElement : exceptionInfo){
            System.out.println(stackTraceElement.getClassName());
        }
        if(exceptionInfo.length > 0){
            stringBuilder.append(exceptionInfo[1].getClassName())
                    .append(".")
                    .append(exceptionInfo[1].getMethodName());
        }
        return stringBuilder.toString();
        // --end-->
    }
}
