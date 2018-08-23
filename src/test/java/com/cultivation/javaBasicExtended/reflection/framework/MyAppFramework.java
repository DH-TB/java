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

    public Response getResponse(String requestController, String requestMethod) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // TODO: Please implement the method
        // <--start
        Class aClass = controllerList.stream().filter(c -> c.getName().equals(requestController)).findAny().orElse(null);

        if(Objects.isNull(aClass)){
            return new Response(404);
        }
        Object instance = aClass.newInstance();

        Method method = getMethod(requestMethod, aClass);
        if (Objects.isNull(method)){
            return new Response(404);
        }

        return getResponse(instance, method);
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

    private Response getResponse(Object instance, Method method) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (!isPublic(method)) return new Response("not fount", 403);
        boolean b = returnTypeIsResponse(method);
        if (!b) return new Response("not fount", 503);

        if(method.getParameterCount() > 0){
            return new Response("not fount", 503);
        }
        return (Response) method.invoke(instance);
    }

    private boolean returnTypeIsResponse(Method declaredMethod) {
        Class<?> returnType = declaredMethod.getReturnType();
        System.out.println(returnType.getName());
        return returnType == Response.class;
    }

    private boolean isPublic(Method declaredMethod) {
        return Modifier.isPublic(declaredMethod.getModifiers());
    }

    // TODO: You can add additional methods here if you want
    // <--start
    // --end-->
}