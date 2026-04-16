package com.solvd.library.reflection;

import com.solvd.library.annotations.LibraryFeature;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class LibraryReflection {

    private LibraryReflection() {
    }

    public static void inspectClass(Class<?> clazz) {
        System.out.println("CLASS: " + clazz.getName());
        System.out.println("MODIFIERS: " + Modifier.toString(clazz.getModifiers()));

        System.out.println("FIELDS:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println("  " + Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("CONSTRUCTORS:");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.println("  " + Modifier.toString(constructor.getModifiers()) + " " + constructorSignature(constructor));
        }

        System.out.println("METHODS:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("  " + Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getSimpleName() + " " + methodSignature(method));
        }
    }

    public static void handleCustomAnnotations(Class<?> clazz) {
        if (clazz.isAnnotationPresent(LibraryFeature.class)) {
            LibraryFeature feature = clazz.getAnnotation(LibraryFeature.class);
            System.out.println("CLASS ANNOTATION ON " + clazz.getSimpleName() + ": " + feature.value());
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(LibraryFeature.class)) {
                LibraryFeature feature = field.getAnnotation(LibraryFeature.class);
                System.out.println("FIELD ANNOTATION ON " + field.getName() + ": " + feature.value());
            }
        }

        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(LibraryFeature.class)) {
                LibraryFeature feature = constructor.getAnnotation(LibraryFeature.class);
                System.out.println("CONSTRUCTOR ANNOTATION: " + feature.value());
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LibraryFeature.class)) {
                LibraryFeature feature = method.getAnnotation(LibraryFeature.class);
                System.out.println("METHOD ANNOTATION ON " + method.getName() + ": " + feature.value());
            }
        }
    }

    public static void createObjectAndCallMethodUsingReflection() {
        try {
            Class<?> libraryClass = Class.forName("main.java.com.solvd.library.organization.Library");
            Constructor<?> constructor = libraryClass.getConstructor(String.class);
            Object libraryObject = constructor.newInstance("Reflection Library");

            Method method = libraryClass.getMethod("getDescription");
            Object result = method.invoke(libraryObject);

            System.out.println("REFLECTION INVOCATION RESULT: " + result);
        } catch (Exception e) {
            System.out.println("Reflection error: " + e.getMessage());
        }
    }

    private static String constructorSignature(Constructor<?> constructor) {
        return constructor.getDeclaringClass().getSimpleName() + "(" + parameters(constructor) + ")";
    }

    private static String methodSignature(Method method) {
        return method.getName() + "(" + parameters(method) + ")";
    }

    private static String parameters(Executable executable) {
        return Arrays.stream(executable.getParameterTypes())
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", "));
    }
}