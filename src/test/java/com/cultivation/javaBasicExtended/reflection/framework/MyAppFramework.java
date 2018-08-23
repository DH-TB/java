package com.cultivation.javaBasicExtended.reflection.framework;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class MyAppFramework {
    private List<Class> controllerList = new ArrayList<>();
    public void registerController(Class controllerClazz) {
        // TODO: Please implement the method
        // <--start

        Class aClass = controllerList.stream().filter(c -> c == controllerClazz).findAny().orElse(null);
        if(Objects.nonNull(aClass)){
            throw new IllegalArgumentException();
        }
        controllerList.add(controllerClazz);

        // --end-->
    }

    public Response getResponse(String requestController, String requestMethod) {
        // TODO: Please implement the method
        // <--start
        throw new NotImplementedException();
        // --end-->
    }

    // TODO: You can add additional methods here if you want
    // <--start
    // --end-->
}