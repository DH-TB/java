package com.cultivation.javaBasic.showYourIntelligence;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start

        StackTraceElement[] exceptionInfo = new Throwable().getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();

        if(exceptionInfo.length >= 1){
            stringBuilder.append(exceptionInfo[1].getClassName())
                    .append(".")
                    .append(exceptionInfo[1].getMethodName());
        }
        return stringBuilder.toString();
        // --end-->
    }
}
