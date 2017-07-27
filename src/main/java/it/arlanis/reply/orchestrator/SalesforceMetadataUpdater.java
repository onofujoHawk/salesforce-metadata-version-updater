package it.arlanis.reply.orchestrator;

import it.arlanis.reply.orchestrator.inspector.Inspector;
import it.arlanis.reply.orchestrator.parser.ContentParser;
import it.arlanis.reply.resources.Utils;
import it.arlanis.reply.resources.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Onofrio Falco on 27/07/2017.
 */
public class SalesforceMetadataUpdater {
    private static SalesforceMetadataUpdater updater = null;
    private static Object mutex = SalesforceMetadataUpdater.class;
    private SalesforceMetadataUpdater(){
    }

    public static SalesforceMetadataUpdater getInstance() {
        if(updater == null) {
            synchronized (mutex) {
                if(updater == null) updater = new SalesforceMetadataUpdater();
            }
        }
        return updater;
    }

    public void updateApiVersion(final Double VERSION) throws Exception {
        Config config = Utils.configure();
        if(config != null) {
            List templates = config.getTemplates();
            List filePaths = config.getPaths();
            if (templates.size() == filePaths.size()) {
                List<String> parsedFiles = new ArrayList();
                Inspector insp = Inspector.getInstance();
                for(int i=0; i<templates.size(); i++) {
                    String temp = (String) templates.get(i);
                    String path = (String) filePaths.get(i);
                    List resp = insp.inspectAndGetFiles(path, temp);
                    if(resp != null) parsedFiles.addAll(resp);
                }
                if(parsedFiles.size() > 0) {
                    ContentParser parser = ContentParser.getInstance();
                    parser.parseAndReplace(parsedFiles, String.valueOf(VERSION));
                    System.out.println("*** PARSE & REPLACE: FINISH ***");
                }
            } else {
                throw new MetadataUpdaterException("The list size must be the same");
            }
        } else {
            throw new MetadataUpdaterException("Unable to configure object");
        }
    }

    /**
     * Custom exception
     */
    class MetadataUpdaterException extends Exception {
        public MetadataUpdaterException(String e) {
            super(e);
        }
    }
}
