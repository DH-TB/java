package com.cultivation.javaBasicExtended.reflection.test;

import com.cultivation.javaBasicExtended.reflection.framework.MyAppFramework;
import com.cultivation.javaBasicExtended.reflection.framework.Response;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyAppFrameworkTest {
    @Test
    void should_throw_if_register_duplicated_controller_clazz() {
        MyAppFramework app = new MyAppFramework();
        app.registerController(StandardController.class);

        assertThrows(IllegalArgumentException.class, () -> app.registerController(StandardController.class));
    }

    @Test
    void should_invoke_request() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyAppFramework app = new MyAppFramework();
        app.registerController(StandardController.class);
        Response response = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.StandardController", "doSomething");

        assertEquals(200, response.getStatus());
        assertEquals("Hello", response.getValue());
    }

    @Test
    void should_get_404_if_no_action_can_be_found() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyAppFramework app = new MyAppFramework();
        app.registerController(StandardController.class);
        Response response = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.StandardController", "what");

        assertEquals(404, response.getStatus());
    }

    // Additional Test

    @Test
    void should_get_404_if_no_controller_is_registered() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyAppFramework app = new MyAppFramework();
        Response response = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.StandardController", "doSomething");

        assertEquals(404, response.getStatus());
    }

    @Test
    void should_get_404_if_requested_controller_does_not_exist() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyAppFramework app = new MyAppFramework();
        app.registerController(AnotherStandardController.class);
        Response response = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.StandardController", "doSomething");

        assertEquals(404, response.getStatus());
    }

    @Test
    void should_get_403_if_the_request_method_is_not_public() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyAppFramework app = new MyAppFramework();
        app.registerController(StandardController.class);
        Response response = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.StandardController", "doSomethingPackagePrivate");

        assertEquals(403, response.getStatus());
    }

    @Test
    void should_create_new_controller_instance_every_time() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyAppFramework app = new MyAppFramework();
        app.registerController(StatefulController.class);

        Response response = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.StatefulController", "updateStatus");
        assertEquals(200, response.getStatus());

        Response getStatusResponse = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.StatefulController", "getStatus");
        assertEquals("initial status", getStatusResponse.getValue());
    }

    @Test
    void should_get_500_if_unhandled_exception_occurred_during_invocation() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyAppFramework app = new MyAppFramework();
        app.registerController(UnhandledExceptionController.class);

        Response response = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.UnhandledExceptionController", "fail");
        assertEquals(500, response.getStatus());
    }

    @Test
    void should_get_503_if_action_return_type_is_not_response() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyAppFramework app = new MyAppFramework();
        app.registerController(BadActionController.class);

        Response response = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.BadActionController", "badAction");
        assertEquals(503, response.getStatus());
    }

    @Test
    void should_get_503_if_action_contains_parameter() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MyAppFramework app = new MyAppFramework();
        app.registerController(BadActionController.class);

        Response response = app.getResponse(
            "com.cultivation.javaBasicExtended.reflection.test.BadActionController", "badActionWithParameter");
        assertEquals(503, response.getStatus());
    }
}
