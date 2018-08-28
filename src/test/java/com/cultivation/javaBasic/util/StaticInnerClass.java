package com.cultivation.javaBasic.util;

public class StaticInnerClass {
    public Inner createInner() {
        return new Inner("Hello");
    }

    public static class Inner {
        private String name;

        public Inner(String name) {
            this.name = name;
        }

        public Inner() {

        }

        public String getName() {
            return name;
        }

        public class InnerClass{

        }

        public static class InnerClass1{

        }
    }

}

class Instance{
    void getInstance(){
        StaticInnerClass.Inner inner = new StaticInnerClass.Inner();
        StaticInnerClass.Inner.InnerClass innerClass = inner.new InnerClass();


        StaticInnerClass.Inner.InnerClass1 innerClass1 = new StaticInnerClass.Inner.InnerClass1();
        
    }
}
