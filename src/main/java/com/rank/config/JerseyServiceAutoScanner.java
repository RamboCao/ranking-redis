package com.rank.config;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JerseyServiceAutoScanner {
    private JerseyServiceAutoScanner() {
    }

    public static Class[] getPublishJerseyServiceClasses(ApplicationContext applicationContext, String... scanPackages) {
        List<Class> jerseyServiceClasses = new ArrayList<>();
        if (scanPackages == null || scanPackages.length == 0) {
            return jerseyServiceClasses.toArray(new Class[jerseyServiceClasses.size()]);
        }
        ClassPathScanningCandidateComponentProvider scanner = new JerseyScanningComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(Produces.class));
        for (var scanPackage : scanPackages) {
            jerseyServiceClasses.addAll(scanner.findCandidateComponents(scanPackage).stream()
                    .map(beanDefinition -> ClassUtils
                            .resolveClassName(beanDefinition.getBeanClassName(), applicationContext.getClassLoader()))
                    .collect(Collectors.toSet()));
        }
        return jerseyServiceClasses.toArray(new Class[jerseyServiceClasses.size()]);
    }

    private static class JerseyScanningComponentProvider extends ClassPathScanningCandidateComponentProvider {
        public JerseyScanningComponentProvider(boolean useDefaultFilters) {
            super(useDefaultFilters);
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            AnnotationMetadata metadata = beanDefinition.getMetadata();
            return (metadata.isIndependent() && metadata.isAbstract() && !beanDefinition.getMetadata().isAnnotation());
        }
    }
}