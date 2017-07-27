package it.arlanis.reply.resources.config;

import java.util.List;

/**
 * Created by Onofrio Falco on 27/07/2017.
 */
public class Config {
    private List<String> paths;
    private List<String> templates;

    public Config() {
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public List<String> getTemplates() {
        return templates;
    }

    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }

    @Override
    public String toString() {
        return "Config{" +
                "paths=" + paths +
                ", templates=" + templates +
                '}';
    }
}
