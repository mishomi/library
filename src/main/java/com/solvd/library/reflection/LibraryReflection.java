package com.solvd.library.reflection;

import com.solvd.library.annotations.LibraryFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class LibraryReflection {

    private static final Logger log = LogManager.getLogger(LibraryReflection.class);
    private LibraryReflection() {
    }

    public static void inspectClass(Class<?> clazz) {
        log.info("CLASS: " + clazz.getName());
        log.info("MODIFIERS: " + Modifier.toString(clazz.getModifiers()));

        log.info("FIELDS:");
        for (Field field : clazz.getDeclaredFields()) {
            log.info("  " + Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " " + field.getName());
        }

        log.info("CONSTRUCTORS:");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            log.info("  " + Modifier.toString(constructor.getModifiers()) + " " + constructorSignature(constructor));
        }

        log.info("METHODS:");
        for (Method method : clazz.getDeclaredMethods()) {
            log.info("  " + Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getSimpleName() + " " + methodSignature(method));
        }
    }

    public static void handleCustomAnnotations(Class<?> clazz) {
        if (clazz.isAnnotationPresent(LibraryFeature.class)) {
            LibraryFeature feature = clazz.getAnnotation(LibraryFeature.class);
            log.info("CLASS ANNOTATION ON " + clazz.getSimpleName() + ": " + feature.value());
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(LibraryFeature.class)) {
                LibraryFeature feature = field.getAnnotation(LibraryFeature.class);
                log.info("FIELD ANNOTATION ON " + field.getName() + ": " + feature.value());
            }
        }

        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(LibraryFeature.class)) {
                LibraryFeature feature = constructor.getAnnotation(LibraryFeature.class);
                log.info("CONSTRUCTOR ANNOTATION: " + feature.value());
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LibraryFeature.class)) {
                LibraryFeature feature = method.getAnnotation(LibraryFeature.class);
                log.info("METHOD ANNOTATION ON " + method.getName() + ": " + feature.value());
            }
        }
    }

    public static void createObjectAndCallMethodUsingReflection() {
        try {
            Class<?> libraryClass = Class.forName("com.solvd.library.organization.Library");
            Constructor<?> constructor = libraryClass.getConstructor(String.class);
            Object libraryObject = constructor.newInstance("Reflection Library");

            Method method = libraryClass.getMethod("getDescription");
            Object result = method.invoke(libraryObject);

            log.info("REFLECTION INVOCATION RESULT: " + result);
        } catch (Exception e) {
            log.error("Reflection error: " + e.getMessage());
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