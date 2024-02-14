package com.atharvahiwase07.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

/*
 * A Simple utility to read file.
 * First we check the classpath. If found, it is used.
 * If not, then we check the filesystem.
 */
public class ResourceLoader {
    
    private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getResource(String path) throws Exception {
        // we are trying to read from the class path
        log.info("reading resource from location: {}", path);
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        if (Objects.nonNull(stream)) {
            return stream;
        }
        return Files.newInputStream(Path.of(path));
    }
}
