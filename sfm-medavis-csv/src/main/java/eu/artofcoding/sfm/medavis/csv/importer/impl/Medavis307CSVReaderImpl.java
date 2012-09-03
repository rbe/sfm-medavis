/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.api.Medavis307CSVReader;
import eu.artofcoding.sfm.medavis.csv.importer.bean.Medavis307Bean;
import eu.artofcoding.sfm.medavis.csv.importer.helper.ParseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read contents of a Medavis (weird) CSV file and put it into database:
 * #307: Untersuchungen mit Episodennummer u. OPS (Fallbezogen).
 * @author rbe
 */
public class Medavis307CSVReaderImpl extends AbstractMedavisCSVReader<Medavis307Bean> implements Medavis307CSVReader {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis307CSVReaderImpl.class);

    /**
     * Constructor.
     */
    public Medavis307CSVReaderImpl() {
        //logger.debug(".init<>");
        clazz = Medavis307Bean.class;
    }

    /**
     * @param bean A data bean.
     * @param list One line of the CSV file, containing Strings
     */
    @Override
    protected void parseData(Object bean, String[] list) {
        // Cast argument
        Medavis307Bean medavisBean = (Medavis307Bean) bean;
        // Number of columns
        int numberOfColumns = list.length;
        // Column counter
        int colCount = 0;
        // Process every column
        for (String entry : list) {
            // Fill Medavis307Bean with data
            //   col =  0            1                             2            3          4             5                   6           7
            //         ["Unt.-Datum, "Überweiser",                 "Nachname",  "Vorname", "Geb.-Datum", "Untersuchung",     "Episode",  "OPS",]
            //         ["01.01.2011, "CA Dr. med. Jxx-Bxxxx Xxxx", "Bxxxxxxxx", "Pxxxx",   "04.07.1949", "CT Schädel nativ", "10925844", "3-200","]
            switch (colCount) {
                case 0:
                    java.util.Date untDatum = ParseHelper.parseDate(entry);
                    if (null != untDatum) {
                        medavisBean.setUntersuchungDatum(untDatum);
                    } else {
                        logger.error(String.format("parseData: Could not parse untersuchungDatum in line number %d '%s':", totalLineNumber, entry));
                    }
                    break;
                case 1:
                    medavisBean.setUeberweiser(entry);
                    break;
                case 2:
                    medavisBean.setNachname(entry);
                    break;
                case 3:
                    medavisBean.setVorname(entry);
                    break;
                case 4:
                    java.util.Date gebDatum = ParseHelper.parseDate(entry);
                    if (null != gebDatum) {
                        medavisBean.setGeburtstag(gebDatum);
                    } else {
                        logger.error(String.format("parseData: Could not parse untersuchung in line number %d '%s':", totalLineNumber, entry));
                    }
                    break;
                case 5:
                    medavisBean.setUntersuchung(entry);
                    break;
                case 6:
                    medavisBean.setEpisode(entry);
                    break;
                case 7:
                    medavisBean.setOps(entry);
                    break;
            }
            // Increase column counter
            colCount++;
        }
    }

}
