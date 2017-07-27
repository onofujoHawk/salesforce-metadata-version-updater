package it.arlanis.reply;

import it.arlanis.reply.orchestrator.SalesforceMetadataUpdater;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static void exec() throws Exception {
        Double versione = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("Digitare la versione API (Numeric)= ");
            versione = Double.parseDouble(br.readLine());
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
            nfe.printStackTrace();
        }
        SalesforceMetadataUpdater meta = SalesforceMetadataUpdater.getInstance();
        meta.updateApiVersion(versione);
    }

    public static void main(String[] args) throws Exception {
        exec();
    }
}
