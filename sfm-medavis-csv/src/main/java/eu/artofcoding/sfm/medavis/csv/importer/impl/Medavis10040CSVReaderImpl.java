/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

//import eu.artofcoding.sfm.medavis.csv.importer.helper.ParseHelper;

import eu.artofcoding.sfm.medavis.csv.importer.api.Medavis10040CSVReader;
import eu.artofcoding.sfm.medavis.csv.importer.bean.Medavis10040Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read contents of a Medavis (weird) CSV file and put it into database:
 * #10040: Stammdaten Untersuchungen mit OPS (festhinterlegt).
 * @author rbe
 */
public class Medavis10040CSVReaderImpl extends AbstractMedavisCSVReader<Medavis10040Bean> implements Medavis10040CSVReader {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis10040CSVReaderImpl.class);

    /**
     * Constructor.
     */
    public Medavis10040CSVReaderImpl() {
        //logger.debug(".init<>");
        clazz = Medavis10040Bean.class;
    }

    /**
     * @param bean A data bean.
     * @param list One line of the CSV file, containing Strings
     */
    @Override
    protected void parseData(Object bean, String[] list) {
        // Cast argument
        Medavis10040Bean medavisBean = (Medavis10040Bean) bean;
        // Number of columns
        int numberOfColumns = list.length;
        // Column counter
        int colCount = 0;
        // Process every column
        for (String entry : list) {
            // Fill Medavis10040Bean with data
            //   col =  0         1                   2                                       3      4        5          6
            //         ["Kürzel", "Kurzbezeichnung", "Bezeichnung",                           "Art", "Code",  "Version", ""]
            //         ["raxab",  "DSA Abdomen",     "Arteriografie der Gefäße des Abdomens", "OPS", "3-604", "2010",    ""]
            switch (colCount) {
                case 0:
                    medavisBean.setKuerzel(entry);
                    break;
                case 1:
                    medavisBean.setKurzbezeichnung(entry);
                    break;
                case 2:
                    medavisBean.setBezeichnung(entry);
                    break;
                case 3:
                    medavisBean.setArt(entry);
                    break;
                case 4:
                    medavisBean.setCode(entry);
                    break;
                case 5:
                    medavisBean.setVersion(entry);
                    break;
            }
            // Increase column counter
            colCount++;
        }
    }

}
