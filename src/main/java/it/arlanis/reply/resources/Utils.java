package it.arlanis.reply.resources;

import it.arlanis.reply.resources.config.Config;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Onofrio Falco on 27/07/2017.
 */
public class Utils {
    public static final Config configure() {
        Config config = null;
        Yaml yaml = new Yaml();
        try {
            try( InputStream in = Files.newInputStream(Paths.get("yml/percorso.yml")) ) {
                config = yaml.loadAs( in, Config.class );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
