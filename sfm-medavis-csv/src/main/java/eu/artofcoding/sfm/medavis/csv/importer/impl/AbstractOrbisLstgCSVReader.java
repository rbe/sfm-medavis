/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Read contents of an 'Orbis Leistungen' CSV file.
 * @author rbe
 */
public abstract class AbstractOrbisLstgCSVReader<T> {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AbstractOrbisLstgCSVReader.class);

    /**
     * Type of Orbis bean.
     */
    protected Class clazz;

    /**
     * Actual line number, is set to total number of lines after file was processed.
     * Empty lines are not counted.
     */
    protected int totalLineNumber;

    /**
     * Constructor.
     */
    protected AbstractOrbisLstgCSVReader() {
        //logger.debug(".init<>");
    }

    /**
     * No header parsing here -- all 'Orbis Leistungen' CSV files have the same structure.
     private void parseHeader(String[] list) {
     }
     */

    /**
     * Parse a single line of CSV data given by Camel.
     * @param list One line of the CSV file, containing Strings
     */
    public Object parseLine(Object bean, List<String> list) {
        String[] entries = list.toArray(new String[list.size()]);
        // Skip first line
        if (totalLineNumber < 2) {
            return null;
        } else /*if (totalLineNumber > 1)*/ {
            // Skip last line; "Summe"
            if (!entries[0].equals("Summe")) {
                // Parse data
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
     * Parse a single line of data and fill Orbis bean.
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
        if (logger.isDebugEnabled()) {
            logger.debug("process: out.body=" + exchange.getOut().getBody());
        }
    }

}
