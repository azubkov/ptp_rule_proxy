package azoo.com.ptp_rule_proxy.xml;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoader {
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

    public String readResource(String path) {
        try {
            URL url = Resources.getResource(path);
            String content = Resources.toString(url, Charsets.UTF_8);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
