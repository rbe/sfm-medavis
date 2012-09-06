/*
 * sfm-medavis
 * sfm-medavis-csv
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.08.12 11:55
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.helper.ParseHelper;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Read contents of a Medavis (weird) CSV file and put it into database.
 * @author rbe
 */
public abstract class AbstractMedavisCSVReader<T> {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AbstractMedavisCSVReader.class);

    /**
     * Type of Medavis bean.
     */
    protected Class clazz;

    /**
     * Actual line number, is set to total number of lines after file was processed.
     * Empty lines are not counted.
     */
    protected int totalLineNumber;

    /**
     * File type, one of:
     * 145 (Stammdaten Hausleistungen),
     * 307 (Dokumentierte OPs),
     * 10025 (Leistungen Material),
     * 10039 (Stammdaten OPs optional),
     * 10040 (Stammdaten OPs fest).
     */
    protected String fileType;

    /**
     * Start date of analysis data.
     */
    protected java.util.Date dateAnalysisFrom;

    /**
     * End date of analysis data.
     */
    protected java.util.Date dateAnalysisTo;

    /**
     * Map of column number: header title.
     */
    protected Map headerMap;

    /**
     * Constructor.
     */
    protected AbstractMedavisCSVReader() {
        //logger.debug(".init<>");
    }

    /**
     * Remeber this two dates,
     * e.g.: Auswertungszeitraum: 01.01.2011  -  31.12.2011.
     * @param list One line of the CSV file, containing Strings
     */
    private void parseAnalysisDates(String[] list) {
        // Get the one and only entry in the list
        String entry = list[0];
        try {
            // Split by colon
            String[] _dates = entry.split(":");
            // Split by dash
            String[] dates = _dates[1].split("-");
            // Start
            String dateFrom = dates[0].trim();
            dateAnalysisFrom = ParseHelper.parseDate(dateFrom);
            if (null == dateAnalysisFrom) {
                logger.error(String.format("parseAnalysisDates: Could not parse dateAnalysisFrom in line number %d '%s'", totalLineNumber, entry));
            }
            // End
            String dateTo = dates[1].trim();
            dateAnalysisTo = ParseHelper.parseDate(dateTo);
            if (null == dateAnalysisTo) {
                logger.error(String.format("parseAnalysisDates: Could not parse dateAnalysisTo in line number %d '%s'", totalLineNumber, entry));
            }
            logger.info("parseAnalysisDates: dateFrom=" + dateFrom + " dateTo=" + dateTo);
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // ignore
            logger.warn(String.format("parseAnalysisDates: Could not parse dateAnalysisTo in line number %d '%s'", totalLineNumber, entry));
        }
    }

    /**
     * @param list One line of the CSV file, containing Strings
     */
    private void parseFileType(String[] list) {
        // Get the one and only entry in the list
        String str = list[0];
        fileType = str.split(":")[0].substring(1);
        logger.info(String.format("parseFileType: fileType=%s", fileType));
    }

    /**
     * Parse line containing column headers,
     * e.g.: ["Kategorie","Kürzel","Bezeichnung","PZN","Einheit","Preis","Ref.Nr.",""]
     * @param list One line of the CSV file, containing Strings
     */
    private void parseHeader(String[] list) {
        for (String e : list) {
            logger.info(String.format("parseHeader: totalLineNumber=%d size=%d: e=%s", totalLineNumber, list.length, e));
        }
        // Number of columns
        int numberOfColumns = list.length;
        // Process every column
        int colCount = 0;
        for (String entry : list) {
        }
    }

    /**
     * Parse a single line of CSV data given by Camel.
     * @param list One line of the CSV file, containing Strings
     */
    public Object parseLine(Object bean, List<String> list) {
        // list   = [  ,"ACCINJEKT","ACC Injekt 300mg","","","0,00","M3558",]
        // .split = ["","ACCINJEKT","ACC Injekt 300mg","","","0,00","M3558",""]
        String[] entries = list.get(0).split(",\"");
        /*
        // Debug
        if (totalLineNumber < 10) {
            ////logger.info("parseLine: totalLineNumber=" + totalLineNumber + " list=" + list);
            for (String e : entries) {
                logger.info("parseLine: totalLineNumber=" + totalLineNumber + " size=" + entries.length + ": e=" + e);
            }
        }
        */
        // Remove any remaining commas and double quotes
        for (int i = 0; i < entries.length; i++) {
            entries[i] = entries[i].replaceAll("\",", "").replaceAll("\"", "");
        }
        // Skip first line
        // parseLine: totalLineNumber=1 size=1: [Druckdatum: 23.08.2011]
        if (totalLineNumber < 2) {
            return null;
        } else if (totalLineNumber == 2) {
            // Parse analysis dates
            // parseLine: totalLineNumber=2 size=1: [Auswertungszeitraum: 01.01.2011  -  31.12.2011]
            parseAnalysisDates(entries);
            // There is no data...
            bean = null;
        } else if (totalLineNumber == 3) {
            // Third line contains information about file type, e.g.: #145: Liste des Materials
            // parseLine: totalLineNumber=3 size=1: [#145: Liste des Materials]
            parseFileType(entries);
            // There is no data...
            bean = null;
        } else if (totalLineNumber == 4) {
            // Read header: [Kategorie,"K?rzel","Bezeichnung","PZN","Einheit","Preis","Ref.Nr.",]
            parseHeader(entries);
            // There is no data...
            bean = null;
        } else {
            // Parse data, if there are more than one entries in list: ["","ACCINJEKT","ACC Injekt 300mg","","","0,00","M3558",""]
            // Last line contains statistics
            // parseLine: totalLineNumber=1545 size=1: [1540 Einträge]
            /*
            // Debug
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < entries.length; i++) sb.append(entries[i]).append(",");
            logger.info("entries.length=" + entries.length + ": " + sb.toString());
            */
            if (entries.length > 1) {
                parseData(bean, entries);
            } else {
                // There is no data...
                bean = null;
            }
        }
        // Return parsed data
        return bean;
    }

    /**
     * Parse a single line of data and fill Medavis bean.
     * @param bean A bean.
     * @param list Single line of CSV data as a String array.
     */
    protected abstract void parseData(Object bean, String[] list);

    /**
     * Camel Processor.
     * @param exchange The Camel Exchange object.
     */
    public void process(Exchange exchange) throws Exception {
        // Reset state
        totalLineNumber = 0;
        // List of beans; one for each line with data from CSV file
        List<Object> medavisBeans = new ArrayList<Object>();
        // Get payload: every line of the file as a list, contained in a list
        List<List<String>> payload = (List<List<String>>) exchange.getIn().getBody();
        // Process every line
        for (List<String> p : payload) {
            // Increase line counter
            totalLineNumber++;
            // Dynamically create instance of Medavis bean
            Object medavisBean = clazz.newInstance();
            // Parse line contents
            medavisBean = parseLine(medavisBean, p);
            /*
            if (totalLineNumber > 10 && totalLineNumber < 20) {
                logger.info("process: " + medavisBean);
            }
            */
            // Add parsed data/bean to list
            if (null != medavisBean) {
                medavisBeans.add(medavisBean);
            }
        }
        //
        exchange.getOut().setBody(medavisBeans);
        /*
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("process: out.body=%s", exchange.getOut().getBody()));
        }
        */
    }

}
