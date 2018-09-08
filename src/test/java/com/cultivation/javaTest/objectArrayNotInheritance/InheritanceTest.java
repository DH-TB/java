package com.cultivation.javaTest.objectArrayNotInheritance;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class InheritanceTest {

    @Test
    void should_not_inheritance_object_array() {
        boolean actual = objectArrayNotInheritance(Student[].class.getSuperclass());
        assertFalse(actual);
    }

    boolean objectArrayNotInheritance(Class<? super Student[]> studentClass) {
        if (studentClass.equals(Person[].class)) return true;

        if (studentClass != Object.class) {
            objectArrayNotInheritance(studentClass.getSuperclass());
        }
        return false;
    }

    @Test
    void should_test_annotation_inherited() {
        Class<Student> aClass = Student.class;

        for (Annotation annotation : aClass.getDeclaredAnnotations()) {
            System.out.println(annotation.annotationType());
            System.out.println(annotation.toString());
        }
    }
}
