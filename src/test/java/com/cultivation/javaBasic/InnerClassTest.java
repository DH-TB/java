package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnonymousClassUpdateField;
import com.cultivation.javaBasic.util.InnerClassUpdateField;
import com.cultivation.javaBasic.util.LocalClassUpdateField;
import com.cultivation.javaBasic.util.StaticInnerClass;
import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//
class InnerClassTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_access_instance_field_of_parent_class() {
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField();
        innerClassUpdateField.somethingHappen();

        Optional<?> integer = Optional.of(2020);
        assertEquals(integer.get(), innerClassUpdateField.getYear());
    }


    @SuppressWarnings("ConstantConditions")
    @Test
    void should_refer_inner_class_from_outside() {
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField();
        InnerClassUpdateField.InnerClass innerClass = innerClassUpdateField.new InnerClass();

        innerClass.increment();

        Optional<?> integer = Optional.of(2020);

        assertEquals(integer.get(), innerClassUpdateField.getYear());
    }

    @Test
    void should_test_innerclass_add(){
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField();
        InnerClassUpdateField.InnerClass innerClass = innerClassUpdateField.new InnerClass(2);
        innerClass.add();

        Optional<?> integer = Optional.of(2020);

        assertEquals(integer.get(), innerClassUpdateField.getYear());
    }


    @Test
    void should_test_innerclass_(){
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField();

        innerClassUpdateField.add(2);

        Optional<?> integer = Optional.of(2020);

        assertEquals(integer.get(), innerClassUpdateField.getYear());
    }

    //成员内部类

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_update_field_using_local_class() {
        LocalClassUpdateField instance = new LocalClassUpdateField();
        instance.somethingHappen();

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Integer> expected = Optional.of(2019);
        // --end-->

        assertEquals(expected.get().intValue(), instance.getYear());
    }
    //局部内部类

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_update_field_using_anonymous_class() {
        AnonymousClassUpdateField instance = new AnonymousClassUpdateField();
        instance.somethingHappen();

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Integer> expected = Optional.of(2019);
        // --end-->

        assertEquals(expected.get().intValue(), instance.getYear());
    }
    //匿名类

    @Test
    void should_create_instance_for_static_inner_class() {
        StaticInnerClass instance = new StaticInnerClass();
        StaticInnerClass.Inner inner = instance.createInner();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "Hello";
        // --end-->

        assertEquals(expected, inner.getName());
    }

}

//http://www.runoob.com/w3cnote/java-inner-class-intro.html