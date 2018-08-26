package com.cultivation.javaTest;

import org.junit.jupiter.api.Test;

class AnnotationTest {

    @Test
    void should_get_annotation_when_suppress_warning(){
        java.lang.annotation.Annotation[] annotations = Annotation.class.getAnnotations();
        for(java.lang.annotation.Annotation annotation : annotations){

            System.out.println(annotation);
        }
    }
}
