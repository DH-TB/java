package com.cultivation.javaBasic.util;

public class DerivedFromSuperClassWithDefaultConstructor extends SuperClassWithDefaultConstructor {
    public DerivedFromSuperClassWithDefaultConstructor() {
        //  super(); // 默认（隐示 ）调用父类的无参，可以显示指定调用父类的构造函数
        // 保证父类可以先实例化成功
        super();
//      super("arg"); // 只能有一个，且放第一句，编译错误
        addLog("DerivedFromSuperClassWithDefaultConstructor.constructor()");
    }

    public DerivedFromSuperClassWithDefaultConstructor(int arg) {
        this();
//        this("12");  子类只能有一个，且放第一句，编译错误
        addLog("DerivedFromSuperClassWithDefaultConstructor.constructor(int)");
    }

    public DerivedFromSuperClassWithDefaultConstructor(String arg) {
        super(arg);
        addLog("DerivedFromSuperClassWithDefaultConstructor.constructor(String)");
    }
}
