/*
 * sfm-medavis
 * sfm-medavis-csv
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.08.12 12:00
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.helper.ParseHelper;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Read contents of an 'Orbis ICPM' CSV file.
 * @author rbe
 */
public abstract class AbstractOrbisIcpmCSVReader<T> {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AbstractOrbisIcpmCSVReader.class);

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
     * Start date of analysis data.
     */
    protected java.util.Date dateAnalysisFrom;

    /**
     * End date of analysis data.
     */
    protected java.util.Date dateAnalysisTo;

    /**
     * Constructor.
     */
    protected AbstractOrbisIcpmCSVReader() {
        //logger.debug(".init<>");
    }

    /**
     * Remember this two dates,
     * e.g.: Auswertungszeitraum:  ab Sa 01.01.2011 bis Di 23.08.2011
     * @param list One line of the CSV file, containing Strings
     */
    private void parseAnalysisDates(String[] list) {
        // Get the one and only entry in the list
        String entry = list[0];
        try {
            // Split by colon, result: ["Auswertungszeitraum", "ab Sa 01.01.2011 bis Di 23.08.2011"]
            String[] _dates = entry.split(":  ");
            // Split by space: ["ab", "Sa", "01.01.2011", "bis", "Di", "23.08.2011"]
            String[] dates = _dates[1].split(" ");
            // Start
            String dateFrom = dates[2].trim();
            dateAnalysisFrom = ParseHelper.parseDate(dateFrom);
            if (null == dateAnalysisFrom) {
                logger.error(String.format("parseAnalysisDates: Could not parse dateAnalysisFrom in line number %d '%s'", totalLineNumber, entry));
            }
            // End
            String dateTo = dates[5].trim();
            dateAnalysisTo = ParseHelper.parseDate(dateTo);
            if (null == dateAnalysisTo) {
                logger.error(String.format("parseAnalysisDates: Could not parse dateAnalysisTo in line number %d '%s'", totalLineNumber, entry));
            }
            logger.info(String.format("parseAnalysisDates: dateFrom=%s dateTo=%s", dateFrom, dateTo));
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // ignore
            logger.warn(String.format("parseAnalysisDates: Could not parse dateAnalysisTo in line number %d '%s'", totalLineNumber, entry));
        }
    }

    /**
     * Parse a single line of CSV data given by Camel.
     * @param list One line of the CSV file, containing Strings
     */
    public Object parseLine(Object bean, List<String> list) {
        String[] entries = list.toArray(new String[list.size()]);
        // Skip first line
        // parseLine: totalLineNumber=1 size=1: [Druckdatum: 23.08.2011]
        if (totalLineNumber < 4) {
            // There is no data...
            return null;
        } else if (totalLineNumber == 4) {
            // Parse analysis dates
            // parseLine: totalLineNumber=3 size=1: [Auswertungszeitraum:  ab Sa 01.01.2011 bis Di 23.08.2011]
            parseAnalysisDates(entries);
            // There is no data...
            bean = null;
        } else if (totalLineNumber >= 16) {
            // Parse data
            parseData(bean, entries);
        } else {
            // There is no data...
            bean = null;
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
        List<Object> orbisBeans = new ArrayList<Object>();
        // Get payload: every line of the file as a list, contained in a list
        List<List<String>> payload = (List<List<String>>) exchange.getIn().getBody();
        // Process every line
        for (List<String> p : payload) {
            // Increase line counter
            totalLineNumber++;
            // Dynamically create instance of Medavis bean
            Object orbisBean = clazz.newInstance();
            // Parse line contents
            orbisBean = parseLine(orbisBean, p);
            // Add parsed data/bean to list
            if (null != orbisBean) {
                orbisBeans.add(orbisBean);
            }
        }
        //
        exchange.getOut().setBody(orbisBeans);
        /*
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("process: out.body=%s", exchange.getOut().getBody()));
        }
        */
    }

}
