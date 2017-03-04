package com.getprepared.util.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by koval on 04.03.2017.
 */
public class PackageScanner {

    private static final Logger LOG = Logger.getLogger(PackageScanner.class);

    private static final char PACKAGE_SEPARATOR = '.';
    private static final char DIRECTORY_SEPARATOR = '/';
    private static final String CLASS_FILE_SUFFIX = ".class";

    public static List<Class<?>> scan(final String scannedPackage) {
        final String scannedPath = scannedPackage.replace(PACKAGE_SEPARATOR, DIRECTORY_SEPARATOR);
        final URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);

        if (scannedUrl == null) {
            final String errorMsg = String.format("Failed to get resources from path %s", scannedPackage);
            LOG.error(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }

        final File scannedDirectory = new File(scannedUrl.getFile());
        final List<Class<?>> classes = new ArrayList<>();
        final File[] directoryFiles = scannedDirectory.listFiles();

        if (ArrayUtils.isNotEmpty(directoryFiles)) {
            Arrays.stream(directoryFiles).forEach(file -> {
                classes.addAll(scan(file, scannedPackage));
            });
            return classes;
        } else {
            return Collections.emptyList();
        }
    }

    private static List<Class<?>> scan(final File scannedFile, final String scannedPackage) {
        final List<Class<?>> classes = new ArrayList<>();
        final String resource = scannedPackage + PACKAGE_SEPARATOR + scannedFile.getName();
        if (scannedFile.isDirectory()) {
            final File[] directoryFiles = scannedFile.listFiles();
            if (ArrayUtils.isNotEmpty(directoryFiles)) {
                Arrays.stream(directoryFiles).forEach(child -> classes.addAll(scan(child, resource)));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            final int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            final String className = resource.substring(0, endIndex);
            classes.add(ReflectionUtils.getClassForName(className));
        }
        return classes;
    }

}
