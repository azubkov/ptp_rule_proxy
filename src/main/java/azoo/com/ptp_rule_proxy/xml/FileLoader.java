package azoo.com.ptp_rule_proxy.xml;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoader {
    private static final Logger LOGGER = Logger.getLogger(FileLoader.class);

    @NotNull
    public String readUrl(String string) throws Exception {
        try {
            URL url = new URL(string);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String message = org.apache.commons.io.IOUtils.toString(in);
                return message;
            } catch (Exception e) {
                throw new Exception(e);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @NotNull
    public String readFile(String filePath) throws Exception {
        try {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            String string = new String(bytes);
            return string;
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    @NotNull
    public String readResource(String path) {
        try {
            URL url = Resources.getResource(path);
            String content = Resources.toString(url, Charsets.UTF_8);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Accepts url, local files, jar resources
     */
    @NotNull
    public String readContent(String path) throws Exception {
        String content = null;
        Exception[] es = new Exception[3];
        try {
            content = readFile(path);
        } catch (Exception e) {
            /*expected, no logging required */
            es[0] = e;
        }
        if (content != null) {
            return content;
        }

        try {
            content = readUrl(path);
        } catch (Exception e) {
            /*expected, no logging required */
            es[1] = e;
        }
        if (content != null) {
            return content;
        }

        try {
            content = readResource(path);
        } catch (Exception e) {
            /*expected, no logging required */
            es[2] = e;
        }
        if (content != null) {
            return content;
        }
        /*as we reach this point exceptions printout required*/
        throw new Exception(String.join("\n[or?] ", es[0].getMessage(), es[1].getMessage(), es[2].getMessage()));
    }

    @Nullable
    public String readContentSafely(String path) {
        try {
            String string = readContent(path);
            return string;
        } catch (Exception e) {
            LOGGER.error(e, e);
            return null;
        }
    }
}
