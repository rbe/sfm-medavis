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

import eu.artofcoding.sfm.medavis.csv.importer.api.Medavis145CSVReader;
import eu.artofcoding.sfm.medavis.csv.importer.bean.Medavis145Bean;
import eu.artofcoding.sfm.medavis.csv.importer.helper.ParseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read contents of a Medavis (weird) CSV file and put it into database:
 * #145: Liste des Materials.
 * @author rbe
 */
public class Medavis145CSVReaderImpl extends AbstractMedavisCSVReader<Medavis145Bean> implements Medavis145CSVReader {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis145CSVReaderImpl.class);

    /**
     * Constructor.
     */
    public Medavis145CSVReaderImpl() {
        //logger.debug(".init<>");
        clazz = Medavis145Bean.class;
    }

    /**
     * @param bean A data bean.
     * @param list One line of the CSV file, containing Strings
     */
    @Override
    protected void parseData(Object bean, String[] list) {
        // Cast argument
        Medavis145Bean medavisBean = (Medavis145Bean) bean;
        // Number of columns
        int numberOfColumns = list.length;
        // Column counter
        int colCount = 0;
        // Process every column
        for (String entry : list) {
            // Fill Medavis145Bean with data
            //   col =  0            1            2                   3      4          5        6          7
            //         ["Kategorie", "Kürzel",    "Bezeichnung",      "PZN", "Einheit", "Preis", "Ref.Nr.", ""]
            //   col = ["",          "ACCINJEKT", "ACC Injekt 300mg", "",    "",        "0,00",  "M3558",   ""]
            switch (colCount) {
                case 0:
                    medavisBean.setKategorie(entry);
                    break;
                case 1:
                    medavisBean.setKuerzel(entry);
                    break;
                case 2:
                    medavisBean.setBezeichnung(entry);
                    break;
                case 3:
                    medavisBean.setPzn(entry);
                    break;
                case 4:
                    medavisBean.setEinheit(entry);
                    break;
                case 5:
                    Double d = ParseHelper.parseDouble(entry);
                    medavisBean.setPreis(d);
                    /*if (null == d) {
                        logger.error("Cannot parse 'Preis' from '" + entry + "':");
                    }*/
                    break;
                case 6:
                    medavisBean.setRefnr(entry);
                    break;
            }
            // Increase column counter
            colCount++;
        }
    }

}
