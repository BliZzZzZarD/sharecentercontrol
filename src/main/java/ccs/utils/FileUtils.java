package ccs.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static String loadScriptFromFile(String filename) {
        try {
            return IOUtils.toString(FileUtils.class.getResourceAsStream(filename), Charset.defaultCharset());
        } catch (IOException e) {
            log.error("Error on loading '{}' script.", filename, e);
        }
        return null;
    }

}

