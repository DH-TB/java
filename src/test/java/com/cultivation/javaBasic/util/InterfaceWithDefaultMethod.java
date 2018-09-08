package com.cultivation.javaBasic.util;

public interface InterfaceWithDefaultMethod {
    int age = 0;

    default String tellMeTheTruthOfTheUniverse() {
        return "The truth of the universe is " + getTheTruthOfTheUniverse();
    }

    default String getTheTruthOfTheUniverse() {
        return "42";
    }

}