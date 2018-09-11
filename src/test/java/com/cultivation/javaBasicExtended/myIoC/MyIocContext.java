package com.cultivation.javaBasicExtended.myIoC;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@SuppressWarnings({"WeakerAccess", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class MyIocContext {
    private final Map<Class, BiFunction> definitions = new HashMap<>();
    private static final ReflectiveResolver<MyIocContext> defaultResolver = new ReflectiveResolver<>();

    public void registerBean(Class<?> beanClazz) {
        if (beanClazz == null) throw new IllegalArgumentException();
        // TODO: please implement the method to register bean class definition.
        // <--start

//        if (beanClazz.getName().startsWith("[")) {
//            throw new IllegalArgumentException();
//        }
        Constructor<?>[] constructors = beanClazz.getConstructors();
        long count = Arrays.stream(constructors).filter(constructor -> constructor.getParameterCount() > 0).count();

        if(constructors.length == 0 || count > 0){
            throw new IllegalArgumentException();
        }

        definitions.put(beanClazz, defaultResolver);
        // --end-->
    }

    // TODO: You can add some helper methods to improve readability.
    // <--start


    // --end-->

    public <T> T getBean(Class<T> beanClazz) {
        if (beanClazz == null) throw new IllegalArgumentException();
        BiFunction creator = definitions.get(beanClazz);
        if (creator == null) {
            throw new IllegalArgumentException("Cannot find type" + beanClazz.toString());
        }

        //noinspection unchecked
        return (T) creator.apply(this, beanClazz);
    }
}

class ReflectiveResolver<T extends MyIocContext> implements BiFunction<T, Class, Object> {
    // TODO: Use reflection to create instance of `T`. You can add helper methods if you like.
    // <--start
    @Override
    public Object apply(T context, Class clazz) {
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


        ArrayList<Class> classList = new ArrayList<>();
        classList.add(clazz);

        getClassList(clazz, classList);

        for (Class klass : classList) {
//            System.out.println(klass.getName());

            Field[] declaredFields = klass.getDeclaredFields();
            Field[] fields = Arrays.stream(declaredFields).filter(field -> field.getDeclaredAnnotation(MyIoCInjection.class) != null).toArray(Field[]::new);
            for (Field field : fields) {
                field.setAccessible(true);
                Class<?> aClass = field.getType();

                Object bean = context.getBean(aClass);
                System.out.println(bean);
                try {
                    field.set(instance, bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }

    private void getClassList(Class clazz, ArrayList<Class> classList) {
        Class superclass = clazz.getSuperclass();
        if (superclass != Object.class) {
            classList.add(clazz);
            getClassList(superclass, classList);
        }
    }
    // --end-->
}