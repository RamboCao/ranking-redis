package com.rank.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author Caolp
 */
public final class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    private ClassUtil() {
    }

    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = System.getSecurityManager() == null
                ? Thread.currentThread().getContextClassLoader()
                : AccessController.doPrivileged((PrivilegedAction<ClassLoader>) () -> Thread.currentThread().getContextClassLoader());

        if (classLoader == null) {
            LOGGER.error("get class loader error, class loader is null , current thread is {}", Thread.currentThread());
            classLoader = ClassUtil.class.getClassLoader();
        }

        return classLoader;
    }
}