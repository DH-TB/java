package com.cultivation.javaBasicExtended.reflection.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
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

    public Response getResponse(String requestController, String requestMethod) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        // TODO: Please implement the method
        // <--start
        Class aClass = controllerList.stream().filter(c -> c.getName().equals(requestController)).findAny().orElse(null);

        if(Objects.isNull(aClass)){
            return new Response(404);
        }

        Method method = getMethod(requestMethod, aClass);
        if (Objects.isNull(method)){
            return new Response(404);
        }

        Object instance = aClass.newInstance();

        return getResult(instance, method);
        // --end-->
    }

    private Method getMethod(String requestMethod, Class aClass) {
        Method[] declaredMethods = aClass.getDeclaredMethods();
        Method method = Arrays.stream(declaredMethods).filter(d -> d.getName().equals(requestMethod)).findAny().orElse(null);
        if(Objects.isNull(method)){
            return null;
        }
        return method;
    }

    private Response getResult(Object instance, Method method) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (!isPublic(method)) return new Response(403);
        if (!returnTypeIsResponse(method)) return new Response(503);
        if(method.getParameterCount() > 0) return new Response(503);

        return invokeMethod(instance, method);
    }

    private Response invokeMethod(Object instance, Method method) {
        Object result;
        try {
            result = method.invoke(instance);
        }
        catch (Exception e){
            return new Response(500);
        }

        return (Response) result;
    }

    private boolean returnTypeIsResponse(Method declaredMethod) {
        Class<?> returnType = declaredMethod.getReturnType();
        return returnType == Response.class;
    }

    private boolean isPublic(Method declaredMethod) {
        return Modifier.isPublic(declaredMethod.getModifiers());
    }

    // TODO: You can add additional methods here if you want
    // <--start
    // --end-->
}