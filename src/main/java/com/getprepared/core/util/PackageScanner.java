package com.getprepared.core.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.getprepared.context.Registry.getApplicationContext;
import static com.getprepared.context.constant.ServerConstant.REFLECTION_UTILS;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;

/**
 * Created by koval on 04.03.2017.
 */
public final class PackageScanner {

    private static final Logger LOG = Logger.getLogger(PackageScanner.class);

    private static final char PACKAGE_SEPARATOR = '.';
    private static final char DIRECTORY_SEPARATOR = '/';
    private static final String CLASS_FILE_SUFFIX = ".class";

    public PackageScanner() { }

    public List<Class<?>> scan(final String scannedPackage) {
        final String scannedPath = scannedPackage.replace(PACKAGE_SEPARATOR, DIRECTORY_SEPARATOR);
        final URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);

        if (scannedUrl == null) {
            final String errorMsg = String.format("Failed to get resources from path %s", scannedPackage);
            LOG.fatal(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }

        final File scannedDirectory = new File(scannedUrl.getFile());
        final List<Class<?>> classes = new ArrayList<>();
        final File[] directoryFiles = scannedDirectory.listFiles();

        if (isNotEmpty(directoryFiles)) {
            stream(directoryFiles).forEach(file -> {
                classes.addAll(scan(file, scannedPackage));
            });
            return classes;
        } else {
            return Collections.emptyList();
        }
    }

    private List<Class<?>> scan(final File scannedFile, final String scannedPackage) {
        final List<Class<?>> classes = new ArrayList<>();
        final String resource = scannedPackage + PACKAGE_SEPARATOR + scannedFile.getName();
        if (scannedFile.isDirectory()) {
            final File[] directoryFiles = scannedFile.listFiles();
            if (isNotEmpty(directoryFiles)) {
                stream(directoryFiles).forEach(child -> classes.addAll(scan(child, resource)));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            final int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            final String className = resource.substring(0, endIndex);
            classes.add(getApplicationContext().getBean(REFLECTION_UTILS, ReflectionUtils.class)
                    .getClassForName(className));
        }
        return classes;
    }

}
