package it.arlanis.reply.orchestrator.inspector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Onofrio Falco on 27/07/2017.
 */
public class Inspector {
    private static Inspector inspector = null;
    private static Object mutex = Inspector.class;
    private Inspector(){
    }

    public static Inspector getInstance() {
        if(inspector == null) {
            synchronized (mutex) {
                if(inspector == null) inspector = new Inspector();
            }
        }
        return inspector;
    }

    public List<String> inspectAndGetFiles(String directoryName, String searchPattern) throws IOException {
        List<File> filesInFolder = new ArrayList();
        List<String> metadataFiles;
        File directory = new File(directoryName);
        List<File> fileList = Arrays.asList(directory.listFiles()); //get all the files from a directory
        System.out.println("DIRECTORY= " + directory.getName() + " | " + fileList.size() + " files");
        for (File file : fileList) {
            if (file.isFile()) {
                System.out.println("   Parsed -> " + file.getName());
                filesInFolder.add(file);
            }
        }
        if (!filesInFolder.isEmpty()) {
            metadataFiles = new ArrayList();
            for (File f : filesInFolder) {
                if(f.getName().contains(searchPattern)) {
                    metadataFiles.add(f.getCanonicalPath());
                }
            }
            return metadataFiles;
        }
        return null;
    }
}
