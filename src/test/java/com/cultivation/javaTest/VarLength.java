package com.cultivation.javaTest;

public class VarLength {

    public String getArgument(int... args){
        return "multiArgument";
    }

    public String getArgument(int arg1, int arg2){
        return "twoArgument";
    }

    public String getArgument(){
        return "noArgument";
    }
}
