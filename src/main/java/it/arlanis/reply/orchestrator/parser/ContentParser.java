package it.arlanis.reply.orchestrator.parser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Onofrio Falco on 27/07/2017.
 */
public class ContentParser {
    private static ContentParser parser = null;
    private static Object mutex = ContentParser.class;
    private static final String regex = "<apiVersion>\\d+.+";
    private ContentParser() {
    }

    public static ContentParser getInstance() {
        if(parser == null) {
            synchronized (mutex) {
                if(parser == null) parser = new ContentParser();
            }
        }
        return parser;
    }

    public void parseAndReplace(List<String> files, String version) throws IOException {
        if (!version.contains(".0")) version += ".0";
        for(String f : files) {
            Path path = Paths.get(f);
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(regex, "<apiVersion>" + version + "</apiVersion>");
            Files.write(path, content.getBytes(charset));
        }
    }


}
